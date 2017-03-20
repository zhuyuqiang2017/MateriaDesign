package com.example.shapeandgradientdrawable;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/18 0018.
 */

public class ShapeActivity extends Activity {

    private TextView tv;
    ShapeDrawable drawable;
    private int index = 0;
    private boolean isTimerRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_layout);
//        tv = (TextView)findViewById(R.id.tv);
/*        drawable = (ShapeDrawable) tv.getBackground();
        drawable.setShape(new Shape() {
            @Override
            public void draw(Canvas canvas, Paint paint) {
                canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),paint);
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
