package com.helloworld.freshmarketapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class FragmentFour extends Fragment {

	TextView tvLocation1;
    Button btnLocation1;
    Button btnLocation2;
    TextView tvLocation2;
    Button btnPrevious1;
    Button btnNext2;
    
    public FragmentFour() {
  	  
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  	  	
    	View view = inflater.inflate(R.layout.registration_two, container, false);
          
    	tvLocation1 = (TextView)view.findViewById(R.id.tv_location1);
        btnLocation1 = (Button)view.findViewById(R.id.btn_location1);
        btnLocation2 = (Button)view.findViewById(R.id.btn_location2);
        tvLocation2 = (TextView)view.findViewById(R.id.tv_location2);
        btnPrevious1 = (Button)view.findViewById(R.id.btn_previous1);
        btnNext2 = (Button)view.findViewById(R.id.btn_next2);
          
        /* 
      	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.categories, android.R.layout.simple_spinner_item);
        	adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        	spSearch.setAdapter(adapter);
        */	
        
        btnNext2.setOnClickListener(onClick);
        	
        return view;
    }
    
    OnClickListener onClick = new OnClickListener() {    
  	String location = "";
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(getActivity(), FragmentFive.class);
			intent.putExtra("location", location);
			startActivity(intent);
		}  
    };
}
