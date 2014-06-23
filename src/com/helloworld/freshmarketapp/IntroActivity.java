package com.helloworld.freshmarketapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class IntroActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
		mHandler.sendEmptyMessageDelayed(0, 1500);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			startActivity(new Intent(IntroActivity.this, SMSActivity.class));
			overridePendingTransition(R.anim.fade, R.anim.hold);
			finish();
		}
	};
}
