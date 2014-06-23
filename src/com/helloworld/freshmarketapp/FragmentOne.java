package com.helloworld.freshmarketapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentOne extends Fragment {
	ImageView ivIcon;
	TextView tvItemName;
	Spinner spinner;
	EditText editText_Search, editText_Money_Min, editText_Money_Max;
	Button button_search;
	boolean isDone = false;

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";

	public FragmentOne() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_one, container,
				false);
		spinner = (Spinner) view.findViewById(R.id.spinner_search);
		editText_Search = (EditText) view.findViewById(R.id.EditText_Search);
		editText_Money_Min = (EditText) view
				.findViewById(R.id.EditText_Money_Min);
		editText_Money_Max = (EditText) view
				.findViewById(R.id.EditText_Money_Max);
		button_search = (Button) view.findViewById(R.id.button_search);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity().getBaseContext(), R.array.categories,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spinner.setAdapter(adapter);
		button_search.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				// Starting a new Intent
				Intent nextScreen = new Intent(getActivity(), FragmentTwo.class);
				// Sending data to another Activity
				nextScreen.putExtra("name", editText_Search.getText()
						.toString());
				nextScreen.putExtra("email", editText_Money_Min.getText()
						.toString());
				// starting new activity
				startActivity(nextScreen);
			}
		});
		return view;
	}
}
