package com.example.lab05;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LifecycleData {
    int onCreate = 0;
    int onStart = 0;
    int onResume = 0;
    int onPause = 0;
    int onStop = 0;
    int onRestart = 0;
    int onDestroy = 0;
    String duration;
    public String toString(){
        return duration + "\n"+
                "onCreate  \t" + onCreate + "\n"+
                "onStart  \t" + onStart + "\n"+
                "onResume  \t" + onResume + "\n"+
                "onPause  \t" + onPause + "\n"+
                "onStop  \t" + onStop + "\n"+
                "onRestart  \t" + onRestart + "\n"+
                "onDestroy  \t" + onDestroy;
    }
    String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this, LifecycleData.class);
    }
    static LifecycleData parseJSON(String fromSharedPreferences){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(fromSharedPreferences, LifecycleData.class);
    }

    public void updateEvent(String currentEnclosingMethod) {
        switch(currentEnclosingMethod){
            case "onCreate":
                onCreate++;
                break;
            case "onStart":
                onStart++;
                break;
            case "onResume":
                onResume++;
                break;
            case "onPause":
                onPause++;
                break;
            case "onStop":
                onStop++;
                break;
            case "onRestart":
                onRestart++;
                break;
            case "onDestroy":
                onDestroy++;
                break;
            default:break;
        }
    }
}
