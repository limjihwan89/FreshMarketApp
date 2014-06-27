package com.helloworld.freshmarketapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FourActivity extends Activity {

	TextView tvLocation1;
	Button btnLocation1; 
	Button btnLocation2;
	TextView tvLocation2;
	Button btnPrevious1;
	Button btnNext2;

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

	}

	OnClickListener onClick = new OnClickListener() {
		android.app.FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.frame);
		String location1 = "";
		String location2 = "";

		@Override
		public void onClick(View v) {
			// Intent intent = new Intent(FourActivity.this,
			// FiveActivity.class);
			// FourActivity.this.startActivity(intent);
			if (v.getId() == R.id.btn_next2) {
				Intent intent1 = new Intent();
				intent1.setClass(FourActivity.this, FiveActivity.class);
				intent1.putExtra("location1", location1);
				startActivity(intent1);
			} else if (v.getId() == R.id.btn_previous1) {
				finish();
			} else if (v.getId() == R.id.btn_location1 || v.getId() == R.id.btn_location2) {
				// android.app.FragmentManager frgManager =
				// getFragmentManager();
				// sfrgManager.beginTransaction().replace(R.id.content_frame,
				// new FragmentTwo()).commit();
				//if (fragment == null) {
				//	fm.beginTransaction().add(R.id.frame, new FragmentTwo(), "map").commit();
				//}
				//Intent intent = new Intent(FourActivity.this, RegiLocationActivity.class);
				//FourActivity.this.startActivity(intent);
				Intent intent2 = new Intent();
				intent2.setClass(FourActivity.this, RegiLocationActivity.class);
				intent2.putExtra("location2", location2);
				startActivity(intent2);
			}
		}
	};
}