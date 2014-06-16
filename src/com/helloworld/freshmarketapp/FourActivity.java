package com.helloworld.freshmarketapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
	
    	tvLocation1 = (TextView)this.findViewById(R.id.tv_location1);
        btnLocation1 = (Button)this.findViewById(R.id.btn_location1);
        btnLocation2 = (Button)this.findViewById(R.id.btn_location2);
        tvLocation2 = (TextView)this.findViewById(R.id.tv_location2);
        btnPrevious1 = (Button)this.findViewById(R.id.btn_previous1);
        btnNext2 = (Button)this.findViewById(R.id.btn_next2);
	}
}
