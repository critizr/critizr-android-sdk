package com.CritizrSDKExample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.CritizrSDK.CritizrConfig;
import com.CritizrSDK.CritizrSDK;
import com.CritizrSDK.listeners.FeedbackListener;
import com.CritizrSDK.models.CzEnvironment;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements FeedbackListener {
	
	public static final String DEBUG_TAG = "CRITIZR_SDK";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.getActionBar().hide();

		String apiKey = this.getResources().getString(R.string.critizr_api_key);

		CritizrConfig.getInstance().setCzEnvironement(CzEnvironment.PRE_PRODUCTION);
		CritizrConfig.getInstance().setApiKey(apiKey);

		CritizrSDK.getInstance().openFeedbackActivityFromDeepLink(this, "https://critizr.com/z/NHWsiN/");

	}
	
	
	public void MainClickMethod(View view) {
		if(view.getId() == R.id.storelocator_btn){
            JSONObject object = new JSONObject();
            try {
                object.put("mode", "feedback");
                object.put("user", "YXJuYXVkfGFybmF1ZC5sYW5jZWxvdEBjcml0aXpyLmNvbQ=="); // arnaud|arnaud.lancelot@critizr.com en BASE64
            } catch (JSONException e) {
                e.printStackTrace();
            }

			CritizrSDK.getInstance().openFeedbackActivity(this, object);
		}else if(view.getId() == R.id.my_store_btn){
			MainActivity.this.startActivity(new Intent(this, MyStoreActivity.class));
		}
		
	}

	@Override
	public void setOnFeedbackSentListener() {
		Log.d(DEBUG_TAG, "Feedback sent");
	}
}
