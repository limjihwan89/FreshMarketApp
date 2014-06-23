package com.helloworld.freshmarketapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SMSActivity extends Activity {
	
	public EditText getPhoneNumber;
	public EditText setPassword, setNickname;
	public Button btn_send1;
	
//	private ArrayList<String> getJsonData;
	
	public static Integer random;
	public static String phoneNumber;
	public static String password;
	public static String nickName;

	public static final String PREFS_NUM = "NumPref";
	
	@SuppressLint({ "NewApi", "NewApi", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_layout);

		getPhoneNumber = (EditText) findViewById(R.id.getPhoneNum);
		setPassword = (EditText) findViewById(R.id.setPassword);
		setNickname = (EditText) findViewById(R.id.setNickname);
		btn_send1 = (Button) findViewById(R.id.btn_send1);

		getPhoneNumber.setText(getMyPhoneNumber());
		// 안드로이드 버젼9 이상에서 StrictMode를 해제함으로써 메인 쓰레드에서도 통신이 가능하도록 설정
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NUM, 0);		
		if (settings.getString("registered", "").toString().equals("registered")) {
			System.out.println("여기7");
			Intent intent2 = new Intent(SMSActivity.this, MainActivity.class);
			startActivity(intent2);
			System.out.println("여기8");
		}
		
		btn_send1.setOnClickListener(sendToServer1);
	}
  
	@SuppressLint("UseValueOf")
	private String getPhoneNumber() {
		int result = (int) (Math.floor(Math.random() * 1000000) + 100000);
		if (result > 1000000) {
			result = result - 100000;
		}
		random = new Integer(result);
		System.out.println("생성된 랜덤 인증값 : " + random);

		TelephonyManager mTelePhonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		// TelephonyManager 객체를 얻기 위해서는 Context 객체에서 제공하는 getSystemService()
		// 메서드를 이용한다.
		String myNumber = mTelePhonyMgr.getLine1Number();
		if (myNumber != null) {
			if (myNumber.startsWith("+82")) {
				myNumber = "0" + myNumber.substring(3);
			} else if (myNumber.startsWith("10")) {
				myNumber = "0" + myNumber.substring(1);
			} else {
				myNumber = mTelePhonyMgr.getLine1Number();
			}
		}
		return myNumber;    
	}

	private CharSequence getMyPhoneNumber() {
		String s = getPhoneNumber();
		return s.substring(0);
	}

	OnClickListener sendToServer1 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String action = "go";
			
			System.out.println("디버깅1 : 버튼동작!!!!!!!!!!!!!!!");
			final String testURL = "http://192.168.200.43:8080/HttpTest2/smsTest.jsp";
			final String dbURL = "http://192.168.200.43:3000/addUser2";
			final String smsURL = "http://192.168.200.43:8080/resources/smsSend.jsp?"+
			"action="+action+"&msg="+random.toString()+"&rphone="+
					getPhoneNumber.getText().toString();
			// bit : 192.168.200.43
			// home : 192.168.219.103
			// hotspot : 192.168.43.137
			// String simpleData = "?hey=IOK&really=FunThis";
			phoneNumber = getPhoneNumber.getText().toString();
			password = setPassword.getText().toString();
			nickName = setNickname.getText().toString();
			
			InputStream dbIs = null;
			InputStream smsIs = null;
			String dbResult = "";
			String smsResult = "";

			try {
				System.out.println("디버깅1-1 : HttpProtocol시작!!!!!!!");
				HttpClient smsHttpClient = new DefaultHttpClient();
				HttpClient dbHttpClient = new DefaultHttpClient();
				// 서버로 보낼 데이터를 리스트에 담기
				
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("phoneNumber", phoneNumber));
				nameValuePairs.add(new BasicNameValuePair("password", password));
				nameValuePairs.add(new BasicNameValuePair("nickname", nickName));
				nameValuePairs.add(new BasicNameValuePair("certify", random.toString()));

				// http프로토콜 및 프레임 워크 매개변수의 컬렉션
				HttpParams smsParams = smsHttpClient.getParams();
				HttpConnectionParams.setConnectionTimeout(smsParams, 5000);
				HttpConnectionParams.setSoTimeout(smsParams, 5000);
				
				HttpParams dbParams = smsHttpClient.getParams();
				HttpConnectionParams.setConnectionTimeout(dbParams, 5000);
				HttpConnectionParams.setSoTimeout(dbParams, 5000);
				
				// Post방식으로 서버에 데이터(리스트) 보내기
				System.out.println("디버깅1-2 : 해당 URL로 통신 시작!!!!!");
				HttpPost smsHttpPost = new HttpPost(smsURL);
				UrlEncodedFormEntity smsEntityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
				smsHttpPost.setEntity(smsEntityRequest);
				
				HttpPost dbHttpPost = new HttpPost(dbURL);
				UrlEncodedFormEntity dbEntityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
				dbHttpPost.setEntity(dbEntityRequest);
				
				// 데이터 보내고 응답받기
				System.out.println("디버깅1-3 : 응답받기!!!!!!!!!");
				HttpResponse smsHttpResponse = smsHttpClient.execute(smsHttpPost);
				HttpEntity smsHttpEntity = smsHttpResponse.getEntity();
				smsIs = smsHttpEntity.getContent();
				
				HttpResponse dbHttpResponse = smsHttpClient.execute(dbHttpPost);
				HttpEntity dbHttpEntity = dbHttpResponse.getEntity();
				dbIs = dbHttpEntity.getContent();

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			try {
//				System.out.println("디버깅1-4 : 스트림생성!!!!!!");
//				BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//				StringBuilder sb = new StringBuilder();
				
//				String line = null;
//				System.out.println("디버깅2 : 서버로부터 데이터 출력 시작");
//				while ((line = br.readLine()) != null) {
//					sb.append(line + "\n");
//				}				
//				result = sb.toString();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
//			try {
//				System.out.println("디버깅3 : 출력된 데이터 JSON객체화 시작");
//				System.out.println("디버깅4 : 출력된 데이터" + result);
//				JSONObject json = new JSONObject(result);
				// 통째로 읽어온 json데이터를 JSONObject로 받는다.
//				JSONArray jArr = json.getJSONArray("dataSend");
				// jsonTest.jsp페이지에서 dataSend라는 key로 put해준 jsonMain을 가져온다.
				// dataSend는 JsonArray의 key다.
				
//				System.out.println("디버깅5 : JSONArray파싱 시작");
//				for (int i = 0; i < jArr.length(); i++) {
//					getJsonData = new ArrayList<>();
//					json = jArr.getJSONObject(i);
					// 0번째 부터 즉, [{...}, {...}, ...] JsonArray안의 0번째 JsonObject
					// ==> { }
					// 를 getJsonObject로 가져와서 JSONObject안에 넣어준후
//					getJsonData.add(json.getString("phoneNumber"));
//					getJsonData.add(json.getString("password"));
//					getJsonData.add(json.getString("certify"));
					// String배열에 해당 key의 value값을 넣어준다.
//				}
				
//				System.out.println("휴대 전화 : " + getJsonData.get(0));
//				System.out.println("비밀 번호 : " + getJsonData.get(1));
//				System.out.println("인증 번호 : " + getJsonData.get(2));
				
//			} catch (JSONException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

//			if(getJsonData.get(0) != null) {
				SharedPreferences settings = getSharedPreferences(PREFS_NUM, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("registered", "registered");
				editor.commit();
//			}
			System.out.println("여기1");
			}
		};
}