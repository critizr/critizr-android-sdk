package com.CritizrSDKExample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.CritizrSDK.CritizrSDK;
import com.CritizrSDK.listeners.FeedbackListener;
import com.CritizrSDK.listeners.RatingListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MyStoreActivity extends Activity implements FeedbackListener, RatingListener {

	public static final String DEBUG_TAG = "CRITIZR_SDK";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_store_layout);
		this.getActionBar().hide();
        try {
            String externalPlaceId = this.getResources().getString(R.string.external_place_id);
            CritizrSDK.getInstance().getPlaceRating(externalPlaceId);
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	public void MyStoreClickMethod(View view) {
		if(view.getId() == R.id.storelocator_btn){
			CritizrSDK.getInstance().openStoreLocator(this, null);
		}else if(view.getId() == R.id.my_store_btn){
            JSONObject object = new JSONObject();
			try {
				object.put("mode", "feedback");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String externalPlaceId = this.getResources().getString(R.string.external_place_id);
			CritizrSDK.getInstance().giveFeedback(this, externalPlaceId, object);
		}
	}


	@Override
	public void setOnRatingResultListener(double v) {
		Log.i("INFO", "Fetched the rating: " + v);
	}

	@Override
	public void setOnRatingErrorListener() {
		Log.i("INFO", "Error while fetching the place rating");
	}

	@Override
	public void setOnFeedbackSentListener() {
		Log.d(DEBUG_TAG, "Feedback sent");
	}
}
