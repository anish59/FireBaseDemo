package com.example.anish.firebasedemo;

/**
 * Created by anish on 20-07-2017.
 */

public class AppConstant {

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "ah_firebase";

    public static final String SENDER_ID = "1001";
    public static String PUB_KEY = "pub-c-252e60ab-5e75-4d6a-b5af-4b34517afacc";
    public static String SUB_KEY = "sub-c-86e368de-a367-11e7-af4e-0a68e4e3f63a";
    public static String CHANNEL_NAME = "Notify_channel";
}

