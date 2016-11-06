package com.idiscount.dfgden.idiscount.ui.fragments.about_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idiscount.dfgden.idiscount.R;
import com.idiscount.dfgden.idiscount.ui.fragments.base_fragment.BaseFragment;


public class AboutFragment extends BaseFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleToolbar(getString(R.string.about_title));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }

    public static AboutFragment getInstance(){
        AboutFragment aboutFragment = new AboutFragment();
        Bundle bundle = new Bundle();
        aboutFragment.setArguments(bundle);
        return aboutFragment;
    }

}
