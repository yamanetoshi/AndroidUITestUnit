package com.example.androiduitest.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androiduitest.*;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
	private Activity activity;
	private Instrumentation instrumentation;

	public MainActivityTest() {
		super(MainActivity.class);
	}

	public void testEditText_initialize() throws Exception {
        EditText edit = (EditText)activity.findViewById(com.example.androiduitest.R.id.editer);
        TextView result = (TextView)activity.findViewById(com.example.androiduitest.R.id.result);
        assertEquals("EditText should be blank", "", edit.getText().toString());
        assertEquals("TextView should be blank", "", result.getText());
    }

	@Override
	protected void setUp() throws Exception {
		super.setUp();

        activity = getActivity();
        instrumentation = getInstrumentation();
	}
	
    public void testEditText_checkInput() throws Exception {
        final EditText edit = (EditText)activity.findViewById(com.example.androiduitest.R.id.editer);
        final Button button = (Button)activity.findViewById(com.example.androiduitest.R.id.send);
        TextView result = (TextView)activity.findViewById(com.example.androiduitest.R.id.result);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edit.requestFocus(); // EditTextにフォーカスを当てる
            }
        });
        instrumentation.waitForIdleSync();
        // sendKeysで文字列を入力する
        sendKeys(KeyEvent.KEYCODE_F);
        sendKeys(KeyEvent.KEYCODE_O);
        sendKeys(KeyEvent.KEYCODE_X);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });
        instrumentation.waitForIdleSync();
        assertEquals("入力された値は正しい","fox", result.getText().toString());
    }
    
    public void testEditText_checkButtonClick() throws Exception {
        EditText edit = (EditText)activity.findViewById(com.example.androiduitest.R.id.editer);
        Button button = (Button)activity.findViewById(com.example.androiduitest.R.id.send);
        TextView result = (TextView)activity.findViewById(com.example.androiduitest.R.id.result);
        TouchUtils.clickView(this, edit);
        sendKeys(KeyEvent.KEYCODE_C);
        sendKeys(KeyEvent.KEYCODE_O);
        sendKeys(KeyEvent.KEYCODE_W);
        TouchUtils.clickView(this, button);
        assertEquals("ボタンがクリックされた時の値のチェック", "cow", result.getText().toString());
    }
}
