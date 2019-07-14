package com.rglstudio.siswapresensi.ui.guru.inputnilai;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.adapter.KelasPagerAdapter;
import com.rglstudio.siswapresensi.model.ResponKelas;
import com.rglstudio.siswapresensi.service.API;
import com.rglstudio.siswapresensi.ui.guru.globalpresenterview.DaftarKelasPresenter;
import com.rglstudio.siswapresensi.ui.guru.globalpresenterview.DaftarKelasView;
import com.rglstudio.siswapresensi.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputNilaiFragment extends Fragment implements DaftarKelasView {
    @BindView(R.id.swipeLay)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.tabNilai)
    TabLayout tab;
    @BindView(R.id.pagerNilai)
    ViewPager pager;

    private DaftarKelasPresenter presenter;
    private KelasPagerAdapter pagerAdapter;

    public InputNilaiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_nilai, container, false);
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
        presenter = new DaftarKelasPresenter(this);
        pagerAdapter = new KelasPagerAdapter(getFragmentManager());
    }

    private void loadData() {
        refreshLayout.setRefreshing(true);
        presenter.getKelas(API.GET_ALL_KELAS);
    }

    @Override
    public void onSuccess(ResponKelas responKelas) {
        refreshLayout.setRefreshing(false);
        for (int i=0;i<responKelas.getData().size();i++){
            Bundle bundle = new Bundle();
            NilaiSiswaFragment fragment = new NilaiSiswaFragment();

            bundle.putString("kelas", responKelas.getData().get(i).getKdKelas());
            fragment.setArguments(bundle);

            pagerAdapter.addFragment(fragment, responKelas.getData().get(i).getNama());
        }
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(responKelas.getData().size());
        tab.setupWithViewPager(pager);
    }

    @Override
    public void onFailed(String msg) {
        refreshLayout.setRefreshing(false);
        DialogUtil.showToast(getContext(), msg);
    }
}
