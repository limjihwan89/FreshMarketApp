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

	// ImageView ivIcon;
	// TextView tvItemName;
	TextView tvMode1;
	Switch swMode;
	TextView tvMode2;
	Button btnNext1;
	/*
	 * TextView tvSearch; Spinner spSearch; TextView tvTitle; EditText etTitle;
	 * TextView tvPicture; Button btPicture; EditText etPicture; TextView
	 * tvPrice1; EditText etPrice; TextView tvPrice2; TextView tvContent;
	 * EditText etContent; TextView tvLocation; Button btLocation; Button
	 * btRegist;
	 */
	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";

	public FragmentThree() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.registration_one, container,
				false);

		tvMode1 = (TextView) view.findViewById(R.id.tv_mode1);
		swMode = (Switch) view.findViewById(R.id.sw_mode);
		tvMode2 = (TextView) view.findViewById(R.id.tv_mode2);
		btnNext1 = (Button) view.findViewById(R.id.btn_next1);
		/*
		 * tvSearch = (TextView)view.findViewById(R.id.tv_search); spSearch =
		 * (Spinner)view.findViewById(R.id.sp_search); tvTitle =
		 * (TextView)view.findViewById(R.id.tv_title); etTitle =
		 * (EditText)view.findViewById(R.id.et_title); tvPicture =
		 * (TextView)view.findViewById(R.id.tv_picture); btPicture =
		 * (Button)view.findViewById(R.id.bt_picture); etPicture =
		 * (EditText)view.findViewById(R.id.et_picture); tvPrice1 =
		 * (TextView)view.findViewById(R.id.tv_price1); etPrice =
		 * (EditText)view.findViewById(R.id.et_price); tvPrice2 =
		 * (TextView)view.findViewById(R.id.tv_price2); tvContent =
		 * (TextView)view.findViewById(R.id.tv_content); etContent =
		 * (EditText)view.findViewById(R.id.et_content); tvLocation =
		 * (TextView)view.findViewById(R.id.tv_location); btLocation =
		 * (Button)view.findViewById(R.id.bt_location); btRegist =
		 * (Button)view.findViewById(R.id.bt_regist);
		 * 
		 * 
		 * ArrayAdapter<CharSequence> adapter =
		 * ArrayAdapter.createFromResource(getActivity().getBaseContext(),
		 * R.array.categories, android.R.layout.simple_spinner_item);
		 * adapter.setDropDownViewResource
		 * (android.R.layout.simple_dropdown_item_1line);
		 * spSearch.setAdapter(adapter);
		 * 
		 * /* ivIcon = (ImageView) view.findViewById(R.id.frag3_icon);
		 * tvItemName = (TextView) view.findViewById(R.id.frag3_text);
		 * 
		 * tvItemName.setText(getArguments().getString(ITEM_NAME));
		 * ivIcon.setImageDrawable(view.getResources().getDrawable(
		 * getArguments().getInt(IMAGE_RESOURCE_ID)));
		 */
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