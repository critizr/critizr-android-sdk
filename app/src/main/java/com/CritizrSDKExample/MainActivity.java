package com.CritizrSDKExample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.CritizrSDK.CritizrListener;
import com.CritizrSDK.CritizrSDK;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements CritizrListener{
	
	public static final String DEBUG_TAG = "CRITIZR_SDK";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.getActionBar().hide();
	}
	
	
	public void MainClickMethod(View view) {
		String apiKey = this.getResources().getString(R.string.critizr_api_key);

		if(view.getId() == R.id.storelocator_btn){

			JSONObject obj= new JSONObject();
			try {
				obj.put("user", "guillaume|guillaume@critizr.com");
				obj.put("mode", "quizonly");
			} catch (JSONException e) {
				e.printStackTrace();
			}

			CritizrSDK.getInstance(apiKey).openFeedbackActivity(this, this, obj);
		}else if(view.getId() == R.id.my_store_btn){
			MainActivity.this.startActivity(new Intent(this, MyStoreActivity.class));
		}
		
	}

	@Override
	public void onFeedbackSent() {
		Log.d(DEBUG_TAG, "Feedback sent");
	}


	@Override
	public void onRatingResult(double arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
