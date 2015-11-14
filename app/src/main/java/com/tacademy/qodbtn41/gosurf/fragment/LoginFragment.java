package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tacademy.qodbtn41.gosurf.LoginActivity;
import com.tacademy.qodbtn41.gosurf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends android.support.v4.app.Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btn = (Button)view.findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).pushAfterLoginFragment();
            }
        });

        if(getActivity().getActionBar() != null){
            getActivity().getActionBar().hide();
        }else{
            getActivity().findViewById(R.id.toolbar_login).setVisibility(View.GONE);
        }
        return view;
    }


}
