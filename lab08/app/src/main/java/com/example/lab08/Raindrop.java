package com.example.lab08;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class Raindrop extends androidx.appcompat.widget.AppCompatImageView {
    public Raindrop(Context context) {
        super(context);
        setImageResource(R.drawable.raindrops2);
        AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable();
        animationDrawable.start();
    }
}
