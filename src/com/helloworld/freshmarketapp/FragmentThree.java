package com.helloworld.freshmarketapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener; 
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class FragmentThree extends Fragment {

	TextView tvMode1;
	Switch swMode;
	TextView tvMode2;
	Button btnNext1;

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";

	public FragmentThree() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.registration_one, container, false);
		
		tvMode1 = (TextView) view.findViewById(R.id.tv_mode1);
		swMode = (Switch) view.findViewById(R.id.sw_mode);
		tvMode2 = (TextView) view.findViewById(R.id.tv_mode2);
		btnNext1 = (Button) view.findViewById(R.id.btn_next1);
		
		btnNext1.setOnClickListener(onClick);
		
		return view;
	}

	OnClickListener onClick = new OnClickListener() {
		String mode = "";

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(getActivity().getBaseContext(), FourActivity.class);
			intent.putExtra("mode", mode);
			startActivity(intent);
			// Intent intent = new Intent(MainActivity.class ,
			// FourActivity.class);
		}
	};
}