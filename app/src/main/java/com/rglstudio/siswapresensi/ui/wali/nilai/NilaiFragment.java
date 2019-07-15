package com.rglstudio.siswapresensi.ui.wali.nilai;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.adapter.NilaiAdapter;
import com.rglstudio.siswapresensi.model.ResponNilai;
import com.rglstudio.siswapresensi.service.API;
import com.rglstudio.siswapresensi.util.DialogUtil;
import com.rglstudio.siswapresensi.util.MyPref;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NilaiFragment extends Fragment implements NilaiView{
    @BindView(R.id.swipeLay)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rvNilai)
    RecyclerView rvNilai;

    private NilaiPresenter presenter;
    private NilaiAdapter adapter;
    private MyPref pref;

    public NilaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nilai, container, false);
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
        pref = new MyPref(getContext());
        presenter = new NilaiPresenter(this);
        adapter = new NilaiAdapter();
        rvNilai.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    private void loadData() {
        refreshLayout.setRefreshing(true);
        presenter.getNilai(API.GET_NILAI, pref.getKeyUserNis());
    }

    @Override
    public void onSuccess(ResponNilai responNilai) {
        refreshLayout.setRefreshing(false);
        adapter.setList(responNilai.getData());
        rvNilai.setAdapter(adapter);
    }

    @Override
    public void onFailed(String msg) {
        refreshLayout.setRefreshing(false);
        DialogUtil.showToast(getContext(), msg);
    }
}
