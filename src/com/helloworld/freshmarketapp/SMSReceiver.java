package com.helloworld.freshmarketapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.helloworld.freshmarketapp.SMSReceiverActivity;;

public class SMSReceiver extends BroadcastReceiver {
	
	public static String MESSAGE = "message";
	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	
	@Override
	public void onReceive(Context context, Intent intent) {	
		try{
			if (intent.getAction().equals(SMS_RECEIVED)) {
				Bundle bundle = intent.getExtras();
				if (bundle != null) {
					Object[] pdus = (Object[])bundle.get("pdus");
					final SmsMessage[] messages = new SmsMessage[pdus.length];
					for (int i = 0; i<pdus.length; i++) {
						messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
					}
					if (messages.length > -1) {
						abortBroadcast();
						
						Intent intent1 = new Intent(context, SMSReceiverActivity.class);
						intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent1.putExtra(MESSAGE, messages[0].getMessageBody());
						context.startActivity(intent1);
						/*
						intent.putExtra(MESSAGE, messages[0].getMessageBody());
						intent.setClass(context, RedirectActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);*/
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}		
	}
}