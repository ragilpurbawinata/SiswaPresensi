package com.rglstudio.siswapresensi.ui.guru.inputpresensi;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

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
import com.rglstudio.siswapresensi.util.DateFormatUtil;
import com.rglstudio.siswapresensi.util.DialogUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class PresensiSiswaFragment extends Fragment implements SiswaKelasView, InputAdapter.SiswaKelasListerner{
    @BindView(R.id.rvPresensiInput)
    RecyclerView rvKelas;
    @BindView(R.id.etTgl)
    EditText etTgl;

    private SiswaKelasPresenter presenter;
    private InputAdapter adapter;
    private String kdKelas;
    private AlertDialog.Builder dialog;
    private ProgressDialog m_Dialog;

    private String tanggalPost;
    private String sesi = "pagi";
    private String status = "hadir";

    public PresensiSiswaFragment() {
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
        View view = inflater.inflate(R.layout.fragment_presensi_siswa, container, false);
        ButterKnife.bind(this, view);

        initView();
        initTanggal();
        loadData();

        return view;
    }

    private void initTanggal() {
        etTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SlyCalendarDialog()
                        .setSingle(true)
                        .setCallback(new SlyCalendarDialog.Callback() {
                            @Override
                            public void onCancelled() {

                            }

                            @Override
                            public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
                                if (firstDate != null) {
                                    tanggalPost = new SimpleDateFormat("yyyy-MM-dd").format(firstDate.getTime());
                                    String tglDisplay = DateFormatUtil.formatDisplay(tanggalPost, "yyyy-MM-dd",
                                            "E, d MMM yyyy");
                                    etTgl.setText(tglDisplay);
                                }
                            }
                        })
                        .show(getChildFragmentManager(), "TAG_SLYCALENDAR");
            }
        });
    }

    private void initView() {
        m_Dialog = new ProgressDialog(getContext());
        dialog = new AlertDialog.Builder(getContext());
        presenter = new SiswaKelasPresenter(this);
        rvKelas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
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

    }

    @Override
    public void onSuccessAddNilai(ResponAddNilai responAddNilai) {

    }

    @Override
    public void onSuccessAddPresensi(ResponAddPresensi responAddPresensi) {
        dialogDismiss();
        DialogUtil.showToast(getContext(), responAddPresensi.getMessage());
    }

    @Override
    public void onFailed(String msg) {
        dialogDismiss();
        DialogUtil.showToast(getContext(), msg);
    }

    @Override
    public void onInputClick(final DataSiswa dataSiswa) {
        if (TextUtils.isEmpty(etTgl.getText())) {
            DialogUtil.showAlert(getContext(), "Pilih tanggal dahulu");
        }
        else {
            View view = getLayoutInflater().inflate(R.layout.dialog_input_presensi, null);
            dialog.setView(view);
            dialog.setCancelable(true);

            RadioGroup rgSesi = view.findViewById(R.id.rgSesi);
            RadioGroup rgStatus = view.findViewById(R.id.rgStatus);

            rgSesi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.rbPagi) {
                        sesi = "pagi";
                    } else if (checkedId == R.id.rbWali) {
                        sesi = "siang";
                    }
                }
            });

            rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.rbHadir) {
                        status = "hadir";
                    } else if (checkedId == R.id.rbIzin) {
                        status = "izin";
                    } else if (checkedId == R.id.rbAlpha) {
                        status = "alpha";
                    }
                }
            });

            dialog.setTitle("Input presensi siswa");
            dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.addPresensi(API.ADD_PRESENSI, dataSiswa.getNis(), tanggalPost, status, sesi);
                    dialog.dismiss();
                    showProgressDialog("Menambahkan presensi...");
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
}
