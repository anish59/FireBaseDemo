package com.example.anish.firebasedemo.fireBaseTuts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anish.firebasedemo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by anish on 24-08-2017.
 */

public class HomeActivity extends AppCompatActivity {
    private android.widget.TextView txtpushmessage;
    private android.widget.EditText edtValue;
    private android.widget.TextView txtregid;
    private android.widget.Button btnAdd;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);// only if you want to store data offline and then transfer it is online
        myRef = database.getReference("User");
        init();
        initListener();
    }

    private void initListener() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               myRef.removeValue();
               /*
                for inserting value
                String userId = myRef.push().getKey();
                myRef.child(userId).setValue("Person_test_" + userId);*/
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               /* This method is called once with the initial value and again
                whenever data at this location is updated.*/
//                String value = dataSnapshot.getValue(String.class);
//                Log.e("onDataChange", "Value is: " + value);
//                Toast.makeText(HomeActivity.this, "onDatachange: " + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.e("onCancelled", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void init() {
        this.txtregid = (TextView) findViewById(R.id.txt_reg_id);
        this.btnAdd = (Button) findViewById(R.id.btnAdd);
        this.edtValue = (EditText) findViewById(R.id.edtValue);
        this.txtpushmessage = (TextView) findViewById(R.id.txt_push_message);
    }
}
