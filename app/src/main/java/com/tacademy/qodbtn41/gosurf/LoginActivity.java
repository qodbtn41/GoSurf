package com.tacademy.qodbtn41.gosurf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tacademy.qodbtn41.gosurf.fragment.AfterLoginFragment;
import com.tacademy.qodbtn41.gosurf.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setToolbar();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container_login, new LoginFragment()).commit();
        }
    }
    private void setToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
    }
    public void pushAfterLoginFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_login, new AfterLoginFragment()).addToBackStack(null).commit();
    }
}
