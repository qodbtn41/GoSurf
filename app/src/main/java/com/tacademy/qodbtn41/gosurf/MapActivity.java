package com.tacademy.qodbtn41.gosurf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tacademy.qodbtn41.gosurf.data.Locations;
import com.tacademy.qodbtn41.gosurf.data.SpotData;
import com.tacademy.qodbtn41.gosurf.data.SpotItem;
import com.tacademy.qodbtn41.gosurf.item.MapInfoView;
import com.tacademy.qodbtn41.gosurf.manager.NetworkManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*지도화면에서는 클릭한 장소의 정보를 화면아래쪽에서 슥 나오는 뷰로 알려주고 샵정보화면으로 링크되게함.
* 선택된 장소의 정보를 같이 전달해서 샵정보화면에서 띄울수있게 하자.*/
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraChangeListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    final Map<SpotItem, Marker> mMarkerResolver = new HashMap<SpotItem, Marker>();
    final Map<Marker, SpotItem> mSpotResolver = new HashMap<Marker, SpotItem>();
    List<SpotItem> spotList;
    MapInfoView mapInfoView;
    String shopMarkerId;

    public static final int WEATHER_GREAT = 1;
    public static final int WEATHER_NORMAL = 2;
    public static final int WEATHER_BAD = 3;
    public static final int WEATHER_WARNING = 4;

    private static final int DEFAULT_ZOOM = 7;
    private static final int SHOP_ZOOM = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init();
    }

    private void shopCheck() {
        Intent intent = getIntent();
        Locations locations = (Locations) intent.getSerializableExtra("locations");
        if(locations != null){
            addShopMarker(locations);
        }
    }

    private void init() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapInfoView = (MapInfoView)findViewById(R.id.view_selected_marker_info);


    }

    //스팟 리스트 가져온다. 필요한 정보는 _id, weather_indicator, Spot_name, locations
    // 1. 위치찾고 상태에 따라 마커이미지 변경
    // 2. 마커를 누르면 그에따른 이름과 상태이미지, 근처샵버튼 보여줌
    private void getData() {
        NetworkManager.getInstance().getSpot(this, new NetworkManager.OnResultListener<SpotData>() {
            @Override
            public void onSuccess(SpotData result) {
                spotList = result.getItems();
                for (SpotItem s : spotList) {
                    addMarker(s);
                }
            }

            @Override
            public void onFail(int code) {

            }
        });
    }



    private void clearAll() {
        for (int i = 0; i < spotList.size(); i++) {
            SpotItem spotItem = spotList.get(i);
            Marker m = mMarkerResolver.get(spotItem);
            mMarkerResolver.remove(m);
            mSpotResolver.remove(spotItem);
            m.remove();
        }

        spotList.clear();
    }

    private void addMarker(SpotItem spotItem) {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(spotItem.getLocations().getLatitude(), spotItem.getLocations().getLongitude()));
        options.anchor(0.5f, 1);//icon과 position의 관계. 가운데 상단에 띄우려면 이와같이 설정.

        options.draggable(false);

        Marker m = mMap.addMarker(options);
        setMarkerIcon(spotItem, m);

        mMarkerResolver.put(spotItem, m);
        mSpotResolver.put(m, spotItem);
    }

    private void addShopMarker(Locations locations) {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(locations.getLatitude(), locations.getLongitude()));
        options.anchor(0.5f, 1);//icon과 position의 관계. 가운데 상단에 띄우려면 이와같이 설정.
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.shop_marker));
        options.draggable(false);
        //샵버튼 추가할 곳.
        moveMap(locations.getLatitude(), locations.getLongitude(), SHOP_ZOOM);
        Marker shopMarker = mMap.addMarker(options);
        shopMarkerId = shopMarker.getId();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(false);

        mMap.setOnCameraChangeListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);

        // 충북 영동이 우리나라의 중앙 36.159377, 127.813133
        LatLng center = new LatLng(35.8, 127.823133);
        moveMap(center.latitude, center.longitude, DEFAULT_ZOOM);
        getData();
        shopCheck();
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        mMap.getProjection();
    }

    Marker lastMarker;
    @Override
    public boolean onMarkerClick(Marker marker) {

        if(marker.getId().equals(shopMarkerId)){
            return true;
        }
        if(lastMarker != null){
            setMarkerIcon(mSpotResolver.get(lastMarker), lastMarker);
        }

        final SpotItem currentSpotItem = mSpotResolver.get(marker);

        //여기는 다른이미지라 setMarkerIcon이 아니다.
        switch (currentSpotItem.getWeather_indicator()){
            case WEATHER_GREAT : {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.great_marker_touch));
                break;
            }
            case WEATHER_NORMAL : {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.normal_marker_touch));
                break;
            }
            case WEATHER_BAD : {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bad_marker_touch));
                break;
            }
            case WEATHER_WARNING : {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.warning_marker_touch));
                break;
            }
        }
        lastMarker = marker;
        mapInfoView.setData(currentSpotItem);
        ImageButton nearbyShopButton = (ImageButton)mapInfoView.findViewById(R.id.image_btn_nearby_shop);
        nearbyShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택된 POI의 이름가지고 근처샵 리스트를 요청한다.
                //스택에 근처샵이 있다면 그 위 스택은 제거
                Intent intent = new Intent(MapActivity.this, NearbyShopActivity.class);
                intent.putExtra("location", currentSpotItem.getLocation_category());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        mapInfoView.setVisibility(View.VISIBLE);//슥 나오는 방식으로 변경
        return true;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(lastMarker != null){
            setMarkerIcon(mSpotResolver.get(lastMarker), lastMarker);
        }
        mapInfoView.setVisibility(View.GONE);
    }

    private void moveMap(double lat, double lng, int zoom) {
        CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.target(new LatLng(lat, lng));
        builder.zoom(zoom);

        CameraPosition position = builder.build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        mMap.moveCamera(update);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().cancelAll(this);
    }

    private void setMarkerIcon(SpotItem spotItem, Marker marker){
        switch (spotItem.getWeather_indicator()){
            case WEATHER_GREAT : {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.great_marker));
                break;
            }
            case WEATHER_NORMAL : {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.normal_marker));
                break;
            }
            case WEATHER_BAD : {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bad_marker));
                break;
            }
            case WEATHER_WARNING : {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.warning_marker));
                break;
            }
        }
    }
}
