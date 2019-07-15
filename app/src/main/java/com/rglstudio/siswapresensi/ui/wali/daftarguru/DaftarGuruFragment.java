package com.rglstudio.siswapresensi.ui.wali.daftarguru;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.adapter.DaftarGuruAdapter;
import com.rglstudio.siswapresensi.model.ResponGuru;
import com.rglstudio.siswapresensi.service.API;
import com.rglstudio.siswapresensi.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarGuruFragment extends Fragment implements DaftarGuruView{
    @BindView(R.id.swipeLay)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rvGuru)
    RecyclerView rvGuru;

    private DaftarGuruPresenter presenter;
    private DaftarGuruAdapter adapter;

    public DaftarGuruFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daftar_guru, container, false);
        ButterKnife.bind(this, view);

        initView();
        loadData();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        return view;
    }

    private void initView() {
        presenter = new DaftarGuruPresenter(this);
        adapter = new DaftarGuruAdapter();
        rvGuru.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    private void loadData() {
        refreshLayout.setRefreshing(true);
        presenter.getGuru(API.GET_ALL_GURU);
    }

    @Override
    public void onSuccess(ResponGuru responGuru) {
        refreshLayout.setRefreshing(false);
        adapter.setList(responGuru.getData());
        rvGuru.setAdapter(adapter);
    }

    @Override
    public void onFailed(String msg) {
        refreshLayout.setRefreshing(false);
        DialogUtil.showToast(getContext(), msg);
    }
}
