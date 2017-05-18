package com.example.administrator.materialdesign;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class SafeService extends Service {
    private final int NOTIFICATION_ID = 99;
    private boolean isForeService = false;
    private boolean isActive = false;
    private MyService mMyService = new MyService();
    private Notification mNotification;
    @Override
    public IBinder onBind(Intent intent) {
        return mMyService;
    }
    class MyService extends Binder{
        public SafeService getSafeService(){
            return SafeService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        isActive = true;
        return super.onStartCommand(intent, flags, startId);
    }

    public boolean getForServiceStatus(){
        return isForeService;
    }

    public void setServiceAsBackService(){
        SafeService.this.stopForeground(true);
        isForeService = false;
    }

    public void setServiceAsForeService(){
        Log.i("zyq","setServiceAsForeService");
        mNotification = getNotification();
        SafeService.this.startForeground(NOTIFICATION_ID,mNotification);
        isForeService = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isActive = false;
    }

    private Notification getNotification(){
        Notification.Builder mBuilder = new Notification.Builder(SafeService.this);
        mBuilder.setSmallIcon(Icon.createWithResource(SafeService.this,R.drawable.ic_security_black_24dp));
        mBuilder.setContentTitle(getString(R.string.notification_title));
        mBuilder.setContentText(getString(R.string.notification_content));
        PendingIntent pendingIntent = PendingIntent.getActivity(SafeService.this,0,new Intent(SafeService.this,SafeSettingActivity.class),0);
        mBuilder.setContentIntent(pendingIntent);
        Notification.BigTextStyle mStyle = new Notification.BigTextStyle();
        mStyle.bigText(getString(R.string.notification_big_content));
        mBuilder.setStyle(mStyle);
        return mBuilder.build();
    }
}
