package com.rilintech.fragment_301_huxike_android.activity;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.rilintech.fragment_301_huxike_android.R;
import com.rilintech.fragment_301_huxike_android.listener.BackGestureListener;
import com.rilintech.fragment_301_huxike_android.utils.SystemBarTintManager;


public class BaseActivity extends Activity {

    /**
     * 手势监听
     */
    GestureDetector mGestureDetector;
    /**
     * 是否需要监听手势关闭功能
     */
    private boolean mNeedBackGesture = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            // 创建状态栏的管理实例  
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏设置  
            tintManager.setStatusBarTintEnabled(true);
            // 激活导航栏设置  
            //tintManager.setNavigationBarTintEnabled(true);

            // 设置一个颜色给系统栏  
            //tintManager.setTintColor(Color.parseColor("#56abe4"));
            tintManager.setTintColor(R.color.theme_background);
            // 设置一个样式背景给导航栏  
            // tintManager.setNavigationBarTintResource(R.drawable.actionbar_background);
            // 设置一个状态栏资源  
            // tintManager.setStatusBarTintDrawable(MyDrawable);
            //tintManager.setStatusBarTintResource(R.drawable.bg_xfselected);
            tintManager.setStatusBarTintColor(0xff56abe4);
        }

        initGestureDetector();
    }


    private void initGestureDetector() {
        if (mGestureDetector == null) {
            mGestureDetector = new GestureDetector(getApplicationContext(),
                    new BackGestureListener(BaseActivity.this));
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mNeedBackGesture) {
            return mGestureDetector.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    /*
     * 设置是否进行手势监听
     */
    public void setNeedBackGesture(boolean mNeedBackGesture) {
        this.mNeedBackGesture = mNeedBackGesture;
    }


    /*
         * 返回
         */
    public void doBack(View view) {
        onBackPressed();
    }
}
