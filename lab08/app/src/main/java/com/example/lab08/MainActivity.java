package com.example.lab08;

// MainActivity.java
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = findViewById(R.id.container);
        // Create and add raindrops to the layout
        for (int i = 0; i < 10; i++) {
            raindrops raindrop = new raindrops(this, getResources().getDrawable(R.drawable.raindrops));
            container.addView(raindrop);
            animateRaindrop(raindrop);
        }
    }

    private void animateRaindrop(raindrops raindrop) {
        // Create an ObjectAnimator for vertical translation (falling animation)
        ObjectAnimator translateY = ObjectAnimator.ofFloat(raindrop, "translationY", -1000f, 2000f);
        translateY.setDuration(3000); // Adjust duration as needed

        // Create an ObjectAnimator for alpha (fade-out animation)
        ObjectAnimator alpha = ObjectAnimator.ofFloat(raindrop, "alpha", 1f, 0f);
        alpha.setDuration(3000); // Adjust duration as needed

        // Combine the animations using AnimatorSet
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateY, alpha);

        // Start the animation
        animatorSet.start();
    }
}