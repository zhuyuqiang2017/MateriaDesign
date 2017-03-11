package com.example.picturedrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private PictureDrawable pictureDrawable;
    private int width;
    private int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.iv);
    }
    private void initPictureDrawable(){

        Picture picture = new Picture();
        width = iv.getMinimumWidth();
        height = iv.getMinimumHeight();
        Canvas c = picture.beginRecording(width,height);
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        c.drawCircle(width/2,height/2,200,p);
        picture.endRecording();
        pictureDrawable = new PictureDrawable(picture);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initPictureDrawable();
    }

    public void setImageViewBg(View view){
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.first),0,0,null);
        pictureDrawable.setBounds(0,0,width,height);
        pictureDrawable.draw(canvas);//直接调用不会绘制任何东西到canvas上的，必须先设置好bounds
//        canvas.drawPicture(pictureDrawable.getPicture());
        iv.setImageBitmap(bitmap);
    }

}
