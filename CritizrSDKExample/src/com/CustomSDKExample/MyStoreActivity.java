package com.CustomSDKExample;

import com.CritizrCustomSDK.CritizrListener;
import com.CritizrCustomSDK.CritizrSDK;
import com.CustomSDKExample.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MyStoreActivity extends Activity implements CritizrListener{
	
	public static final String DEBUG_TAG = "CRITIZR_SDK";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_store_layout);
		
		this.getActionBar().hide();
	
	}

	public void MyStoreClickMethod(View view) {
		
		String apiKey = MyStoreActivity.this.getResources().getString(R.string.critizr_api_key);
		if(view.getId() == R.id.storelocator_btn){
			CritizrSDK.getInstance(apiKey).openFeedbackActivity(this, this);
			
		}else if(view.getId() == R.id.my_store_btn){
			int externalPlaceId = 12;    /*External place id I choose for my example - Bolibio Valencienne*/
			CritizrSDK.getInstance(apiKey).openFeedbackActivity(this, this, externalPlaceId);
		}
		
	}

	@Override
	public void onFeedbackSent() {
		Log.d(DEBUG_TAG, "Feedback sent with externalPlaceId");
		
	}
	
	
}
