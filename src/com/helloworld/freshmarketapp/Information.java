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
import org.json.*;

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

public class Information extends Activity {

	public EditText getPhoneNumber, setPassword, setNickname;
	public Button btn_send1;

//	private ArrayList<String> DBgetJsonData;
//	private ArrayList<String> SMSgetJsonData;

	public static Integer random;
	public static String phoneNumber;
	public static String password;
	public static String nickname;

	public static final String PREFS_NUM = "NumPref";

	// private String message = "";
	/*
	 * @Override protected void onNewIntent(Intent intent) {
	 * super.onNewIntent(intent);
	 * 
	 * sms = (EditText)findViewById(R.id.sms); message =
	 * intent.getStringExtra("test"); sms.setText(message); }
	 */

	@SuppressLint({ "NewApi", "NewApi", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information);

		getPhoneNumber = (EditText) findViewById(R.id.getPhoneNum);
		setPassword = (EditText) findViewById(R.id.setPassword);
		setNickname	= (EditText) findViewById(R.id.setNickname);
		btn_send1 = (Button) findViewById(R.id.btn_send1);

		getPhoneNumber.setText(getMyPhoneNumber());
		// 안드로이드 버젼9 이상에서 StrictMode를 해제함으로써 메인 쓰레드에서도 통신이 가능하도록 설정
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		SharedPreferences settings = getSharedPreferences(PREFS_NUM, 0);
		if (settings.getString("registered", "").toString().equals("registered")) {
			System.out.println("여기7");
			Intent intent2 = new Intent(Information.this, MainActivity.class);
			startActivity(intent2);
			System.out.println("여기8");
		}
		btn_send1.setOnClickListener(sendToServer1);
		// btn_send2.setOnClickListener(sendToServer2);
		/*
		 * Bundle bundle = getIntent().getExtras(); //문자열로 된 키와 여러가지의 타입의 값을 저장
		 * 하는 일종의 Map 클래스이다. //Android에서 Activity간에 데이터를 주고 받을 때 Bundle 클래스를
		 * 사용하여 여러 가지의 데이터를 전송한다. //기본타입인 int, double, long, String 부터
		 * FloatArray, StringArrayList Serializable, Parcelable 구현한 객체를 전송한다. if
		 * (bundle != null){ if (bundle.containsKey(SmsReceiver.MESSAGE)){
		 * //containsKey() => 주어진 key값이 이 번들의 매핑에 포함이된 경우 true를 리턴 String
		 * message = bundle.getString(SmsReceiver.MESSAGE); if
		 * (message.startsWith("[")) { message = message.substring(8,14); } else
		 * { message ="잘못된 인증번호"; } this.et_send2.setText(message); } }
		 */
	}

	/*
	 * @Override public void onResume(){ super.onResume(); Bundle bundle =
	 * getIntent().getExtras(); //문자열로 된 키와 여러가지의 타입의 값을 저장 하는 일종의 Map 클래스이다.
	 * //Android에서 Activity간에 데이터를 주고 받을 때 Bundle 클래스를 사용하여 여러 가지의 데이터를 전송한다.
	 * //기본타입인 int, double, long, String 부터 FloatArray, StringArrayList
	 * Serializable, Parcelable 구현한 객체를 전송한다. if (bundle != null){ if
	 * (bundle.containsKey(SmsReceiver.MESSAGE)){ //containsKey() => 주어진 key값이 이
	 * 번들의 매핑에 포함이된 경우 true를 리턴 String message =
	 * bundle.getString(SmsReceiver.MESSAGE); if (message.startsWith("[")) {
	 * message = message.substring(8, 14); } else { message ="잘못된 인증번호"; }
	 * sms.setText(message); } } }
	 */

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
//			final String SMSURL = "http://192.168.200.80:8080/HttpTest2/smsTest.jsp";
//			final String SMSURL = "http://192.168.219.107:8080/HttpTest2/smsTest.jsp";
//			final String SMSURL = "http://192.168.200.43:8080/HttpTest2/smsTest.jsp";
			final String DBURL = "http://192.168.200.43:3000/addUser2";
			final String SMSURL =
			 "http://192.168.200.43:8080/resources/smsSend.jsp?"
			 + "action="
			 + action
			 + "&msg="
			 + random.toString()
			 + "&rphone="
			 + getPhoneNumber.getText().toString();
			// bit : 192.168.200.43
			// home : 192.168.219.103
			// hotspot : 192.168.43.137
			// String simpleData = "?hey=IOK&really=FunThis";
			phoneNumber = getPhoneNumber.getText().toString();
			password = setPassword.getText().toString();
			nickname = setNickname.getText().toString();
			
			InputStream DBis = null;
			InputStream SMSis = null;

//			String DBresult = "";
//			String SMSresult = "";

			try {
				System.out.println("디버깅1-1 : HttpProtocol시작!!!!!!!");
				HttpClient DBhttpClient = new DefaultHttpClient();
				HttpClient SMShttpClient = new DefaultHttpClient();

				// 서버로 보낼 데이터를 리스트에 담기

				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("phoneNumber", phoneNumber));
				nameValuePairs.add(new BasicNameValuePair("password", password));
				nameValuePairs.add(new BasicNameValuePair("certify", random.toString()));
				nameValuePairs.add(new BasicNameValuePair("nickname", nickname));

				// http프로토콜 및 프레임 워크 매개변수의 컬렉션
				HttpParams DBparams = DBhttpClient.getParams();
				HttpConnectionParams.setConnectionTimeout(DBparams, 1000);
				HttpConnectionParams.setSoTimeout(DBparams, 1000);
				HttpParams SMSparams = SMShttpClient.getParams();
				HttpConnectionParams.setConnectionTimeout(SMSparams, 1000);
				HttpConnectionParams.setSoTimeout(SMSparams, 1000);

				// Post방식으로 서버에 데이터(리스트) 보내기
				System.out.println("디버깅1-2 : 해당 URL로 통신 시작!!!!!");
				HttpPost DBhttpPost = new HttpPost(DBURL);
				HttpPost SMShttpPost = new HttpPost(SMSURL);

				UrlEncodedFormEntity DBentityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
				UrlEncodedFormEntity SMSentityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
				DBhttpPost.setEntity(DBentityRequest);
				SMShttpPost.setEntity(SMSentityRequest);

				System.out.println("여기1");
				SharedPreferences settings = getSharedPreferences(PREFS_NUM, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("registered", "registered");
				editor.commit();

				// 데이터 보내고 응답받기
				System.out.println("디버깅1-3 : 응답받기!!!!!!!!!");
				HttpResponse DBhttpResponse = DBhttpClient.execute(DBhttpPost);
				HttpResponse SMShttpResponse = SMShttpClient.execute(SMShttpPost);

				HttpEntity DBhttpEntity = DBhttpResponse.getEntity();
				HttpEntity SMShttpEntity = SMShttpResponse.getEntity();

				DBis = DBhttpEntity.getContent();
				SMSis = SMShttpEntity.getContent();
				System.out.println("duddududu");
			
			} catch (IOException e) {
				System.out.println("여기다다다1");
				e.printStackTrace();
			}
//			try {
//				System.out.println("디버깅1-4 : 스트림생성!!!!!!");
//				BufferedReader DBbr = new BufferedReader(new InputStreamReader(
//						DBis, "UTF-8"));
////				BufferedReader SMSbr = new BufferedReader(new InputStreamReader(
////						SMSis, "UTF-8"));
//				StringBuilder DBsb = new StringBuilder();
////				StringBuilder SMSsb = new StringBuilder();
//
//
//				String DBline = null;
////				String SMSline = null;
//				System.out.println("디버깅2 : 서버로부터 데이터 출력 시작");
////				while (((DBline = DBbr.readLine()) != null) && ((SMSline = SMSbr.readLine()) != null)) {
////					DBsb.append(DBline + "\n");
////					SMSsb.append(SMSline + "\n");
////				}
//				while ((DBline = DBbr.readLine()) != null) {
//					DBsb.append(DBline + "\n");
//				}
////				while ((SMSline = SMSbr.readLine()) != null) {
////					SMSsb.append(SMSline + "\n");
////				}
//
//				DBresult = DBsb.toString();
////				SMSresult = SMSsb.toString();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

//			try {
//				System.out.println("디버깅3 : 출력된 데이터 JSON객체화 시작");
//				System.out.println("디버깅4 : 출력된 데이터" + DBresult);
////				System.out.println("디버깅4 : 출력된 데이터" + SMSresult);
//
//				JSONObject DBjson = new JSONObject(DBresult);
////				JSONObject SMSjson = new JSONObject(SMSresult);
//				// 통째로 읽어온 json데이터를 JSONObject로 받는다.
//				JSONArray DBjArr = DBjson.getJSONArray("dataSend");
////				JSONArray SMSjArr = SMSjson.getJSONArray("dataSend");
//
//				// jsonTest.jsp페이지에서 dataSend라는 key로 put해준 jsonMain을 가져온다.
//				// dataSend는 JsonArray의 key다.
//
//				System.out.println("디버깅5 : JSONArray파싱 시작");
////				for (int i = 0, j = 0; i < DBjArr.length() && j < SMSjArr.length() ; i++, j++) {
////					DBgetJsonData = new ArrayList<String>();
////					DBjson = DBjArr.getJSONObject(i);
////					SMSgetJsonData = new ArrayList<String>();
////					SMSjson = SMSjArr.getJSONObject(j);
////					// 0번째 부터 즉, [{...}, {...}, ...] JsonArray안의 0번째 JsonObject
////					// ==> { }
////					// 를 getJsonObject로 가져와서 JSONObject안에 넣어준후
////					DBgetJsonData.add(DBjson.getString("phoneNumber"));
////					DBgetJsonData.add(DBjson.getString("password"));
////					DBgetJsonData.add(DBjson.getString("nickname"));
////					SMSgetJsonData.add(SMSjson.getString("phoneNumber"));
////					SMSgetJsonData.add(SMSjson.getString("password"));
////					SMSgetJsonData.add(SMSjson.getString("certify"));
////				}
//				for (int i = 0; i < DBjArr.length(); i++) {
//					DBgetJsonData = new ArrayList();
//					DBjson = DBjArr.getJSONObject(i);
//					// 0번째 부터 즉, [{...}, {...}, ...] JsonArray안의 0번째 JsonObject
//					// ==> { }
//					// 를 getJsonObject로 가져와서 JSONObject안에 넣어준후
//					DBgetJsonData.add(DBjson.getString("phoneNumber"));
//					DBgetJsonData.add(DBjson.getString("password"));
//					DBgetJsonData.add(DBjson.getString("nickname"));
////					getJsonData.add(DBjson.getString("certify"));
//					// String배열에 해당 key의 value값을 넣어준다.
//				}
////				for (int j = 0; j < SMSjArr.length(); j++) {
////					SMSgetJsonData = new ArrayList();
////					SMSjson = SMSjArr.getJSONObject(j);
////					// 0번째 부터 즉, [{...}, {...}, ...] JsonArray안의 0번째 JsonObject
////					// ==> { }
////					// 를 getJsonObject로 가져와서 JSONObject안에 넣어준후
////					SMSgetJsonData.add(SMSjson.getString("phoneNumber"));
//////					SMSgetJsonData.add(DBjson.getString("password"));
//////					SMSgetJsonData.add(DBjson.getString("nickname"));
////					SMSgetJsonData.add(SMSjson.getString("certify"));
////					// String배열에 해당 key의 value값을 넣어준다.
////				}
//
//				System.out.println("DB휴대전화 번호 : " + DBgetJsonData.get(0));
//				System.out.println("DB비밀 번호 : " + DBgetJsonData.get(1));
//				System.out.println("DB닉네임 : " + DBgetJsonData.get(2));
//
////				System.out.println("SMS휴대전화 번호 : " + SMSgetJsonData.get(0));
////				System.out.println("SMS비밀 번호 : " + SMSgetJsonData.get(1));
////				System.out.println("SMS인증 번호 : " + SMSgetJsonData.get(2));
//
//			} catch (JSONException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
			}
	};
	/*
	 * OnClickListener sendToServer2 = new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub if(sms.getText() != null) { System.out.println("sms에서 파싱한 인증번호 : " +
	 * sms.getText()); System.out.println("내부적으로 생성된 인증번호 : " +
	 * random.toString());
	 * System.out.println(sms.getText().toString().equals(random.toString()));
	 * 
	 * if(sms.getText().toString().equals(random.toString())) {
	 * tv_get1.setText("인증 성공!!!!!!"); } else tv_get1.setText("인증 실패!!!!!!"); }
	 * } };
	 */
	/*
	 * private void setTextView() {
	 * 
	 * System.out.println("sms에서 파싱한 인증번호 : " + et_send2.getText());
	 * System.out.println("내부적으로 생성된 인증번호 : " + random.toString());
	 * 
	 * if(et_send2.getText().equals(random.toString())) {
	 * tv_get1.setText("인증 성공!!!!!!"); } else tv_get1.setText("인증 실패!!!!!!"); }
	 */
}