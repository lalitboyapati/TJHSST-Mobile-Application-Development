package com.example.finallab07;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmentcontainer, FragmentB.newInstance(99,"RulerOfTheWorld"), "FragmentB");
        ft.commit();
    }

    int count = 0;
    public void update_greeting(View view){
        count = 0;
        count+=1;
        FragmentB fragmentB = (FragmentB)getSupportFragmentManager().findFragmentByTag("FragmentB");
        if (count==1) {
            ((TextView) fragmentB.view.findViewById(R.id.btextview)).setText(R.string.greeting2);
        }
        if (count==2) {
            ((TextView) fragmentB.view.findViewById(R.id.btextview)).setText(R.string.greeting3);
        }
        if (count==3) {
            ((TextView) fragmentB.view.findViewById(R.id.btextview)).setText(R.string.greeting4);
        }
        if (count==4) {
            ((TextView) fragmentB.view.findViewById(R.id.btextview)).setText(R.string.greeting5);
        }
    }
}
