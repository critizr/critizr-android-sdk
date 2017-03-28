package com.CritizrSDKExample;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
		CritizrConfig.getInstance().setCzEnvironement(CzEnvironment.PRODUCTION);
		CritizrConfig.getInstance().setApiKey(apiKey);

		Intent intent = getIntent();
		Log.i("INFO", "I'm in onCreate method");
		if (intent != null){
			Log.i("INFO", "GOT INTENT");
			String action = intent.getAction();
			Log.i("INFO", "TRYING ACTION");
			if (action != null){
				Log.i("INFO", "GOT INTENT");
				Uri data = intent.getData();
				if (data != null && data.getAuthority() != null){
					Log.i("INFO", "GOT AUTHORITY " + data.getAuthority());
				}
				if (data != null && data.getAuthority() != null
						&& (data.getAuthority().startsWith("critizr.herokuapp.com")
						|| data.getAuthority().startsWith("critizr.com")
						|| data.getAuthority().startsWith("preprod.critizr.com"))){
					Log.i("INFO", "DEEP LINK -> " + data.toString());
					CritizrSDK.getInstance().openFeedbackActivityFromDeepLink(this, data.toString());
				}
			}
		}
	}

	@Override
	protected void onNewIntent(Intent intent){
		Log.i("INFO", "I'm in onNewIntent method");
		if (intent != null){
			Log.i("INFO", "GOT INTENT");
			String action = intent.getAction();
			Log.i("INFO", "TRYING ACTION");
			if (action != null){
				Log.i("INFO", "GOT INTENT");
				Uri data = intent.getData();
				if (data != null && data.getAuthority() != null){
					Log.i("INFO", "GOT AUTHORITY" + data.getAuthority());
				}
				if (data != null && data.getAuthority() != null
						&& (data.getAuthority().startsWith("critizr.herokuapp.com")
						|| data.getAuthority().startsWith("critizr.com")
						|| data.getAuthority().startsWith("preprod.critizr.com"))){
					Log.i("INFO", "DEEP LINK -> " + data.toString());
					CritizrSDK.getInstance().openFeedbackActivityFromDeepLink(this, data.toString());
				}
			}
		}
	}
	
	
	public void MainClickMethod(View view) {
		if(view.getId() == R.id.storelocator_btn){
            JSONObject object = new JSONObject();
            try {
                object.put("mode", "feedback");
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
