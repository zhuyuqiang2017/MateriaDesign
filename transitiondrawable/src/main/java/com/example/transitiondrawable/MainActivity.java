package com.example.transitiondrawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        TransitionDrawable transitionDrawable = (TransitionDrawable) getResources().getDrawable(R.drawable.transition_drawable, null);
        tv.setBackground(transitionDrawable);
        transitionDrawable.startTransition(3000);

        Resources res = getResources();
        TransitionDrawable imageTransitionDrawable = new TransitionDrawable(new Drawable[]{res.getDrawable(R.drawable.third,null),
                res.getDrawable(R.drawable.fourth,null)});
        iv = (ImageView)findViewById(R.id.iv);
        iv.setImageDrawable(imageTransitionDrawable);
        imageTransitionDrawable.startTransition(3000);

        Drawable a = iv.getDrawable();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(iv.getDrawable() instanceof TransitionDrawable){
                    TransitionDrawable transitionDrawable = (TransitionDrawable) iv.getDrawable();
                    final Drawable first = transitionDrawable.getDrawable(1);
                    TransitionDrawable btnTransitionDrawable = new TransitionDrawable(new Drawable[]{first,getResources().getDrawable(R.drawable.fifth)});
                    iv.setImageDrawable(btnTransitionDrawable);
                    btnTransitionDrawable.startTransition(5000);
                }else{
                    final Drawable first = iv.getDrawable();
                    TransitionDrawable btnTransitionDrawable = new TransitionDrawable(new Drawable[]{first,getResources().getDrawable(R.drawable.fifth)});
                    iv.setImageDrawable(btnTransitionDrawable);
                    btnTransitionDrawable.startTransition(5000);
                }
            }
        });
    }
}
