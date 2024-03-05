package com.example.lab08;

// Raindrop.java
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class raindrops extends androidx.appcompat.widget.AppCompatImageView{
    public raindrops(Context context, Drawable drawable) {
        super(context);
        setImageDrawable(drawable);
    }
}
