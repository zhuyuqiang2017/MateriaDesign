package com.example.clipandpaintdrawable;

import android.graphics.drawable.ClipDrawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private static final int CHANGE_LEVEL = 99;
    private ImageView iv;
    private ClipDrawable mClipDrawable;
    private boolean isExpand = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        mClipDrawable = (ClipDrawable) iv.getDrawable();
        mClipDrawable.setLevel(10);
    }

    public void changeImageView(View view) {
        if (!isExpand) {
            isExpand = true;
            final Handler mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == CHANGE_LEVEL) {
                        int level = mClipDrawable.getLevel() + 50;
                        if (level >= 10000) {
                            level = 10000;
                        }
                        mClipDrawable.setLevel(level);
                    }
                }
            };
            final CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 10) {
                @Override
                public void onTick(long millisUntilFinished) {

                    if (mClipDrawable.getLevel() >= 10000) {
                        this.onFinish();
                    } else {
                        mHandler.sendEmptyMessage(99);
                    }

                }

                @Override
                public void onFinish() {
                    isExpand = false;
                }
            };
            timer.start();
        }
    }

    public void changeImage(View view) {
        if (iv.getDrawable() != null) {
            iv.setBackground(iv.getDrawable());
        }
        final ClipDrawable imageDrawable = new ClipDrawable(getResources().getDrawable(R.drawable.second), Gravity.TOP | Gravity.START, ClipDrawable.VERTICAL);
        iv.setImageDrawable(imageDrawable);
        imageDrawable.setLevel(10);
        if (!isExpand) {
            isExpand = true;
            final Handler mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == CHANGE_LEVEL) {
                        int level = imageDrawable.getLevel() + 50;
                        if (level >= 10000) {
                            level = 10000;
                        }
                        imageDrawable.setLevel(level);
                    }
                }
            };
            final CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 10) {
                @Override
                public void onTick(long millisUntilFinished) {

                    if (imageDrawable.getLevel() >= 10000) {
                        this.onFinish();
                    } else {
                        mHandler.sendEmptyMessage(99);
                    }

                }

                @Override
                public void onFinish() {
                    isExpand = false;
                }
            };
            timer.start();
        }
    }

}
