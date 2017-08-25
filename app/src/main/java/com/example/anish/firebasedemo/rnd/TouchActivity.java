package com.example.anish.firebasedemo.rnd;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.anish.firebasedemo.R;

/**
 * Created by anish on 04-08-2017.
 */

public class TouchActivity extends AppCompatActivity {
    private DemoClass demoClass;
    private Context context;
    private TextView txt_reg_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_reg_id=(TextView)findViewById(R.id.txt_reg_id);
        context=this;

//        txt_reg_id.setOnClickListener();

//        demoClass=new DemoClass(context, DemoClass.OnTouchListener);
    }
}
