package com.rglstudio.siswapresensi.ui.wali.presensi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.adapter.PresensiAdapter;
import com.rglstudio.siswapresensi.model.ResponPresensi;
import com.rglstudio.siswapresensi.service.API;
import com.rglstudio.siswapresensi.util.DialogUtil;
import com.rglstudio.siswapresensi.util.MyPref;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class PresensiFragment extends Fragment implements PresensiView{
    @BindView(R.id.swipeLay)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.etTgl)
    EditText etTgl;
    @BindView(R.id.rvPresensi)
    RecyclerView rvPresensi;

    private PresensiPresenter presenter;
    private PresensiAdapter adapter;
    private MyPref pref;

    private String startDate, endDate;

    public PresensiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_presensi, container, false);
        ButterKnife.bind(this, view);

        initView();
        initClick();
        loadData();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        return view;
    }

    private void initClick() {
        etTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SlyCalendarDialog()
                        .setSingle(false)
                        .setCallback(new SlyCalendarDialog.Callback() {
                            @Override
                            public void onCancelled() {

                            }

                            @Override
                            public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {
                                if (firstDate != null && secondDate !=null) {
                                    startDate = new SimpleDateFormat("yyyy-MM-dd").format(firstDate.getTime());
                                    endDate = new SimpleDateFormat("yyyy-MM-dd").format(secondDate.getTime());

                                    String rangeDate = new SimpleDateFormat("d MMM yyyy").format(firstDate.getTime()) + " - "
                                            + new SimpleDateFormat("d MMM yyyy").format(secondDate.getTime());
                                    etTgl.setText(rangeDate);

                                    if (adapter.getList()!=null) {
                                        adapter.getList().clear();
                                    }
                                    adapter.notifyDataSetChanged();
                                    loadData();
                                }
                            }
                        })
                        .show(getChildFragmentManager(), "TAG_SLYCALENDAR");
            }
        });
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

        pref = new MyPref(getContext());
        presenter = new PresensiPresenter(this);
        adapter = new PresensiAdapter();
        rvPresensi.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    private void loadData() {
        refreshLayout.setRefreshing(true);
        presenter.getPresensi(API.GET_PRESENSI, pref.getKeyUserNis(), startDate, endDate);
    }

    @Override
    public void onSuccess(ResponPresensi responPresensi) {
        refreshLayout.setRefreshing(false);
        adapter.setList(responPresensi.getData());
        rvPresensi.setAdapter(adapter);
    }

    @Override
    public void onFailed(String msg) {
        refreshLayout.setRefreshing(false);
        DialogUtil.showToast(getContext(), msg);
    }
}
