package com.example.administrator.materialdesign;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class RegisterActivity extends Activity{

    private EditText mUser;
    private EditText mPassword;
    private EditText mPasswordAgain;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private AlertDialog mDescDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUser = (EditText) findViewById(R.id.register_new_user);
        mPassword = (EditText) findViewById(R.id.register_pwd);
        mPasswordAgain = (EditText) findViewById(R.id.register_pwd_again);
        mSharedPreferences = getSharedPreferences(SafeUtils.KEY_PRE_NAME,MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void onRegisterEnsure(View view){
        final String mUserName = mUser.getText().toString().trim();
        final String mPassWord = mPassword.getText().toString().trim();
        final String mPassWordAgain = mPasswordAgain.getText().toString().trim();
        if(mPassWord.length()>5 && mPassWordAgain.length()>5 && mPassWord.equals(mPassWordAgain)){
            mEditor.putString(SafeUtils.KEY_USER_NAME,mUserName);
            mEditor.putString(SafeUtils.KEY_PASS_WORD,mPassWord);
            mEditor.commit();
            Toast.makeText(RegisterActivity.this,"用户注册成功",Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(RegisterActivity.this,"密码无效",Toast.LENGTH_LONG).show();
        }
    }
    public void onRegisterCancel(View view){
        finish();
    }
    public void onRegisterDesc(View view){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegisterActivity.this);
        View mMainView = LayoutInflater.from(RegisterActivity.this).inflate(R.layout.desc_layout,null);
        ImageView close = (ImageView) mMainView.findViewById(R.id.desc_dialog_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDescDialog != null){
                    mDescDialog.dismiss();
                }
            }
        });
        mMainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDescDialog != null){
                    mDescDialog.dismiss();
                }
            }
        });
        mBuilder.setView(mMainView);
        mBuilder.setCancelable(true);
        mDescDialog = mBuilder.create();
        mDescDialog.show();
    }
}
