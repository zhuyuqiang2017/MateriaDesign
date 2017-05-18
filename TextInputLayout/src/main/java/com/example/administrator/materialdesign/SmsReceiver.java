package com.example.administrator.materialdesign;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class SmsReceiver extends BroadcastReceiver {
    private String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(SMS_ACTION)){

        }else{

        }
    }
}
