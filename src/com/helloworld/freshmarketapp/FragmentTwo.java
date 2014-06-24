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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTwo extends Fragment {

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";
	private static View view;
	/**
	 * Note that this may be null if the Google Play services APK is not
	 * available.
	 */
	private static GoogleMap mMap;
	private static Double latitude, longitude;
	private static JSONArray jArr;
	private static Location myLocation;

	private static ArrayList<String> getUserNum;
	private static ArrayList<String> getItemNum;
	private static ArrayList<String> getItemName;
	private static ArrayList<String> getItemInfo;
	private static ArrayList<String> getPrice;
	private static ArrayList<Integer> getStateCode;
	private static ArrayList<String> getCategory1;
	private static ArrayList<String> getCategory2;
	private static ArrayList<String> getFilePath1;
	private static ArrayList<String> getFilePath2;
	private static ArrayList<String> getFilePath3;
	private static ArrayList<Double> getGridX1;
	private static ArrayList<Double> getGridY1;

	// private static ArrayList<Double> getGridX2;
	// private static ArrayList<Double> getGridY2;
	// private static ArrayList<Double> getGridX3;
	// private static ArrayList<Double> getGridY3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		// // node통신
		// StrictMode.ThreadPolicy policy = new
		// StrictMode.ThreadPolicy.Builder()
		// .permitAll().build();
		// StrictMode.setThreadPolicy(policy);
		// // final String itemURL = "http://192.168.200.80:3000/findItemList";
		// final String itemURL = "http://192.168.219.107:3000/findItemList";
		//
		// InputStream itemIs = null;
		// String itemResult = "";
		// String result = "";
		//
		// try {
		// HttpClient itemHttpClient = new DefaultHttpClient();
		//
		// ArrayList<NameValuePair> nameValuePairs = new
		// ArrayList<NameValuePair>();
		// String phoneNumber = "죠까";
		// nameValuePairs.add(new BasicNameValuePair("phoneNumber",
		// phoneNumber));
		//
		// HttpParams itemParams = itemHttpClient.getParams();
		// HttpConnectionParams.setConnectionTimeout(itemParams, 1000000);
		// HttpConnectionParams.setSoTimeout(itemParams, 1000000);
		//
		// HttpPost itemHttpPost = new HttpPost(itemURL);
		// // HttpGet itemHttpGet = new HttpGet(itemURL);
		// UrlEncodedFormEntity itemEntityRequest = new UrlEncodedFormEntity(
		// nameValuePairs, "UTF-8");
		// itemHttpPost.setEntity(itemEntityRequest);
		//
		// HttpResponse itemHttpResponse = itemHttpClient
		// .execute(itemHttpPost);
		// HttpEntity itemHttpEntity = itemHttpResponse.getEntity();
		// itemIs = itemHttpEntity.getContent();
		//
		// HttpResponse dbHttpResponse = itemHttpClient.execute(itemHttpPost);
		// HttpEntity dbHttpEntity = dbHttpResponse.getEntity();
		// itemIs = dbHttpEntity.getContent();
		//
		// } catch (ClientProtocolException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// try {
		// BufferedReader br = new BufferedReader(new InputStreamReader(
		// itemIs, "UTF-8"));
		// StringBuilder sb = new StringBuilder();
		//
		// String line = null;
		// while ((line = br.readLine()) != null) {
		// sb.append(line + "\n");
		// }
		// result = sb.toString();
		// System.out.println("이밑에 json데이터가 나오면 성공한것이다!!" + result);
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// try {
		// System.out.println("디버깅1 : 출력된 데이터 JSON객체화 시작");
		// jArr = new JSONArray(result);
		// System.out.println(jArr.length() + "개의 데이터");
		// // JSONObject json = jArr.getJSONObject(0);
		// // System.out.println(json);
		//
		// System.out.println("디버깅2 : JSONObject파싱 시작");
		// getUserNum = new ArrayList<>();
		// getItemNum = new ArrayList<>();
		// getItemName = new ArrayList<>();
		// getItemInfo = new ArrayList<>();
		// getPrice = new ArrayList<>();
		// getStateCode = new ArrayList<>();
		// getCategory1 = new ArrayList<>();
		// getCategory2 = new ArrayList<>();
		// getFilePath1 = new ArrayList<>();
		// getFilePath2 = new ArrayList<>();
		// getFilePath3 = new ArrayList<>();
		// getGridX1 = new ArrayList<>();
		// getGridY1 = new ArrayList<>();
		// getGridX2 = new ArrayList<>();
		// getGridY2 = new ArrayList<>();
		// getGridX3 = new ArrayList<>();
		// getGridY3 = new ArrayList<>();
		// for (int i = 0; i < jArr.length(); i++) {
		// // json = jArr.getJSONObject(i);
		// // 0번째 부터 즉, [{...}, {...}, ...] JsonArray안의 0번째 JsonObject
		// // ==> { }
		// // 를 getJsonObject로 가져와서 JSONObject안에 넣어준후
		// // getJsonData.add(jArr.getString("item_name"));
		// // getJsonData.add(jArr.getString("item_info"));
		// // getJsonData.add(jArr.getString("price"));
		// // getJsonData.add(jArr.getString("grid_x1"));
		// // getJsonData.add(jArr.getString("grid_y1"));
		// // String배열에 해당 key의 value값을 넣어준다.
		// JSONObject json = jArr.getJSONObject(i);
		// getUserNum.add(json.getString("user_no"));
		// getItemNum.add(json.getString("item_no"));
		// getItemName.add(json.getString("item_name"));
		// getItemInfo.add(json.getString("item_info"));
		// getPrice.add(json.getString("price"));
		// getStateCode.add(json.getString("state_code"));
		// getCategory1.add(json.getString("category1"));
		// getCategory2.add(json.getString("category2"));
		// getFilePath1.add(json.getString("filepath1"));
		// getFilePath2.add(json.getString("filepath2"));
		// getFilePath3.add(json.getString("filepath3"));
		// getGridX1.add(json.getDouble("grid_x1"));
		// getGridY1.add(json.getDouble("grid_y1"));
		// getGridX2.add(json.getDouble("grid_x2"));
		// getGridY2.add(json.getDouble("grid_y2"));
		// getGridX3.add(json.getDouble("grid_x3"));
		// getGridY3.add(json.getDouble("grid_y3"));
		// }
		// } catch (JSONException e) {
		// e.printStackTrace();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		try {
			view = inflater.inflate(R.layout.map, container, false);
		} catch (InflateException e) {
			e.printStackTrace();
		}

		// Passing harcoded values for latitude & longitude. Please change as
		// per your need. This is just used to drop a Marker on the Map
		latitude = 37.498134;
		longitude = 127.027600;

		setUpMapIfNeeded(); // For setting up the MapFragment
		return view;
	}

	/***** Sets up the map if it is possible to do so *****/
	public static void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) MainActivity.fragmentManager
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	/**
	 * This is where we can add markers or lines, add listeners or move the
	 * camera.
	 * <p>
	 * This should only be called once and when we are sure that {@link #mMap}
	 * is not null.
	 */
	private static void setUpMap() {
		// For showing a move to my loction button
		// node통신
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		final String itemURL = "http://192.168.200.80:3000/findItemList";
		// final String itemURL = "http://192.168.219.107:3000/findItemList";

		InputStream itemIs = null;
		String itemResult = "";
		String result = "";

		try {
			HttpClient itemHttpClient = new DefaultHttpClient();

			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			String phoneNumber = "후훗";
			nameValuePairs.add(new BasicNameValuePair("phoneNumber",
					phoneNumber));

			HttpParams itemParams = itemHttpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(itemParams, 1000000);
			HttpConnectionParams.setSoTimeout(itemParams, 1000000);

			HttpPost itemHttpPost = new HttpPost(itemURL);
			// HttpGet itemHttpGet = new HttpGet(itemURL);
			UrlEncodedFormEntity itemEntityRequest = new UrlEncodedFormEntity(
					nameValuePairs, "UTF-8");
			itemHttpPost.setEntity(itemEntityRequest);

			HttpResponse itemHttpResponse = itemHttpClient
					.execute(itemHttpPost);
			HttpEntity itemHttpEntity = itemHttpResponse.getEntity();
			itemIs = itemHttpEntity.getContent();

			HttpResponse dbHttpResponse = itemHttpClient.execute(itemHttpPost);
			HttpEntity dbHttpEntity = dbHttpResponse.getEntity();
			itemIs = dbHttpEntity.getContent();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					itemIs, "UTF-8"));
			StringBuilder sb = new StringBuilder();

			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			result = sb.toString();
			// System.out.println("이밑에 json데이터가 나오면 성공한것이다!!" + result);
			System.out.println("이밑에 json데이터가 나오면 성공한것이다!!");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("디버깅1 : 출력된 데이터 JSON객체화 시작");
			jArr = new JSONArray(result);
			System.out.println(jArr.length() + "개의 데이터");
			// JSONObject json = jArr.getJSONObject(0);
			// System.out.println(json);

			System.out.println("디버깅2 : JSONObject파싱 시작");
			getUserNum = new ArrayList<>();
			getItemNum = new ArrayList<>();
			getItemName = new ArrayList<>();
			getItemInfo = new ArrayList<>();
			getPrice = new ArrayList<>();
			getStateCode = new ArrayList<>();
			getCategory1 = new ArrayList<>();
			getCategory2 = new ArrayList<>();
			getFilePath1 = new ArrayList<>();
			getFilePath2 = new ArrayList<>();
			getFilePath3 = new ArrayList<>();
			getGridX1 = new ArrayList<>();
			getGridY1 = new ArrayList<>();
			// getGridX2 = new ArrayList<>();
			// getGridY2 = new ArrayList<>();
			// getGridX3 = new ArrayList<>();
			// getGridY3 = new ArrayList<>();
			for (int i = 0; i < jArr.length(); i++) {
				// json = jArr.getJSONObject(i);
				// 0번째 부터 즉, [{...}, {...}, ...] JsonArray안의 0번째 JsonObject
				// ==> { }
				// 를 getJsonObject로 가져와서 JSONObject안에 넣어준후
				// getJsonData.add(jArr.getString("item_name"));
				// getJsonData.add(jArr.getString("item_info"));
				// getJsonData.add(jArr.getString("price"));
				// getJsonData.add(jArr.getString("grid_x1"));
				// getJsonData.add(jArr.getString("grid_y1"));
				// String배열에 해당 key의 value값을 넣어준다.
				JSONObject json = jArr.getJSONObject(i);
				getUserNum.add(json.getString("user_no"));
				getItemNum.add(json.getString("item_no"));
				getItemName.add(json.getString("item_name"));
				getItemInfo.add(json.getString("item_info"));
				getPrice.add(json.getString("price"));
				getStateCode.add(json.getInt("state_code"));
				getCategory1.add(json.getString("category1"));
				getCategory2.add(json.getString("category2"));
				getFilePath1.add(json.getString("filepath1"));
				getFilePath2.add(json.getString("filepath2"));
				getFilePath3.add(json.getString("filepath3"));
				getGridX1.add(json.getDouble("grid_x1"));
				getGridY1.add(json.getDouble("grid_y1"));
				// getGridX2.add(json.getDouble("grid_x2"));
				// getGridY2.add(json.getDouble("grid_y2"));
				// getGridX3.add(json.getDouble("grid_x3"));
				// getGridY3.add(json.getDouble("grid_y3"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mMap.setMyLocationEnabled(true);
		try {
			myLocation = mMap.getMyLocation();
			try {
				System.out.println("Latitude: " + myLocation.getLatitude()
						+ "\nLongitude: " + myLocation.getLongitude());
			} catch (Exception e) {
				System.out.println("여기여기 에러다1");
				e.printStackTrace();
				System.out.println("여기여기1");
			}
		} catch (Exception e) {
			System.out.println("여기여기 에러다2");
			e.printStackTrace();
			System.out.println("여기여기2");
		}
		if (myLocation != null) {
			mMap.addMarker(new MarkerOptions()
					.position(
							new LatLng(myLocation.getLatitude(), myLocation
									.getLongitude()))
					.title("이런 세상에나")
					.snippet("뭐냐 이거....")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.people)));
			System.out.println("여기여기3");
		} else {
			mMap.addMarker(new MarkerOptions()
					.position(new LatLng(latitude, longitude))
					.title("이런 세상에나")
					.snippet("뭐냐 이거....")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.people)));
			System.out.println("여기여기4");
		}
		for (int j = 0; j < jArr.length(); j++) {
			// System.out.println("좌표X" + j + " : " + getGridX1.get(j));
			// System.out.println("좌표Y" + j + " : " + getGridY1.get(j));
			if (getStateCode.get(j) == 1) {
				mMap.addMarker(new MarkerOptions()
						.position(
								new LatLng(getGridX1.get(j), getGridY1.get(j)))
						.title(getItemName.get(j))
						.snippet(getItemInfo.get(j))
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.bsh2)));
			} else if (getStateCode.get(j) == 2) {
				mMap.addMarker(new MarkerOptions()
						.position(
								new LatLng(getGridX1.get(j), getGridY1.get(j)))
						.title(getItemName.get(j))
						.snippet(getItemInfo.get(j))
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.ssh2)));

			} else if (getStateCode.get(j) == 3) {
				mMap.addMarker(new MarkerOptions()
						.position(
								new LatLng(getGridX1.get(j), getGridY1.get(j)))
						.title(getItemName.get(j))
						.snippet(getItemInfo.get(j))
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.dsh)));

			}
		}
		// For zooming automatically to the Dropped PIN Location
		if (myLocation == null) {
			mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					latitude, longitude), 12.0f));
			System.out.println("여기여기5");
		} else {
			mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					myLocation.getLatitude(), myLocation.getLongitude()), 12.0f));
			System.out.println("여기여기6");
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mMap != null) {
			setUpMap();
		}
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) MainActivity.fragmentManager
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	/****
	 * The mapfragment's id must be removed from the FragmentManager or else if
	 * the same it is passed on the next time then app will crash
	 ****/
	// 여기서부터
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
	}
	// 여기까지 없으면 그냥 지도 다시 못돌아감
}