package com.rilintech.fragment_301_huxike_android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rilintech.fragment_301_huxike_android.R;
import com.rilintech.fragment_301_huxike_android.bean.PatientInfo;
import com.rilintech.fragment_301_huxike_android.db.PatientInfoManager;


/**
 * Created by zsn on 15/10/27.
 */
public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText accountEdit;// 用户名
    private EditText passwordEdit;// 密码
    private Button login;// 登陆按钮
    //private CheckBox rememberPass;// 记住密码
    //private CheckBox denglu;// 自动登陆
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    private ImageView visible,invisible;
    private AlertDialog dialog;
    private PatientInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initView();
        initListener();

        shared = getSharedPreferences("users", MODE_PRIVATE);
        editor = shared.edit();

        // 设置自动登录默认为不点击
        /*Boolean value = shared.getBoolean("auto_ischecked", false);
        denglu.setChecked(value);

        // 从SharedPreferences里边取出 记住密码的状态
        if(shared.getBoolean("ischecked",false)){
            //将记住密码设置为被点击状态
            rememberPass.setChecked(true);
            accountEdit.setText(shared.getString("user_name", ""));
            passwordEdit.setText(shared.getString("user_pwd", ""));
        }
        if(shared.getBoolean("auto_ischecked",false)){
        denglu.setChecked(true);
        }
        //如果记住密码，并且自动登陆
        if(rememberPass.isChecked()&&denglu.isChecked()){
            login();
        }*/

    }

    /**
     * 各种监听
     */
    private void initListener() {
        login.setOnClickListener(this);
        /*rememberPass.setOnClickListener(this);
        denglu.setOnClickListener(this);*/
    }

    /**
     * 初始化
     */
    private void initView() {
        accountEdit=(EditText)findViewById(R.id.account_et);
        passwordEdit=(EditText)findViewById(R.id.password_et);
        login=(Button)findViewById(R.id.btn_login);
        /*rememberPass=(CheckBox)findViewById(R.id.checkBox_remeber_pwd);
        denglu=(CheckBox)findViewById(R.id.checkBox_auto_login);*/
        visible = (ImageView) findViewById(R.id.visible);
        invisible = (ImageView) findViewById(R.id.invisible);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //登陆
            case R.id.btn_login:
                login();
            break;
            //记住密码
            /*case R.id.checkBox_remeber_pwd:
                Boolean isChecked_remeber_pwd = rememberPass.isChecked();
                editor.putBoolean("ischecked", isChecked_remeber_pwd);
                editor.commit();
            break;
            //自动登陆
            case R.id.checkBox_auto_login:
                Boolean isChecked_auto_login = denglu.isChecked();
                editor.putBoolean("auto_ischecked", isChecked_auto_login);
                editor.commit();
            break;*/

            default:
                break;
        }
    }

    /**
     * 登陆
     */
    private void login() {
       AlertDialog.Builder builder = new AlertDialog.Builder (LoginActivity.this);
        View view1 = getLayoutInflater().inflate(R.layout.login_dialog_layout, null);
        TextView title = (TextView) view1.findViewById(R.id.title);
        title.setText("登录中...");
        LinearLayout ll = (LinearLayout)view1.findViewById(R.id.ll);
        ll.setVisibility(View.GONE);
        builder.setCancelable(false);
        dialog = builder.show();
        dialog.getWindow().setContentView(view1);
        //模拟耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Looper.prepare();
                    // 将信息存入到users里面
                    String user_name = accountEdit.getText().toString();
                    String user_pwd=passwordEdit.getText().toString();
                    
                    if(TextUtils.isEmpty(user_name)){
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(TextUtils.isEmpty(user_pwd)){
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_LONG).show();
                        return;
                    }

                    Thread.sleep(2000);
                    if ("admin".equals(user_name)&& "admin".equals(user_pwd)) {
                        dialog.dismiss();
                        //保存信息
                        editor.putString("user_name", user_name);
                        editor.putString("user_pwd", user_pwd);
                        editor.commit();
                        //创建一个空白的患者对象
                        insertPatientInfo();
                        // 创建Intent对象，传入源Activity和目的Activity的类对象
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    } else {
                        dialog.dismiss();
                        // 登录信息错误，通过Toast显示提示信息
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Looper.loop();
                }
            }
        }).start();



    }
    /**
     * 密码可见
     */
    public void visible(View view){
        passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        passwordEdit.postInvalidate();
        visible.setVisibility(View.VISIBLE);
        invisible.setVisibility(View.GONE);
    }

    /**
     * 密码不可见
     */
    public void invisible(View view){
        passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        passwordEdit.postInvalidate();
        invisible.setVisibility(View.VISIBLE);
        visible.setVisibility(View.GONE);
    }
    //第一次启动插入一个空白的患者对象
    private void insertPatientInfo(){

        PatientInfoManager manager = new PatientInfoManager(this);
        manager.openDataBase();
        info = manager.patientInfo();
        if (info == null) {
            info = new PatientInfo();
            info.setName("");
            info.setSex("");
            info.setAge("");
            info.setBedNO("");
            info.setIdentity_card("");
            info.setHeight("");
            info.setWeight("");
            info.setPhone("");
            info.setZip_code("");
            info.setAddress("");
            info.setCompany("");
            info.setHospital("");
            info.setDescribe("");

            manager.addPatientInfo(info);
        }else {

        }
        manager.closeDataBase();

    }



}
