package com.rilintech.fragment_301_huxike_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.rilintech.fragment_301_huxike_android.R;
import com.rilintech.fragment_301_huxike_android.view.ProgressWebView;


/**
 * @Description:WebView界面，带自定义进度条显示
 * @author rilintech
 */ 
public class BaseWebActivity extends BaseActivity {

	protected ProgressWebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baseweb);

		mWebView = (ProgressWebView) findViewById(R.id.baseweb_webview);
		initData();
	}

	protected void initData() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String url = bundle.getString("url");

		 if(!TextUtils.isEmpty(url)){
		mWebView.loadUrl(url);
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mWebView = null;

	}

	/**
	 * 返回键
	 * @param view
	 */
	public void onClick(View view){
		this.finish();
	}

}
