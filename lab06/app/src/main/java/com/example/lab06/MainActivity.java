package com.example.lab06;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    RecyclerView.Adapter mMyFragmentStateAdapter;
    int num_items = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.container);
        mMyFragmentStateAdapter = new MyFragmentStateAdapter(this);
        viewPager2.setAdapter(mMyFragmentStateAdapter);
    }
    private class MyFragmentStateAdapter extends FragmentStateAdapter {
        public MyFragmentStateAdapter(FragmentActivity fragmentActivity){
            super(fragmentActivity);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(viewPager2);
        }

        @Override
        public int getItemCount() {
            return num_items;
        }

    }
}
