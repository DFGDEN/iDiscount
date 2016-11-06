package com.idiscount.dfgden.idiscount.ui.fragments.ad_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.idiscount.dfgden.idiscount.IDiscountApp;
import com.idiscount.dfgden.idiscount.R;
import com.idiscount.dfgden.idiscount.models.Apartment;
import com.idiscount.dfgden.idiscount.rest.RESTManager;
import com.idiscount.dfgden.idiscount.sharedpreference.PreferenceHelper;
import com.idiscount.dfgden.idiscount.ui.adaptors.AdAdaptor;
import com.idiscount.dfgden.idiscount.ui.fragments.base_fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AdFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AdFragmentView, AdAdaptor.OnPositionListener {

    private AdAdaptor adAdaptor;
    private Unbinder unbinder;
    private AdFragmentPresenter adFragmentPresenter;

    @Inject RESTManager restManager;

    @Inject PreferenceHelper preferenceHelper;

    @BindView(R.id.layoutRefresh) SwipeRefreshLayout layoutRefresh;

    @BindView(R.id.recycleList) RecyclerView recyclerView;

    @BindView(R.id.txtCount) TextView txtCount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleToolbar(getString(R.string.ad_title));
        ((IDiscountApp) getActivity().getApplication()).getApplicationComponent().inject(this);
        this.adFragmentPresenter = new AdFragmentPresenter(restManager, preferenceHelper);
        this.adFragmentPresenter.attachView(this);
        this.adAdaptor = new AdAdaptor(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ad, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleList(recyclerView);
        this.layoutRefresh.setOnRefreshListener(this);
        adFragmentPresenter.updApartments();
    }

    @Override
    public void onPause() {
        super.onPause();
        adFragmentPresenter.saveDataToPreference((ArrayList<Apartment>) adAdaptor.getApartments());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
        this.adFragmentPresenter.detachView();
    }

    @Override
    public void onRefresh() {
        adFragmentPresenter.updApartments();
    }

    @Override
    public void addApartments(final List<Apartment> apartments) {
        this.adAdaptor.addApartments(apartments);
        if (getActivity() != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int startPos = adAdaptor.getItemCount();
                    int itemCount = apartments.size();
                    adAdaptor.notifyItemRangeInserted(startPos, itemCount);
                    layoutRefresh.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public void updApartments(List<Apartment> apartments) {
        this.adAdaptor.updApartments(apartments);
        if (getActivity() != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adAdaptor.notifyDataSetChanged();
                    layoutRefresh.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public void updApartmentCount(final int count) {
        if (getActivity() != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtCount.setText(String.format(getString(R.string.ad_count), count+""));
                }
            });
        }
    }

    @Override
    public void showRefresh(boolean isRefresh) {
        this.layoutRefresh.setRefreshing(isRefresh);
    }

    @Override
    public void showError(final String error) {
        if (getActivity() != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    layoutRefresh.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public List<Apartment> getApartment() {
        return adAdaptor.getApartments();
    }

    @Override
    public void onPosition(int pos) {
            int maxPos = adAdaptor.getItemCount();
            this.adFragmentPresenter.tryToUpdate(pos, maxPos);
    }

    private void initRecycleList(RecyclerView recycleList) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        RecyclerView.ItemAnimator scaleInAnimationAdapter = new DefaultItemAnimator();
        recycleList.setItemAnimator(scaleInAnimationAdapter);
        recycleList.setLayoutManager(gridLayoutManager);
        recycleList.setAdapter(adAdaptor);
        adAdaptor.setOnPositionListener(this);
    }

    public static AdFragment getInstance(){
        AdFragment adFragment = new AdFragment();
        Bundle bundle = new Bundle();
        adFragment.setArguments(bundle);
        return adFragment;
    }

}
