package com.example.lab09;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View {
    private List<Sprite> sprites;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        sprites = new ArrayList<>();
        sprites.add(new Sprite(50, 50, 160, 160, 3, 2, Color.GREEN));
        sprites.add(new Sprite(200, 200, 310, 310, -2, -1, Color.GREEN));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Sprite sprite : sprites) {
            sprite.update(getWidth(),getHeight());
            sprite.draw(canvas);
        }

        invalidate(); // Request a redraw
    }
}
