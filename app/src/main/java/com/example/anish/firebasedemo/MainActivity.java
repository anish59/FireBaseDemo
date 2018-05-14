package com.example.anish.firebasedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anish.firebasedemo.fireBaseTuts.model.Info;
import com.example.anish.firebasedemo.helper.NotificationUtils;
import com.example.anish.firebasedemo.helper.PrefUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.pubnub.api.Callback;
import com.pubnub.api.PnGcmMessage;
import com.pubnub.api.PnMessage;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import org.json.JSONException;
import org.json.JSONObject;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private TextView textPushMessage, txtRegId;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private String TAG = MainActivity.class.getSimpleName();
    private Context context = this;
    private Button btnSendPubNubNotification;
    private Pubnub pubnub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this);
        setContentView(R.layout.activity_main);
        pubnub = new Pubnub(getString(R.string.com_pubnub_publishKey), getString(R.string.com_pubnub_subscribeKey));

        initViews();
        init();
        enablePushNotification();
        initListeners();
    }

    private void initListeners() {
        btnSendPubNubNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPushNotification();
            }
        });


    }

    public static Callback callback = new Callback() {
        @Override
        public void successCallback(String channel, Object message) {
            Log.i("SuccessTag", "Success on Channel " + AppConstant.CHANNEL_NAME + " : " + message);
        }

        @Override
        public void errorCallback(String channel, PubnubError error) {
            Log.i("errTag", "Error On Channel " + AppConstant.CHANNEL_NAME + " : " + error);
        }
    };


    public void enablePushNotification() {
        pubnub.enablePushNotificationsOnChannel(AppConstant.CHANNEL_NAME, FirebaseInstanceId.getInstance().getToken(), new Callback() {
            @Override
            public void successCallback(String chanel, Object response) {
                super.successCallback(chanel, response);
                Log.e(TAG, "enablePushNotificationsOnChannel successCallback: " + chanel);
                Log.e(TAG, "enablePushNotificationsOnChannel successCallback: " + response);

            }

            @Override
            public void errorCallback(String s, PubnubError pubnubError) {
                super.errorCallback(s, pubnubError);
                Log.e(TAG, "enablePushNotificationsOnChannel errorCallback: " + s);
                Log.e(TAG, "enablePushNotificationsOnChannel errorCallback: " + pubnubError);

            }
        });
    }

    //    public Map<String, Object> createmessage(String messageType, String messageId) {
//        JSONObject obj = new JSONObject();
//        try {
//            obj.put("messageType", messageType);
//            obj.put("senderID", SharedPref.getInstance().getInt(SharedConstants.USER_ID) + "");
//            obj.put("content", messagestring);
//            obj.put("type", contentType);
//            obj.put("userName", data.getName());
//            obj.put("messageId", messageId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        byte[] encodeddata1 = Base64.encode(obj.toString().getBytes(), Base64.NO_WRAP);
//        String data = new String(encodeddata1);
//
//        Map<String, Object> messagepayload = new HashMap<>();
//        messagepayload.put("message", notification().toString());
//        Map<String, Object> datapayload = new HashMap<>();
//        datapayload.put("data", messagepayload);
//        Map<String, Object> mobilePayload = new HashMap<>();
//
//        mobilePayload.put("pn_gcm", datapayload);
//        mobilePayload.put("pn_other", data);
//        mobilePayload.put("pn_debug", true);
//        Log.e("published message", mobilePayload.toString());
//        return mobilePayload;
//    }
    private void init() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // checking for type intent filter
                if (intent.getAction().equals(AppConstant.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(AppConstant.TOPIC_GLOBAL);

                    displayFireBaseRegId();

                } else if (intent.getAction().equals(AppConstant.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    textPushMessage.setText(message);
                }
            }

        };
        displayFireBaseRegId();
    }

    private void displayFireBaseRegId() {

        if (!TextUtils.isEmpty(PrefUtils.getRegId(context)))
            txtRegId.setText("FireBase Reg Id: " + PrefUtils.getRegId(context));
        else
            txtRegId.setText("FireBase Reg Id is not received yet!");
    }


    private void initViews() {
        txtRegId = (TextView) findViewById(R.id.txt_reg_id);
        textPushMessage = (TextView) findViewById(R.id.txt_push_message);
        btnSendPubNubNotification = (Button) findViewById(R.id.btnSendPubNubNotification);
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

    void sendPushNotification() {
        PnGcmMessage gcmMessage = new PnGcmMessage();
        JSONObject jso = new JSONObject();
        Info info = new Info("01", "Anish", "001");
        try {
            jso.put("title", "hi");
            jso.put("message", "Blah blah blah!!");
            jso.put("InfoData", new Gson().toJson(info));
            jso.put("image", "https://static.comicvine.com/uploads/original/11126/111264223/4978219-4440144695-21085.jpg");
        } catch (JSONException e) {
        }
        gcmMessage.setData(jso);

        PnMessage message = new PnMessage(pubnub, AppConstant.CHANNEL_NAME, callback, gcmMessage);

        try {
            message.publish();
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }
}
