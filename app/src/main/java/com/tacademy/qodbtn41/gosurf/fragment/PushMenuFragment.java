package com.tacademy.qodbtn41.gosurf.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.item.MenuCheckableView;
import com.tacademy.qodbtn41.gosurf.item.MenuTextView;
import com.tacademy.qodbtn41.gosurf.manager.PropertyManager;


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
        MenuCheckableView menuCheckableView = (MenuCheckableView)view.findViewById(R.id.push_use_select);
        menuCheckableView.setData("파도 알림", PropertyManager.getInstance().getPushUse());
        menuCheckableView.setOnCheckButtonClickListener(new MenuCheckableView.OnCheckButtonClickListener() {
            @Override
            public void onButtonClick(MenuCheckableView view, ImageView imageView, boolean isChecked) {
                if (isChecked) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_off));
                    PropertyManager.getInstance().setPushUse(false);
                    view.setIsChecked(false);
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_on));
                    PropertyManager.getInstance().setPushUse(true);
                    view.setIsChecked(true);
                }
            }
        });

        menuCheckableView = (MenuCheckableView) view.findViewById(R.id.push_sound);
        menuCheckableView.setData("소리", PropertyManager.getInstance().getPushSound());
        menuCheckableView.setOnCheckButtonClickListener(new MenuCheckableView.OnCheckButtonClickListener() {
            @Override
            public void onButtonClick(MenuCheckableView view,ImageView imageView, boolean isChecked) {
                if(isChecked){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_off));
                    PropertyManager.getInstance().setPushSound(false);
                    view.setIsChecked(false);
                }else{
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.check_on));
                    PropertyManager.getInstance().setPushSound(true);
                    view.setIsChecked(true);
                }
            }
        });

        MenuTextView menu = (MenuTextView)view.findViewById(R.id.push_type);
        menu.setData("진동", "매너모드에서만 켜기");
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_push_menu, new PushContentFragment()).addToBackStack(null).commit();
            }
        });

        menu = (MenuTextView)view.findViewById(R.id.push_location);
        menu.setData("알림 내용", "Great+Warning");
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_push_menu, new PushLocationFragment()).addToBackStack(null).commit();
            }
        });

        menu = (MenuTextView)view.findViewById(R.id.push_popup);
        menu.setData("알림 팝업", "항상 받기");
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_push_menu, new PushPopupFragment()).addToBackStack(null).commit();
            }
        });

        menu = (MenuTextView)view.findViewById(R.id.push_vibration);
        menu.setData("알림 지역", "동해 강원 죽도");
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_push_menu, new PushVibrationFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }


}
