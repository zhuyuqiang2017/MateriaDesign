package com.example.shapeandgradientdrawable;

import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GradientActivity extends AppCompatActivity {

    private TextView tv;
    GradientDrawable drawable;
    private int index = 0;
    GradientDrawable.Orientation[] orientations;
    private boolean isTimerRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gradient_layout);
        tv = (TextView)findViewById(R.id.tv);
        drawable = (GradientDrawable) tv.getBackground();
        drawable.setLevel(10000);
        orientations = GradientDrawable.Orientation.values();
        Log.i("zyq","orientation = "+orientations.length);
    }

    public void startDrawableAnimation(View view){
        if(isTimerRunning){
            timer.cancel();
            timer.onFinish();
        }else{
            if (drawable instanceof GradientDrawable){
                timer.start();
            }
        }
    }

    private CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE,20) {
        @Override
        public void onTick(long millisUntilFinished) {
            isTimerRunning = true;
            if (index<8){
                drawable.setOrientation(orientations[index]);
                index ++;
            }else{
                drawable.setOrientation(orientations[0]);
                index = 1;
            }

        }

        @Override
        public void onFinish() {
            isTimerRunning = false;
        }
    };
}
