package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

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
        ImageButton nearbyShopButton;
        nearbyShopButton = (ImageButton)findViewById(R.id.image_btn_nearby_shop);
        nearbyShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택된 POI의 이름가지고 근처샵 리스트를 요청한다.
                //스택에 근처샵이 있다면 그 위 스택은 제거
                Intent intent = new Intent(MapActivity.this, NearbyShopActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
