package com.idiscount.dfgden.idiscount.ui.fragments.base_fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.idiscount.dfgden.idiscount.R;


/**
 * Created by dfgden on 8/22/16.
 */
public class BaseFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    protected void setTitleToolbar(String text) {
        if (getActivity().findViewById(R.id.txtToolbarTitle) != null) {
            ((TextView) getActivity().findViewById(R.id.txtToolbarTitle)).setText(text);
        }
    }
}
