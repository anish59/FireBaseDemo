package com.example.anish.firebasedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anish.firebasedemo.helper.NotificationUtils;
import com.example.anish.firebasedemo.helper.PrefUtils;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private TextView textPushMessage, txtRegId;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private String TAG = MainActivity.class.getSimpleName();
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        init();
    }

    private void init() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // checking for type intent filter
                if (intent.getAction().equals(AppConstant.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(AppConstant.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(AppConstant.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    textPushMessage.setText(message);
                }
            }

        };
        displayFirebaseRegId();
    }

    private void displayFirebaseRegId() {

        if (!TextUtils.isEmpty(PrefUtils.getRegId(context)))
            txtRegId.setText("FireBase Reg Id: " + PrefUtils.getRegId(context));
        else
            txtRegId.setText("FireBase Reg Id is not received yet!");
    }


    private void initViews() {
        txtRegId = (TextView) findViewById(R.id.txt_reg_id);
        textPushMessage = (TextView) findViewById(R.id.txt_push_message);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(AppConstant.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(AppConstant.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}
