package com.tacademy.qodbtn41.gosurf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/*지도화면에서는 클릭한 장소의 정보를 화면아래쪽에서 슥 나오는 뷰로 알려주고 샵정보화면으로 링크되게함.
* 선택된 장소의 정보를 같이 전달해서 샵정보화면에서 띄울수있게 하자.*/
public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init();
    }

    private void init() {

    }
}
