package com.helloworld.freshmarketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import com.helloworld.freshmarketapp.SMSActivity;
import com.helloworld.freshmarketapp.R;
import com.helloworld.freshmarketapp.SMSReceiver;

public class SMSReceiverActivity extends Activity {
	
	private EditText et_sms;
	private Button btn_ok;
	private TextView tv_get;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_recieve_layout);
		
		et_sms = (EditText)findViewById(R.id.et_sms);
		btn_ok = (Button)findViewById(R.id.btn_ok);
		tv_get = (TextView)findViewById(R.id.tv_get);
		
		btn_ok.setOnClickListener(Approval);		
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Bundle bundle = getIntent().getExtras();
		//문자열로 된 키와 여러가지의 타입의 값을 저장 하는 일종의 Map 클래스이다.
		//Android에서 Activity간에 데이터를 주고 받을 때 Bundle 클래스를 사용하여 여러 가지의 데이터를 전송한다.
		//기본타입인 int, double, long, String 부터 FloatArray, StringArrayList Serializable, Parcelable 구현한 객체를 전송한다.
		if (bundle != null){
			if (bundle.containsKey(SMSReceiver.MESSAGE)){
				//containsKey() => 주어진 key값이 이 번들의 매핑에 포함이된 경우 true를 리턴
				String message = bundle.getString(SMSReceiver.MESSAGE);
				if (message.startsWith("[")) {
					message = message.substring(8, 14);
				} else {
					message ="잘못된 인증번호";
				}
				et_sms.setText(message);
			}
		}
	}
	
	OnClickListener Approval = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(et_sms.getText() != null) {				
				System.out.println("sms에서 파싱한 인증번호 : " + et_sms.getText());
				System.out.println("내부적으로 생성된 인증번호 : " + SMSActivity.random.toString());
				System.out.println(et_sms.getText().toString().equals(SMSActivity.random.toString()));
				
				if(et_sms.getText().toString().equals(SMSActivity.random.toString())) {
					Intent intent = new Intent(SMSReceiverActivity.this, MainActivity.class);
					startActivity(intent);
				}
				else
					tv_get.setText("인증 실패!!!!!!");
			}
		}	
	};	
}
