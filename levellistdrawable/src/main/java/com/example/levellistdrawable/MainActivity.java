package com.example.levellistdrawable;

import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private LevelListDrawable levelListDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.iv);
        levelListDrawable = (LevelListDrawable) iv.getDrawable();
    }

    public void setPicture(View view){
        int level = levelListDrawable.getLevel();
        if (level<1 || level>11){
            levelListDrawable.setLevel(2);
        } else{
            levelListDrawable.setLevel(level+2);
            if (levelListDrawable.getCurrent() instanceof TransitionDrawable){
                TransitionDrawable transitionDrawable = (TransitionDrawable) levelListDrawable.getCurrent();
                transitionDrawable.startTransition(3000);
            }
        }
    }

}
