package com.tacademy.qodbtn41.gosurf.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.qodbtn41.gosurf.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PushContentFragment extends android.support.v4.app.Fragment {


    public PushContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_push_content, container, false);
    }


}
