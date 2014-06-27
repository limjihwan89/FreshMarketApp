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
	public static String stateCode = "";
	
	Intent intent;
	
	//LocationManager gLocMan;
	//Geocoder gCoder;
	
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
		
		//gCoder = new Geocoder(this, Locale.KOREAN);
		
		if(FragmentThree.stateCode != null) {
			Intent intent2 = getIntent();			
			stateCode = intent2.getStringExtra("stateCode");
			System.out.println("사고 팔기 정보 : " + stateCode);
		}
		
		if(RegiLocationActivity.gridX != null && RegiLocationActivity.gridY != null) {
			btnLocation1.setEnabled(false);
			Intent intent1 = getIntent();
			locationData = intent1.getStringExtra("location");
			System.out.println("들어왔다!!!!!!!!!!!!!!!!! : " + locationData);
			/*if(locationData != null) {
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
			}*/
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
				//Intent intent1 = new Intent();
				intent.setClass(FourActivity.this, FiveActivity.class);
				intent.putExtra("location1", locationData);
				startActivity(intent);
			} else if (v.getId() == R.id.btn_previous1) {
				finish();
			} else if (v.getId() == R.id.btn_location1) {;
				//Intent intent2 = new Intent();
				intent.setClass(FourActivity.this, RegiLocationActivity.class);
				intent.putExtra("location2", location2);
				startActivity(intent);
			} else if (v.getId() == R.id.btn_location2) {
				RegiLocationActivity.gridX = null;
				RegiLocationActivity.gridY = null;
				//Intent intent2 = new Intent();
				intent.setClass(FourActivity.this, RegiLocationActivity.class);
				intent.putExtra("location2", location2);
				startActivity(intent);
			}
		}
	};
}
