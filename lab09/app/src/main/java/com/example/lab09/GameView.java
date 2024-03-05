package com.example.lab09;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
    private List<Sprite> sprites;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        sprites = new ArrayList<>();

        sprites.add(new Sprite(50, 50, 160, 160, 3, 2, Color.RED));
        sprites.add(new Sprite(200, 200, 310, 310, -2, -1, Color.BLUE));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Sprite sprite : sprites) {
            sprite.update(getWidth(),getHeight());
            sprite.draw(canvas);
        }

        checkCollisions();
        checkContainment();
    }

    private void checkCollisions() {
        for (int i = 0; i < sprites.size(); i++) {
            for (int j = i + 1; j < sprites.size(); j++) {
                if (RectF.intersects(sprites.get(i), sprites.get(j))) {
                    sprites.get(i).setColor(Color.YELLOW);
                    sprites.get(j).setColor(Color.YELLOW);
                }
            }
        }
    }

    private void checkContainment() {
        for (Sprite sprite : sprites) {
            if (!RectF.intersects(sprite, new RectF(0, 0, getWidth(), getHeight()))) {
                sprite.setdX(-sprite.getdX());
                sprite.setdY(-sprite.getdY());
            }
        }
    }
}
