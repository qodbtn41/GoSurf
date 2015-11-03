package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.qodbtn41.gosurf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopDetailFragment extends android.support.v4.app.Fragment {

    private View view;

    public ShopDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_detail, container, false);
        setToolbar();
        return view;
    }

    private void setToolbar(){

    }





}
