package com.example.administrator.materialdesign;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class SafeSettingActivity extends Activity {
    private Switch mSwitch;
    private TextView mSafeNumber;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private AlertDialog mDialog = null;
    private SafeService mSafeService;
    private boolean isActiveService = false;
    private boolean isBindedService = false;
    private boolean isForeService = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mSafeService = ((SafeService.MyService)service).getSafeService();
            isBindedService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBindedService = false;
        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        mSwitch = (Switch) findViewById(R.id.safe_switch);
        mSafeNumber = (TextView) findViewById(R.id.safe_number_id);
        mSharedPreferences = getSharedPreferences(SafeUtils.KEY_PRE_NAME,MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeSwitchViewStatus(isChecked);
            }
        });
    }

    private void changeSwitchViewStatus(boolean isChecked) {
        mEditor.putBoolean(SafeUtils.KEY_SAFE_STATUS,isChecked);
        mEditor.commit();
        if(!isChecked && isActiveService){
            stopService(new Intent(SafeSettingActivity.this,SafeService.class));
        }else{
            ensureStartSafeService();
        }
    }

    public void onModifySafeNumber(View view){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SafeSettingActivity.this);
        View mMainView = LayoutInflater.from(SafeSettingActivity.this).inflate(R.layout.setting_safe_number,null);
        final EditText mEditNumber = (EditText) mMainView.findViewById(R.id.dialog_number_content);
        final TextView mCancel = (TextView) mMainView.findViewById(R.id.dialog_safe_cancel);
        final TextView mEnsure = (TextView) mMainView.findViewById(R.id.dialog_safe_ensure);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                updateViewValue();
            }
        });
        mEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = mEditNumber.getText().toString().trim();
                if(number.length()>10){
                    mEditor.putString(SafeUtils.KEY_SAFE_NUMBER,number);
                    mEditor.commit();
                    mDialog.dismiss();
                    updateViewValue();
                }else{
                    Toast.makeText(SafeSettingActivity.this,"无效手机号",Toast.LENGTH_LONG).show();
                }
            }
        });
        mBuilder.setView(mMainView);
        mDialog = mBuilder.create();
        mDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void ensureStartSafeService(){
        if(mSharedPreferences.getBoolean(SafeUtils.KEY_SAFE_STATUS,false)){
            isActiveService = (startService(new Intent(SafeSettingActivity.this,SafeService.class)) == null);
            if(!isActiveService){
                new Throwable("can't start SafeService");
            }
            Log.i("zyq","isBindedService" + isBindedService);
            if(!isBindedService){
                bindService(new Intent(SafeSettingActivity.this,SafeService.class),mConnection, Context.BIND_AUTO_CREATE);
                Log.i("zyq","isBinded  " + bindService(new Intent(SafeSettingActivity.this,SafeService.class),mConnection, Context.BIND_AUTO_CREATE));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateViewValue();
        ensureStartSafeService();
        setServiceAsBackService();
    }

    private void setServiceAsBackService() {
        if (mSafeService != null){
            isForeService = mSafeService.getForServiceStatus();
            if(isForeService){
                mSafeService.setServiceAsBackService();
            }
        }
    }

    private void setServiceAsForeService() {
        Log.i("zyq","mSafeService != null" + (mSafeService != null));
        if (mSafeService != null){
            isForeService = mSafeService.getForServiceStatus();
            Log.i("zyq","isForeService" + isForeService);
            if(!isForeService){
                mSafeService.setServiceAsForeService();
            }
        }
    }

    private void updateViewValue(){
        mSafeNumber.setText(mSharedPreferences.getString(SafeUtils.KEY_SAFE_NUMBER,"187XXXXXXXX"));
        mSwitch.setChecked(mSharedPreferences.getBoolean(SafeUtils.KEY_SAFE_STATUS,false));
    }

    @Override
    protected void onPause() {
        super.onPause();
        setServiceAsForeService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBindedService){
            unbindService(mConnection);
        }
    }
}
