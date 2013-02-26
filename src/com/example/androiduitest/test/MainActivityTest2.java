package com.example.androiduitest.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

import com.example.androiduitest.*;

public class MainActivityTest2 extends ActivityUnitTestCase<MainActivity> {

	public MainActivityTest2() {
		super(MainActivity.class);
	}

    public void testButton1() throws Exception {
        Intent intent = new Intent();
        MainActivity target = startActivity(intent, null, null);
        assertEquals("MainActivity#getState should return 0", 0, target.getState());
        getInstrumentation().callActivityOnStart(target);
        assertEquals("MainActivity#getState should return 1", 1, target.getState());
        getInstrumentation().callActivityOnResume(target);
        assertEquals("MainActivity#getState should return 2", 2, target.getState());
    }
/*
    public void testButton() throws Exception {
        Intent intent = new Intent();
        MainActivity activity = startActivity(intent, null, null);
        final Button button = (Button)activity.findViewById(com.example.androiduitest.R.id.send);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        Intent target = getStartedActivityIntent();
        String ret = target.getComponent().getClassName();
        assertEquals(SecondAndroidActivity.class.getName(), ret);
            
        int request_code = getStartedActivityRequest();
        assertEquals(100, request_code);
    }
    */
}
