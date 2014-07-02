package com.helloworld.freshmarketapp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FiveActivity extends Activity {
	
	TextView tvImage1;
	Button btnImage1;
	Button btnImage2;
	TextView tvImage2;
	ImageView image;
	Button btnPrevious2;
	Button btnNext3;
	Bitmap bitmap; // image 안에 들어갈 이미지.
	Uri photo_uri;

	public final static int CAMERA_SHOOT = 100; //intent 에 사용될 요청코드
	public final static int GET_PICTURE = 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.registration_three);
		
		tvImage1 = (TextView) this.findViewById(R.id.tv_image1);
		btnImage1 = (Button) this.findViewById(R.id.btn_image1);
		btnImage2 = (Button) this.findViewById(R.id.btn_image2);
		tvImage2 = (TextView) this.findViewById(R.id.tv_image2);
		image = (ImageView) this.findViewById(R.id.image);
		btnPrevious2 = (Button) this.findViewById(R.id.btn_previous2);
		btnNext3 = (Button) this.findViewById(R.id.btn_next3);
		
		btnImage1.setOnClickListener(onClick);
		btnImage2.setOnClickListener(onClick);
		btnPrevious2.setOnClickListener(onClick);
		btnNext3.setOnClickListener(onClick);
	}
	
	OnClickListener onClick = new OnClickListener() { 
		Intent intent;
		String Image = "";
		
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.btn_next3) {
				intent = new Intent(FiveActivity.this, SixActivity.class);
				intent.putExtra("Image", Image);
				FiveActivity.this.startActivity(intent);
			} else if(v.getId() == R.id.btn_previous2) {
				intent = new Intent(FiveActivity.this, FourActivity.class);
				FiveActivity.this.startActivity(intent);
			} else if(v.getId() == R.id.btn_image1) {
				/*intent = new Intent(Intent.ACTION_MAIN);
				String sd = Environment.getExternalStorageDirectory().getAbsolutePath();
				Uri uri = Uri.fromFile(new File(sd + "/test.jpg"));
				startActivity(intent);*/
				Intent intent_getPicture = new Intent(Intent.ACTION_GET_CONTENT);
				intent_getPicture.setType("image/*");
				startActivityForResult(intent_getPicture, GET_PICTURE);
					
			} else if(v.getId() == R.id.btn_image2) {
				if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ){
		    		// sd 카드 경로 가져오기
		    		String SD_path = Environment.getExternalStorageDirectory().getAbsolutePath(); //sd 카드 루트경로.
		    		SD_path += "/testCamera"; // 사용할 폴더. 경로만 지정한것이지 실제로 폴더가 생기거나 한건 아니다.
		    		
		    		// sd 카드에 사용할 폴더있는지 검사후 없으면 생성.
		    		File photoFile = new File(SD_path);
		    		if(!photoFile.exists()){
		    			//toLog("Image directory does not exist!!! new make directory");
		    			photoFile.mkdir();
		    		}else{
		    			//toLog("(File)photoFile : " + photoFile.getPath());
		    		}
	    			//toLog("(String)SD_path : " + SD_path);
	    			//toLog("(File)photoFile : " + photoFile.getPath());
		    		// 파일명 지정.파일명은 유니크 해야 하므로.. 가장 흔한 날짜 시간을 파일명으로 사용한다.
		    		// "eastgem test shoot XXXX-XX-XX XX:XX:XX.jpg" 라는 이름이 될것이다.
		    		SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		    				//			    				"yyyy-MM-dd"); 
		    		String filename = "/eastgem test shoot " + timeStampFormat.format(new Date()).toString() + ".jpg";

		    		// 폴더 생성이나 점검을 완료했으니 파일을 직접 생성하자.
		    		photoFile = new File(SD_path + filename); // 사진이 저장될 경로 및 파일명.
		    		photo_uri = Uri.fromFile(photoFile);

	    			//toLog("new (File)photoFile : " + photoFile.getPath());
	    			//toLog("Uri photo_uri : " + photo_uri.toString());
	    			
	    			// 카메라 Activity 부를때 사용할 인텐트
		    		Intent intent_Camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		    		intent_Camera.putExtra(MediaStore.EXTRA_OUTPUT, photo_uri);
		    		startActivityForResult(intent_Camera, CAMERA_SHOOT);
	    		}else{
	    			// sd 카드가 없으면 내부 메모리에 저장해도 되긴 한다.
	    			// 그러나 나는 사용하지 못하게 하겠다.
	    			//toAlert("SD카드가 장착되어 있지 않습니다.");
	    		}
			}
		}
	};	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case CAMERA_SHOOT: // 카메라 요청을 끝내고 리턴했을때.
				if(resultCode == Activity.RESULT_OK){ // 카메라로 사진을 찍고 저장했을때
					try{
						// data 가 있을경우. 이 경우가 기기마다 다르다고 한다.
						if(bitmap != null && !bitmap.isRecycled()){ bitmap.recycle(); bitmap = null; }
						bitmap = (Bitmap)data.getExtras().get(MediaStore.EXTRA_OUTPUT);
						image.setImageBitmap(bitmap);
					}catch (Exception e) {
						// data가 없을경우. T-Mobile G1 과 Nexus One 의 경우 data가 항상 null 이다.
						// 따라서 photo_uri 를 전역변수로 선언하고 사용하는데...
						// 별로 좋지는 못한 방법이라고 생각한다.
						// 비트맵 이미지를 업로드 또는 가공해야 할때 아래의 경로와 파일을 사용하면 된다.
						String physical_path = photo_uri.toString().substring(7).replaceAll("%3B", ";").replaceAll("%20"," ");
						if(bitmap != null && !bitmap.isRecycled()){ bitmap.recycle(); bitmap = null; }
						bitmap = BitmapFactory.decodeFile(physical_path);

						//toLog("CAMERA_SHOOT/ physical_path : " + physical_path);

						image.setImageBitmap(bitmap);
						photo_uri = null;
					}
				}else{ // 취소버튼을 눌렀을때.
					//toMessage("취소 하셨습니다.");
				}
				break;
			case GET_PICTURE: // 이미지 선택을 끝내고 리턴했을때.
				if(resultCode == Activity.RESULT_OK){ // 정상적으로 이미지를 선택했을경우.
					try{ // 이미지 선택의 경우 data 가 null 이 들어오는 일은 없지 싶다...
						Uri select_uri = data.getData();
						//toLog("GET_PICTURE/ select_uri : " + select_uri.toString());
						
						Cursor c = getContentResolver().query(Uri.parse(select_uri.toString()), null,null,null,null);
						c.moveToNext();
						
						// 비트맵 이미지를 업로드 또는 가공할경우 아래의 변수를 사용하도록 한다.
						String physical_path = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA)).toString().substring(5).replaceAll("%3B", ";").replaceAll("%20"," ");
						if(bitmap != null && !bitmap.isRecycled()){ bitmap.recycle(); bitmap = null; }
						bitmap = BitmapFactory.decodeFile(physical_path);

						//toLog("GET_PICTURE/ physical_path : " + physical_path);

						image.setImageBitmap(bitmap);
					}catch (Exception e) {
						//toAlert("Exception\n" + e.getMessage());
					}
				}else{ // 취소버튼을 눌렀을때.
					//toMessage("취소 하셨습니다.");
				}
				break;
			default:
				//toAlert("Error", "onActivityResult\n여기는 default 입니다.");
				break;
		}
	};
}		
