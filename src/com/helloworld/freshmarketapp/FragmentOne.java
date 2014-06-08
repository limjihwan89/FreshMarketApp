package com.helloworld.freshmarketapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
 
public class FragmentOne extends Fragment {
	ImageView ivIcon;
    TextView tvItemName;
    Spinner spinner;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public FragmentOne()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

  	  View view = inflater.inflate(R.layout.fragment_layout_one, container,
                false);
  	  spinner = (Spinner) view.findViewById(R.id.spinner1);
  	  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.categories, android.R.layout.simple_spinner_item);
  	  adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
  	  spinner.setAdapter(adapter);
    return view;
    }
    
}