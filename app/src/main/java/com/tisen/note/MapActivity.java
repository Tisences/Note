package com.tisen.note;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.tisen.note.activity.BaseActivity;
import com.tisen.note.utils.MyLocationListener;
import com.tisen.note.utils.NotifyListener;

/**
 * Created by tisen on 2016/11/7.
 */
public class MapActivity extends BaseActivity {

    public static final String Ak = "cEguDLVyk2Oe9iDhE91sg9nwqkG0dPL9";


    private MapView mapView;
    private boolean isFirstLoc = true;
    private NotifyListener notifyListener = new NotifyListener();
    public LocationClient locationClient = null;
    public BDLocationListener locationListener = new MyLocationListener();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.map);
        final BaiduMap baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                MyLocationData locationData = new MyLocationData.Builder()
                        .accuracy(bdLocation.getRadius())
                        .direction(100).latitude(bdLocation.getLatitude())
                        .longitude(bdLocation.getLongitude()).build();
                baiduMap.setMyLocationData(locationData);
                if(isFirstLoc){
                    isFirstLoc = false;
                    LatLng latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(latLng).zoom(18.0f);
                    baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                }
            }
        });
        initLocation();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd0911");
        int span = 1000;
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setIsNeedLocationDescribe(true);

        locationClient.setLocOption(option);
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        locationClient.stop();
        mapView.onDestroy();
        mapView = null;
        super.onDestroy();
    }
}
