package com.example.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int gridSize = 4;
    private GridView gridView;
    private ImageAdapter adapter;
    private AppCompatButton resetButton;

    private ArrayList<Integer> selectedPositions = new ArrayList<>();
    private int matches = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);
        adapter = new ImageAdapter(this, gridSize * gridSize);
        gridView.setAdapter(adapter);

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectedPositions.size() < 2 && !selectedPositions.contains(position)) {
                    selectedPositions.add(position);
                    adapter.flipCard(position);
                    if (selectedPositions.size() == 2) {
                        checkMatch();
                    }
                }
            }
        });
    }

    private void checkMatch() {
        int pos1 = selectedPositions.get(0);
        int pos2 = selectedPositions.get(1);

        if (adapter.getItem(pos1) == adapter.getItem(pos2)) {
            matches++;
            if (matches == gridSize * gridSize / 2) {
                adapter.showWinningDialog();
                resetGame();
            }
        } else {
            gridView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.flipCard(pos1);
                    adapter.flipCard(pos2);
                }
            }, 1000);
        }

        selectedPositions.clear();
    }


    private void resetGame() {
        matches = 0;
        adapter.resetCards();
    }
}
