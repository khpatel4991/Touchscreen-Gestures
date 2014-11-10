package com.phone.kashyap.touchscreengestures;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class FullscreenActivity extends Activity
{
	private static final String LOG_TAG = FullscreenActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.i(LOG_TAG, "Main Activity Started");
		setContentView(new DrawingView(this));
	}
}
