package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tacademy.qodbtn41.gosurf.MainActivity;
import com.tacademy.qodbtn41.gosurf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AfterLoginFragment extends android.support.v4.app.Fragment {


    public AfterLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_after, container, false);
        Button btn = (Button)view.findViewById(R.id.btn_go_main);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }


}
