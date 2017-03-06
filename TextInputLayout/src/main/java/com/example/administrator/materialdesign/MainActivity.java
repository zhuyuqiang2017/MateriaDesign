package com.example.administrator.materialdesign;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout mUserNameInput,mPasswordInput;
    String EMAIL_ADDRESS = "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
    Pattern mPattern = Pattern.compile(EMAIL_ADDRESS);
    private Matcher mMatcher;
    private CountDownTimer mTimer = new CountDownTimer(5000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            if(mUserNameInput != null){
//                mUserNameInput.setError(null);
                mUserNameInput.setErrorEnabled(false);
            }
            if(mPasswordInput != null){
                mPasswordInput.setErrorEnabled(false);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserNameInput = (TextInputLayout)findViewById(R.id.layout_1);
        mPasswordInput = (TextInputLayout)findViewById(R.id.layout_2);

    }

    public void onLogin(View view){
        hideKeyBroad();;//隐藏输入键盘
        if (!isValidateEmailAddress(mUserNameInput.getEditText().getText().toString())){
            mUserNameInput.setError("Invalidate Email Address");
            mTimer.start();
        }else if (!isValidatePassword(mPasswordInput.getEditText().getText().toString())){
            mPasswordInput.setError("Invalidate Password");
            mTimer.start();
        }else{
            doLogin();
        }
    }

    private void hideKeyBroad(){
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(getCurrentFocus() != null){
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void doLogin(){
        Toast.makeText(MainActivity.this,"执行登录操作",Toast.LENGTH_LONG).show();
    }

    public void onCancel(View view){
        Toast.makeText(MainActivity.this,"取消登录",Toast.LENGTH_LONG).show();
    }

    //根据正则表达式制定的规则，对输入的字符串进行验证
    private boolean isValidateEmailAddress(CharSequence email){
        mMatcher = mPattern.matcher(email);
       return mMatcher.matches();
    }

    private boolean isValidatePassword(CharSequence password){
        return password.length()>5;
    }
}
