package com.example.vectordrawable;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.iv);
    }
    public void startAnimator(View view){
        Drawable d = iv.getDrawable();
        if (d instanceof AnimatedVectorDrawable){
            ((AnimatedVectorDrawable) d).start();
        }
    }
}
