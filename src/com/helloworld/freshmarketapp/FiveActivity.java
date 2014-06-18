package com.helloworld.freshmarketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FiveActivity extends Activity {
	
	TextView tvImage1;
    Button btnImage1;
    Button btnImage2;
    TextView tvImage2;
    Button btnPrevious2;
    Button btnNext3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.registration_three); 
		
		tvImage1 = (TextView)this.findViewById(R.id.tv_location1);
		btnImage1 = (Button)this.findViewById(R.id.btn_location1);
		btnImage2 = (Button)this.findViewById(R.id.btn_location2);
		tvImage2 = (TextView)this.findViewById(R.id.tv_location2);
		btnPrevious2 = (Button)this.findViewById(R.id.btn_previous2);
		btnNext3 = (Button)this.findViewById(R.id.btn_next3);
        
		btnPrevious2.setOnClickListener(onClick);
		btnNext3.setOnClickListener(onClick);
    	
    }
    
    OnClickListener onClick = new OnClickListener() {    
    	String Image = "";
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btn_next3) {
				Intent intent = new Intent(FiveActivity.this, SixActivity.class);
				intent.putExtra("Image", Image);
				FiveActivity.this.startActivity(intent);
			} else if(v.getId() == R.id.btn_previous2) {
				finish();
			} 
		}  
    };
}
