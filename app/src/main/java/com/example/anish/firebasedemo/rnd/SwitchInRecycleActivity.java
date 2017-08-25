package com.example.anish.firebasedemo.rnd;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anish.firebasedemo.R;
import com.example.anish.firebasedemo.rnd.adapter.SwitchAdapter;

public class SwitchInRecycleActivity extends AppCompatActivity {
    private RecyclerView rvSwitches;
    private SwitchAdapter switchAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_switch_in_recycle);
        initViews();
        initAdapter();
    }

    private void initAdapter() {
        switchAdapter = new SwitchAdapter(context);
        rvSwitches.setLayoutManager(new LinearLayoutManager(context));
        rvSwitches.setAdapter(switchAdapter);
    }

    private void initViews() {
        rvSwitches = (RecyclerView) findViewById(R.id.rvSwitches);
    }
}
