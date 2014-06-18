package com.helloworld.freshmarketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SixActivity extends Activity {
	
	TextView tvCategory;
	Spinner spnCategory1;
	Spinner spnCategory2;
	Button btnPrevious3;
	Button btnNext4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		this.setContentView(R.layout.registration_four); 
	
		tvCategory = (TextView)this.findViewById(R.id.tv_category);
		spnCategory1 = (Spinner)this.findViewById(R.id.spn_category1);
		spnCategory2 = (Spinner)this.findViewById(R.id.spn_category2);
		btnPrevious3 = (Button)this.findViewById(R.id.btn_previous3);
		btnNext4 = (Button)this.findViewById(R.id.btn_next4);
		
    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SixActivity.this, R.array.categories, android.R.layout.simple_spinner_item);
      	adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
      	spnCategory1.setAdapter(adapter);
    
      	btnPrevious3.setOnClickListener(onClick);
		btnNext4.setOnClickListener(onClick);
	
	}
	
	OnClickListener onClick = new OnClickListener() {    
		String category = "";
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btn_next4) {
				Intent intent = new Intent();
				intent.setClass(SixActivity.this , SevenActivity.class);
				intent.putExtra("category", category);
				startActivity(intent);
			} else if(v.getId() == R.id.btn_previous3) {
				finish();
			}
		}  
	};
}
