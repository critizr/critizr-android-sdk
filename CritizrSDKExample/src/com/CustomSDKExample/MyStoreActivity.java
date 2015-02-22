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
		
		String apiKey = MyStoreActivity.this.getResources().getString(R.string.api_key);
		if(view.getId() == R.id.storelocator_btn){
			CritizrSDK.getInstance(apiKey).openStorLocator(this, this);
			
		}else if(view.getId() == R.id.my_store_btn){
			String externalPlaceId = "1235";    /*External place id I choose for my example */
			CritizrSDK.getInstance(apiKey).openStore(this, this, externalPlaceId);
		}
		
	}

	@Override
	public void onMessageSent() {
		Log.d(DEBUG_TAG, "Remark sent from MyStoreActivity");

		
	}
	
	
}
