package com.example.animatedvetectordrawable;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Drawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.iv);
    }
    public void startVectorAnimation(View view){
        drawable = iv.getDrawable();
        if (drawable instanceof AnimatedVectorDrawable){
            Log.i("zyq","start");
            ((AnimatedVectorDrawable) drawable).start();
        }
    }
}
