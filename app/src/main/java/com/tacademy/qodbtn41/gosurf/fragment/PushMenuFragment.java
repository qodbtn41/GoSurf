package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tacademy.qodbtn41.gosurf.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PushMenuFragment extends android.support.v4.app.Fragment {


    public PushMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_push_menu, container, false);
        Button btn = (Button)view.findViewById(R.id.btn_push_content);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_push_menu, new PushContentFragment()).addToBackStack(null).commit();
            }
        });
        btn = (Button)view.findViewById(R.id.btn_push_location);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_push_menu, new PushLocationFragment()).addToBackStack(null).commit();
            }
        });
        btn = (Button)view.findViewById(R.id.btn_push_popup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_push_menu, new PushPopupFragment()).addToBackStack(null).commit();
            }
        });
        btn = (Button)view.findViewById(R.id.btn_push_vibration);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_push_menu, new PushVibrationFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }


}
