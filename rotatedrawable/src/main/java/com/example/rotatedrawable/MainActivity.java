package com.example.rotatedrawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Drawable drawable;
    private final int UPDATE_VIEW = 99;
    private boolean IS_ROTATING = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.iv);
        drawable = iv.getDrawable();
        drawable.setLevel(0);
    }

    public void setRotateDrawableLevel(View view){
        if (drawable instanceof RotateDrawable){
//            drawable.setLevel(5000);
            IS_ROTATING = true;
            if(IS_ROTATING){
                timer.start();
            }
        }
    }

    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE,60) {
        @Override
        public void onTick(long millisUntilFinished) {
            mHandler.sendEmptyMessage(UPDATE_VIEW);
        }

        @Override
        public void onFinish() {
            IS_ROTATING = false;
        }
    };

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_VIEW:
                    int level = drawable.getLevel();
                    Log.i("zyq","level = "+level);
                    if (level >= 10000){
                        timer.cancel();

                        drawable.setLevel(10000);
                    }else{
                        drawable.setLevel(level+100);
                    }

                    break;
            }
        }
    };
}
