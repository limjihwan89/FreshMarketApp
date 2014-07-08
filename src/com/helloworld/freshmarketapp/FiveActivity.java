package com.helloworld.freshmarketapp;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FiveActivity extends Activity {
	
	TextView tvImage1;
	Button btnImage1;
	Button btnImage2;
	ImageView image;
	Button btnPrevious2;
	Button btnNext3;
	
	static int REQUEST_PICTURE = 1;
	static int REQUEST_PHOTO_ALBUM = 2;
	static String SAMPLEIMG = "photo.png";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.registration_three);
				
		tvImage1 = (TextView) this.findViewById(R.id.tv_image1);
		btnImage1 = (Button) this.findViewById(R.id.btn_image1);
		btnImage2 = (Button) this.findViewById(R.id.btn_image2);
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
			} else if(v.getId() == R.id.btn_image1) { //사진 앨범 연동
				photoAlbum();					
			} else if(v.getId() == R.id.btn_image2) { //카메라 연동
				takePicture();
			}
		}
	};	
	
  	void takePicture() {
  		//카메라 호출 intent 생성
  		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
  		File file = new File(Environment.getExternalStorageDirectory(), SAMPLEIMG);
  		// /storage/emulated/0(Environment.getExternalStorageDirectory()) + photo.png(SAMPLEIMG)
  		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
  		startActivityForResult(intent, REQUEST_PICTURE);
  	}
  	
  	void photoAlbum() {
  		//photo Album 호출 intent 생성
  		Intent intent = new Intent(Intent.ACTION_PICK);		
  		intent.setType(Images.Media.CONTENT_TYPE);
  		intent.setData(Images.Media.EXTERNAL_CONTENT_URI);
  		startActivityForResult(intent, REQUEST_PHOTO_ALBUM);
  	}
  	
  	//촬영한 사진을 imageView에 셋팅하기 위해서...
  	Bitmap loadPicture() {
  		File file = new File(Environment.getExternalStorageDirectory(), SAMPLEIMG);
  		BitmapFactory.Options option = new BitmapFactory.Options();
  		option.inSampleSize = 4;
  		System.out.println(file.getAbsolutePath()); //찍을때 마다 같은 경로
  		return BitmapFactory.decodeFile(file.getAbsolutePath(), option);
  		//file.getAbsolutePath() = /storage/emulated/0/photo.png
  	}
  	
  	@Override
  	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  		// TODO Auto-generated method stub
  		super.onActivityResult(requestCode, resultCode, data); 		
  		if(resultCode != RESULT_OK) {
  			return;
  		}
  		if(requestCode == REQUEST_PICTURE) { //imageView에 촬영한 사진 셋팅
  			image.setImageBitmap(loadPicture());
  		}
  		if(requestCode == REQUEST_PHOTO_ALBUM) { //imageView에 앨범 사진 셋팅
  			image.setImageURI(data.getData());
  			System.out.println(data.getData().getPath()); //경로가 찍히는데 무슨경로인지 파악 불가
  														  //기기 내의 local경로와 다름
  		}
  	}
}