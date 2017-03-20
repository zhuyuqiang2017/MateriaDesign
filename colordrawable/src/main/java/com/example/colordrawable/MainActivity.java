package com.example.colordrawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ColorDrawable drawable;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawable = (ColorDrawable) getDrawable(R.drawable.color_drawable);
        iv = (ImageView)findViewById(R.id.iv);
    }

    public void setColorDrawable(View view){
        Bitmap b = Bitmap.createBitmap(iv.getWidth(),iv.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(b);
        drawable.setBounds(iv.getWidth()/2-50,iv.getHeight()/2-50,iv.getWidth()/2+50,iv.getHeight()/2+50);
        drawable.draw(canvas);
        iv.setImageBitmap(b);
    }
}
