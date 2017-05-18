package com.example.administrator.materialdesign;

import android.animation.Animator;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class LogInActivity extends Activity {
    private EditText mUser;
    private EditText mPassword;
    private String mUserName = null;
    private String mPassWord = null;
    private SharedPreferences mSharedPreferences;
    private AlertDialog mDescDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUser = (EditText) findViewById(R.id.user_edit_id);
        mPassword = (EditText) findViewById(R.id.pwd_edit_id);
        mSharedPreferences = getSharedPreferences(SafeUtils.KEY_PRE_NAME, Context.MODE_PRIVATE);
        mUserName = mSharedPreferences.getString(SafeUtils.KEY_USER_NAME,"");
        mPassWord = mSharedPreferences.getString(SafeUtils.KEY_PASS_WORD,"");
    }

    public void onRegisterNewUser(View view) {
        Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onLogInEnsure(View view) {
        String mLogInUser = mUser.getText().toString().trim();
        String mLogInPwd = mPassword.getText().toString().trim();
        Log.i("zyq","user = "+mLogInUser+", u = "+mUserName+", pass = "+mLogInPwd+" ,p = "+mPassWord);
        if(mLogInUser != null && mLogInPwd != null && (mLogInPwd.length()>5) && mUserName.equals(mLogInUser) && (mPassWord.equals(mLogInPwd))){
            Intent intent = new Intent(LogInActivity.this, SafeSettingActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(LogInActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
        }
    }

    public void onLogInCancel(View view) {
        finish();
    }

    public void onShowDescription(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LogInActivity.this);
        final View mMainView = LayoutInflater.from(LogInActivity.this).inflate(R.layout.desc_layout,null);
        ImageView close = (ImageView) mMainView.findViewById(R.id.desc_dialog_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDescDialog != null){
                    mDescDialog.dismiss();
                }
            }
        });
        mBuilder.setView(mMainView);
        mDescDialog = mBuilder.create();
        mDescDialog.show();
    }

    public void onFindPassword(View view){
        Toast.makeText(LogInActivity.this,"尚未实现 !!",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(mUser != null){
            mUser.setText(null);
        }

        if(mPassword != null){
            mPassword.setText(null);
        }
    }
}
