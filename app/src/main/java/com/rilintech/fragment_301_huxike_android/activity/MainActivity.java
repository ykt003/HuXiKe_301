package com.rilintech.fragment_301_huxike_android.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.rilintech.fragment_301_huxike_android.R;
import com.rilintech.fragment_301_huxike_android.fragment.FragmentPersonalCenter;
import com.rilintech.fragment_301_huxike_android.fragment.FragmentHome;
import com.umeng.update.UmengUpdateAgent;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    //个人中心
    private LinearLayout personalCenter;
    private ImageView iv_personal_center;
    private TextView tv_personal_center;
    private FragmentPersonalCenter fragmentPersonalCenter;
    //首页
    private LinearLayout home;
    private ImageView iv_home;
    private TextView tv_home;
    private FragmentHome fragmentHome;
    //当前选中页标记
    public static int INTERFACE = 0;
    //标题
    private TextView tv_title;
    //返回键
    private ImageView iv_back;
    //上滑进入首页
    private TextView tvHint;
    //返回键按下时间
    private long mExitTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //友盟更新服务
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);

        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {

        //设置pull_door_text
        tvHint = (TextView) findViewById(R.id.tv_hint);
        Animation ani = new AlphaAnimation(0f, 1f);
        ani.setDuration(1500);
        ani.setRepeatMode(Animation.REVERSE);
        ani.setRepeatCount(Animation.INFINITE);
        tvHint.startAnimation(ani);

        personalCenter = (LinearLayout) findViewById(R.id.ll_personal_center);
        iv_personal_center = (ImageView) findViewById(R.id.iv_personal_center);
        tv_personal_center = (TextView) findViewById(R.id.tv_personal_center);
        home = (LinearLayout) findViewById(R.id.ll_home);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_title = (TextView) findViewById(R.id.layout_title).findViewById(R.id.tv_title);
        iv_back = (ImageView) findViewById(R.id.layout_title).findViewById(R.id.iv_back);
        iv_back.setVisibility(View.GONE);
        personalCenter.setOnClickListener(this);
        home.setOnClickListener(this);

        setDefaultFragment();
    }

    /**
     * 设置默认的显示界面
     */
    private void setDefaultFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentHome = new FragmentHome();
        transaction.replace(R.id.id_content, fragmentHome);
        INTERFACE = 1;
        setBottomBarStyle(INTERFACE);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.ll_personal_center:
                if (fragmentPersonalCenter == null) {
                    fragmentPersonalCenter = new FragmentPersonalCenter();
                }
                transaction.replace(R.id.id_content, fragmentPersonalCenter);
                INTERFACE = 2;
                setBottomBarStyle(INTERFACE);
                break;
            case R.id.ll_home:
                if (fragmentHome == null) {
                    fragmentHome = new FragmentHome();
                }
                transaction.replace(R.id.id_content, fragmentHome);
                INTERFACE = 1;
                setBottomBarStyle(INTERFACE);
                break;
            case R.id.iv_back:

                break;
        }
        transaction.commit();

    }

    /**
     * 改变标题和bottomBar选中效果
     * @param b
     */
    private void setBottomBarStyle(int b){

        switch (b){
            case 1:
                iv_home.setImageResource(R.drawable.ic_home_pressed);
                tv_home.setTextColor(getResources().getColor(R.color.mBlue));
                iv_personal_center.setImageResource(R.drawable.ic_personal_center_normal);
                tv_personal_center.setTextColor(getResources().getColor(R.color.subscribe_item_normal_stroke));
                tv_title.setText(getResources().getString(R.string.app_name));
                break;
            case 2:
                iv_home.setImageResource(R.drawable.ic_home_normal);
                tv_home.setTextColor(getResources().getColor(R.color.subscribe_item_normal_stroke));
                iv_personal_center.setImageResource(R.drawable.ic_personal_center_pressed);
                tv_personal_center.setTextColor(getResources().getColor(R.color.mBlue));
                tv_title.setText(getResources().getString(R.string.personal_center));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "在按一次退出",
                        Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }else {
                this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                this.finish();
            }
            return true;
        }
        //拦截MENU按钮点击事件，让他无任何操作
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }





}
