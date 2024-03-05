package com.example.afinal;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ImageAdapter extends BaseAdapter {

    private final Context context;
    private final int numCards;
    private int[] images;
    private final boolean[] flippedCards;

    private int flippedCount = 0;
    private int firstFlippedPosition = -1;

    private boolean isFlippingAllowed = true;

    private int lastFlippedPosition;

    public ImageAdapter(Context context, int numCards) {
        this.context = context;
        this.numCards = numCards;
        this.images = new int[numCards / 2];
        this.flippedCards = new boolean[numCards];

        // Assign different images to each pair
        for (int i = 0; i < numCards / 2; i++) {
            images[i] = getUniqueImageResource(i);
        }

        // Duplicate the images to create pairs
        int[] duplicatedImages = new int[images.length * 2];
        System.arraycopy(images, 0, duplicatedImages, 0, images.length);
        System.arraycopy(images, 0, duplicatedImages, images.length, images.length);
        this.images = duplicatedImages;

        // Shuffle the array to randomize card positions
        shuffleImages();
    }

    // Helper method to get a unique image resource for each pair
    private int getUniqueImageResource(int pairIndex) {
        switch (pairIndex) {
            case 0:
                return R.drawable.image1;
            case 1:
                return R.drawable.image2;
            case 2:
                return R.drawable.image3;
            case 3:
                return R.drawable.image4;
            case 4:
                return R.drawable.image5;
            case 5:
                return R.drawable.image6;
            case 6:
                return R.drawable.image7;
            default:
                return R.drawable.image8;
        }
    }


    private void shuffleImages() {
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        int[] shuffledImages = new int[numCards];
        for (int i = 0; i < numCards; i++) {
            shuffledImages[i] = images[indices.get(i)];
        }

        System.arraycopy(shuffledImages, 0, images, 0, numCards);
    }

    @Override
    public int getCount() {
        return numCards;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(flippedCards[position] ? images[position] : android.R.drawable.ic_menu_info_details);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));

        if (isFlippingAllowed) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flipCard(position);
                }
            });
        }

        return imageView;
    }

    public void flipCard(int position) {
        if (!flippedCards[position] && flippedCount < 2) {
            flippedCards[position] = true;
            notifyDataSetChanged();
            flippedCount++;

            if (flippedCount == 1) {
                firstFlippedPosition = position;
            } else if (flippedCount == 2) {
                if (images[firstFlippedPosition] == images[position]) {
                    // Match found
                    flippedCount = 0;
                    firstFlippedPosition = -1;
                    checkGameCompletion();
                } else {
                    // No match, flip back the cards after a delay
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            flippedCards[firstFlippedPosition] = false;
                            flippedCards[position] = false;
                            notifyDataSetChanged();
                            flippedCount = 0;
                            firstFlippedPosition = -1;
                        }
                    }, 1000);
                }
            }
        }
    }

    private void checkGameCompletion() {
        int matchedPairs = 0;
        for (int i = 0; i < numCards; i += 2) {
            if (flippedCards[i] && flippedCards[i + 1]) {
                matchedPairs++;
            }
        }

        if (matchedPairs == numCards / 2) {
            showWinningDialog();
        }
    }

    void showWinningDialog() {
        Toast.makeText(context, "Congratulations! You win!", Toast.LENGTH_SHORT).show();
    }




    public void resetCards() {
        lastFlippedPosition = -1;
        for (int i = 0; i < numCards; i++) {
            flippedCards[i] = false;
        }
        shuffleImages();
        notifyDataSetChanged();
    }

}

