package com.example.lab05;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.SeekBar;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String TAG = "com.example.lab05.sharedpreferences";
    LifecycleData currentRun, lifeTime;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView currentRunTV, lifeTimeTV;

    SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        currentRun = new LifecycleData();
        currentRun.duration = "Current Run";

        String lifecycleDataAsString = sharedPreferences.getString("lifetime", "");
        if(lifecycleDataAsString.equals("")){
            lifeTime = new LifecycleData();
            lifeTime.duration = "Lifetime";
        }
        else{
            lifeTime = LifecycleData.parseJSON(lifecycleDataAsString);
        }
        lifeTimeTV = findViewById(R.id.lifetime);
        currentRunTV = findViewById(R.id.current);
        String currentEnclosingMethod = new Throwable().getStackTrace()[0].getMethodName();
        updateCount(currentEnclosingMethod);

        seekbar = findViewById(R.id.seekBar);


        seekbar.setProgress(30);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               currentRunTV.setTextSize(i);
                lifeTimeTV.setTextSize(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                lastProgress = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "FontSize Change To " + seekBar.getProgress()+" sp",Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                seekbar.setProgress(lastProgress);
                                currentRunTV.setTextSize(lastProgress);
                                lifeTimeTV.setTextSize(lastProgress);
                                Snackbar.make(findViewById(android.R.id.content), "Font Size Reverted Back to " + lastProgress + "sp", Snackbar.LENGTH_LONG).show();
                            }
                        });
                snackbar.setActionTextColor(Color.BLUE);
                View snackBarView = snackbar.getView();
                snackbar.show();
            }
        });
    }

    private void displayData() {
        lifeTimeTV.setText(lifeTime.toString());
        currentRunTV.setText(currentRun.toString());
    }

    public void storeData(){
        editor.putString("lifetime", lifeTime.toJSON()).apply();

    }
    public void updateCount(String currentEnclosingMethod){
        currentRun.updateEvent(currentEnclosingMethod);
        lifeTime.updateEvent(currentEnclosingMethod);
        displayData();
        storeData();
    }
    @Override
    protected void onStart(){
        super.onStart();
        String currentEnclosingMethod = new Throwable().getStackTrace()[0].getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onResume(){
        super.onResume();
        String currentEnclosingMethod = new Throwable().getStackTrace()[0].getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onPause(){
        super.onPause();
        String currentEnclosingMethod = new Throwable().getStackTrace()[0].getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onStop(){
        super.onStop();
        String currentEnclosingMethod = new Throwable().getStackTrace()[0].getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        String currentEnclosingMethod = new Throwable().getStackTrace()[0].getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String currentEnclosingMethod = new Throwable().getStackTrace()[0].getMethodName();
        updateCount(currentEnclosingMethod);
    }


}
