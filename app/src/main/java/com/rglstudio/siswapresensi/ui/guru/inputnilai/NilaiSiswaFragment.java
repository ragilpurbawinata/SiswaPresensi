package com.rglstudio.siswapresensi.ui.guru.inputnilai;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.adapter.InputAdapter;
import com.rglstudio.siswapresensi.model.DataSiswa;
import com.rglstudio.siswapresensi.model.ResponAddNilai;
import com.rglstudio.siswapresensi.model.ResponAddPresensi;
import com.rglstudio.siswapresensi.model.ResponGcmId;
import com.rglstudio.siswapresensi.model.ResponSiswa;
import com.rglstudio.siswapresensi.service.API;
import com.rglstudio.siswapresensi.ui.guru.globalpresenterview.SiswaKelasPresenter;
import com.rglstudio.siswapresensi.ui.guru.globalpresenterview.SiswaKelasView;
import com.rglstudio.siswapresensi.ui.sendnotif.SendNotifPresenter;
import com.rglstudio.siswapresensi.ui.sendnotif.SendNotifView;
import com.rglstudio.siswapresensi.util.DialogUtil;
import com.rglstudio.siswapresensi.util.MyPref;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NilaiSiswaFragment extends Fragment implements
        SiswaKelasView, InputAdapter.SiswaKelasListerner, SendNotifView {
    @BindView(R.id.rvNilaiInput)
    RecyclerView rvKelas;

    private MyPref pref;
    private SiswaKelasPresenter presenter;
    private InputAdapter adapter;
    private String kdKelas;
    private AlertDialog.Builder dialog;
    private ProgressDialog m_Dialog;
    private SendNotifPresenter sendNotifPresenter;

    private int nilai;

    public NilaiSiswaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kdKelas = getArguments().getString("kelas");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nilai_siswa, container, false);
        ButterKnife.bind(this, view);

        initView();
        loadData();

        return view;
    }

    private void initView() {
        m_Dialog = new ProgressDialog(getContext());
        dialog = new AlertDialog.Builder(getContext());
        pref = new MyPref(getContext());
        presenter = new SiswaKelasPresenter(this);
        sendNotifPresenter = new SendNotifPresenter(this);
        rvKelas.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    private void loadData() {
        presenter.getSiswa(API.GET_SISWA_BY_KELAS, kdKelas);
    }

    @Override
    public void onSuccessGetSiswa(ResponSiswa responSiswa) {
        adapter = new InputAdapter(responSiswa.getData(), this);
        rvKelas.setAdapter(adapter);
    }

    @Override
    public void onSuccessGetGcm(ResponGcmId responGcmId) {
        String title = "Notifikasi nilai siswa";
        String msg = "Nilai mata pelajaran "+pref.getKeyUserNameMapel()+" : "+nilai;
        sendNotifPresenter.sendNotification(responGcmId.getData().getGcmId(), title, msg);
    }

    @Override
    public void onSuccessAddNilai(ResponAddNilai responAddNilai) {
        dialogDismiss();
        DialogUtil.showToast(getContext(), responAddNilai.getMessage());

        presenter.getGcm(API.GET_GCM_ID, responAddNilai.getNis());
        showProgressDialog("Mengirim notif...");
    }

    @Override
    public void onSuccessAddPresensi(ResponAddPresensi responAddPresensi) {

    }

    @Override
    public void onFailed(String msg) {
        dialogDismiss();
        DialogUtil.showToast(getContext(), msg);
    }

    @Override
    public void onInputClick(final DataSiswa dataSiswa) {
        View view = getLayoutInflater().inflate(R.layout.dialog_input_nilai, null);
        dialog.setView(view);
        dialog.setCancelable(true);

        final NumberPicker np = view.findViewById(R.id.numNilai);
        np.setMinValue(10);
        np.setMaxValue(100);

        dialog.setTitle("Input nilai siswa");
        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nilai = np.getValue();
                presenter.addNilai(API.ADD_NILAI, dataSiswa.getNis(), pref.getKeyUserKdMapel(), String.valueOf(nilai));
                dialog.dismiss();
                showProgressDialog("Menambahkan nilai...");
            }
        });

        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showProgressDialog(String message){
        m_Dialog.setMessage(message);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(false);
        m_Dialog.show();
    }

    public void dialogDismiss(){
        if (m_Dialog.isShowing()) {
            m_Dialog.dismiss();
        }
    }

    @Override
    public void onSuccessSendNotif(String msg) {
        dialogDismiss();
        DialogUtil.showToast(getContext(), msg);
    }

    @Override
    public void onFailedSendNotif(String msg) {
        dialogDismiss();
        DialogUtil.showToast(getContext(), msg);
    }
}
