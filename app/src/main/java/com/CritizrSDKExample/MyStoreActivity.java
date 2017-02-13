package com.CritizrSDKExample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.CritizrSDK.CritizrListener;
import com.CritizrSDK.CritizrSDK;

import org.json.JSONException;
import org.json.JSONObject;

public class MyStoreActivity extends Activity implements CritizrListener{

	public static final String DEBUG_TAG = "CRITIZR_SDK";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_store_layout);

		this.getActionBar().hide();

//		/* To get place Rating */
        try {
            String apiKey = MyStoreActivity.this.getResources().getString(R.string.critizr_api_key);
            String externalPlaceId = "velo-paris-xvii";
            CritizrSDK.getInstance(apiKey).getPlaceRating(externalPlaceId, this);
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	public void MyStoreClickMethod(View view) {
		String apiKey = MyStoreActivity.this.getResources().getString(R.string.critizr_api_key);
		if(view.getId() == R.id.storelocator_btn){
			CritizrSDK.getInstance(apiKey).openFeedbackActivity(this, this, null);
		}else if(view.getId() == R.id.my_store_btn){
            JSONObject object = new JSONObject();
            try {
                object.put("mode", "feedback");
                object.put("user", "guillaume|guillaume@critizr.com");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String externalPlaceId = "velo-paris-xvii";    /*External place id I choose for my example - VELO PARIS*/
			CritizrSDK.getInstance(apiKey).openFeedbackActivity(this, this, externalPlaceId, object);
		}
	}

	@Override
	public void onFeedbackSent() {
		Log.d(DEBUG_TAG, "Feedback sent");
		
	}

	@Override
	public void onRatingResult(double rating) {
		Log.d(DEBUG_TAG, "Place Rating is "+rating);		
	}
}
