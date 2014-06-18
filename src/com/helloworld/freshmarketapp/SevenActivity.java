package com.helloworld.freshmarketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SevenActivity extends Activity {
	
	TextView tvContent;
	EditText etTitle;
	EditText etPrice;
	EditText etContent;
	Button btnRegistration1;
	Button btnRegistration2;
	Button btnPrevious;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		this.setContentView(R.layout.registration_five); 
	
		tvContent = (TextView)this.findViewById(R.id.tv_content);
		etTitle = (EditText)this.findViewById(R.id.et_title);
		etPrice = (EditText)this.findViewById(R.id.et_price);
		etContent = (EditText)this.findViewById(R.id.et_content);
		btnRegistration1 = (Button)this.findViewById(R.id.btn_registration1);
		btnRegistration2 = (Button)this.findViewById(R.id.btn_registration2);
		btnPrevious = (Button)this.findViewById(R.id.btn_previous4);
    
		btnPrevious.setOnClickListener(onClick);
	
	}

	OnClickListener onClick = new OnClickListener() {    
		String content = "";
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btn_previous4) {
				//finish();
				Intent intent = new Intent();
				intent.setClass(SevenActivity.this , TestActivity.class);
				intent.putExtra("content", content);
				startActivity(intent);
			}			
		}  
	};
}
