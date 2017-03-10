package com.example.paintdrawable;

import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private PaintDrawable bg_1;
    private PaintDrawable bg_2;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.iv);
        bg_1 = new PaintDrawable(Color.BLUE);
        bg_1.setCornerRadius(200.0f);
        bg_2 = new PaintDrawable(Color.RED);
        bg_2.setCornerRadii(new float[]{100,200,100,200,200,400,200,400});
        iv.setBackground(bg_1);
    }

    public void setImageViewBg(View view){
        iv.setBackground(bg_2);
    }
}
