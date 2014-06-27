package com.helloworld.freshmarketapp;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegiLocationActivity extends Activity implements OnMapLongClickListener{
	
	class MyInfoWindowAdapter implements InfoWindowAdapter{
		
        private final View myContentsView;
		
        //Marker를 누르면 띄워줄 형식을 xml로
		MyInfoWindowAdapter() {
			myContentsView = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
		}
		
		@Override
		public View getInfoContents(Marker marker) {			
			TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            //tvTitle.setText(marker.getTitle());
			tvTitle.setText("제목 및 가격");
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            //tvSnippet.setText(marker.getSnippet());
            tvSnippet.setText("내용");
            return myContentsView;
		}
		
		@Override
		public View getInfoWindow(Marker marker) {
			// TODO Auto-generated method stub
			return null;
		}		
	}
	
	final int RQS_GooglePlayServices = 1;
	GoogleMap myMap;
	//TextView tvLocInfo;
	Button okBtn;
	private static String locationCoordi;
	Intent intent = new Intent();
	private static Double latitude, longitude;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regi_map);
        
		latitude = 37.498134;
		longitude = 127.027600;
		
        //tvLocInfo = (TextView)findViewById(R.id.locinfo);
		okBtn = (Button)findViewById(R.id.btn);
        
        FragmentManager myFragmentManager = getFragmentManager();
        MapFragment myMapFragment 
        	= (MapFragment)myFragmentManager.findFragmentById(R.id.map);
        myMap = myMapFragment.getMap();      
        myMap.setMyLocationEnabled(true);
        //myMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //myMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        
        myMap.getUiSettings().setZoomControlsEnabled(true);
        myMap.getUiSettings().setCompassEnabled(true);
        myMap.getUiSettings().setMyLocationButtonEnabled(true);
        
        myMap.getUiSettings().setRotateGesturesEnabled(true);
        myMap.getUiSettings().setScrollGesturesEnabled(true);
        myMap.getUiSettings().setTiltGesturesEnabled(true);
        myMap.getUiSettings().setZoomGesturesEnabled(true);
        //or myMap.getUiSettings().setAllGesturesEnabled(true);
        
        myMap.setTrafficEnabled(true);      
        myMap.setOnMapLongClickListener(this);       
        myMap.setInfoWindowAdapter(new MyInfoWindowAdapter());      
		myMap.setMyLocationEnabled(true);
		
		// default위치에 Marker찍기
		myMap.addMarker(new MarkerOptions().position
				(new LatLng(latitude, longitude)).title("병신같은 강남역").snippet("Home Address"));	
		myMap.animateCamera(CameraUpdateFactory.newLatLngZoom
				(new LatLng(latitude, longitude), 12.0f));
		
		okBtn.setOnClickListener(onClick);
    }
    
    OnClickListener onClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(RegiLocationActivity.this, FourActivity.class);
			intent.putExtra("location", locationCoordi);
			startActivity(intent);
		}   	
    };
    
	@Override
	protected void onResume() {

		super.onResume();		
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());		
		if (resultCode == ConnectionResult.SUCCESS){
			Toast.makeText(getApplicationContext(), 
					"원하는 지역을 선택하세요.", 
					Toast.LENGTH_LONG).show();	
		}else{
			GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);	
		} 
	}

	@Override
	public void onMapLongClick(LatLng point) {
		//tvLocInfo.setText("새로운 위치 등록 " + point.toString());
		Marker newMarker = myMap.addMarker(new MarkerOptions()
								.position(point)
								.snippet(point.toString()));		
		newMarker.setTitle(newMarker.getId());
		locationCoordi = point.toString();
		System.out.println("가져올 위치 좌표 : " + locationCoordi);
		
		intent.setClass(RegiLocationActivity.this, FourActivity.class);
		intent.putExtra("location", point);
		startActivity(intent);
	}
}