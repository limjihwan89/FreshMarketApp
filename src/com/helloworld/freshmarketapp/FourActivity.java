package com.helloworld.freshmarketapp;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FourActivity extends Activity {
	
	TextView tvLocation1;
	Button btnLocation1;
	Button btnLocation2;
	TextView tvLocation2;
	Button btnPrevious1;
	Button btnNext2;
	
	public static String locationData = "";
	//Intent intent = new Intent();
	
	LocationManager gLocMan;
	Geocoder gCoder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.registration_two);
		
		//String getStringExtra(location);
		
		tvLocation1 = (TextView) this.findViewById(R.id.tv_location1);
		btnLocation1 = (Button) this.findViewById(R.id.btn_location1);
		btnLocation2 = (Button) this.findViewById(R.id.btn_location2);
		tvLocation2 = (TextView) this.findViewById(R.id.tv_location2);
		btnPrevious1 = (Button) this.findViewById(R.id.btn_previous1);
		btnNext2 = (Button) this.findViewById(R.id.btn_next2);
		
		btnPrevious1.setOnClickListener(onClick);
		btnLocation1.setOnClickListener(onClick);
		btnLocation2.setOnClickListener(onClick);
		btnNext2.setOnClickListener(onClick);
		
		gCoder = new Geocoder(this, Locale.KOREAN);
		
		if(RegiLocationActivity.gridX != null && RegiLocationActivity.gridY != null) {
			Intent intent = getIntent();
			locationData = intent.getStringExtra("location");
			System.out.println("들어왔다!!!!!!!!!!!!!!!!! : " + locationData);
			if(locationData != null) {
				List<Address> addr;
				try {
					addr = gCoder.getFromLocation(RegiLocationActivity.gridX, RegiLocationActivity.gridY, 5);
					if(addr == null) {
						tvLocation2.setText("no result");
						return;
					}
					tvLocation2.setText(addr.get(0).toString());
				} catch (IOException e) {
					tvLocation2.setText(e.getMessage());
				}				
			}
		}
	}

	OnClickListener onClick = new OnClickListener() {

		String location2 = "";

		@Override
		public void onClick(View v) {
			// Intent intent = new Intent(FourActivity.this,
			// FiveActivity.class);
			// FourActivity.this.startActivity(intent);
			if (v.getId() == R.id.btn_next2) {
				Intent intent1 = new Intent();
				intent1.setClass(FourActivity.this, FiveActivity.class);
				intent1.putExtra("location1", locationData);
				startActivity(intent1);
			} else if (v.getId() == R.id.btn_previous1) {
				finish();
			} else if (v.getId() == R.id.btn_location1) {
				// android.app.FragmentManager frgManager =
				// getFragmentManager();
				// sfrgManager.beginTransaction().replace(R.id.content_frame,
				// new FragmentTwo()).commit();
				//if (fragment == null) {
				//	fm.beginTransaction().add(R.id.frame, new FragmentTwo(), "map").commit();
				//}
				//Intent intent = new Intent(FourActivity.this, RegiLocationActivity.class);
				//FourActivity.this.startActivity(intent);
				//System.out.println("좌표 있냐?? " + RegiLocationActivity.gridX + ", " + RegiLocationActivity.gridY);
				Intent intent2 = new Intent();
				intent2.setClass(FourActivity.this, RegiLocationActivity.class);
				intent2.putExtra("location2", location2);
				startActivity(intent2);
			} else if (v.getId() == R.id.btn_location2) {
				RegiLocationActivity.gridX = null;
				RegiLocationActivity.gridY = null;
				Intent intent2 = new Intent();
				intent2.setClass(FourActivity.this, RegiLocationActivity.class);
				intent2.putExtra("location2", location2);
				startActivity(intent2);
			}
		}
	};
}
