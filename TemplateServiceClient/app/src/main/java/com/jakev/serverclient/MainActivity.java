package com.jakev.serverclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.jakev.boundserver.aidl.TestInterface;

/* EDIT: Import your AIDL here */
//import com.your.app.aidl.TestInterface;

public class MainActivity extends Activity {

    private static final String TAG = "ServiceClient";

    /* EDIT: Add your service object here. */
    //TestInterface service;

    MyServiceConnection mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize the connection */
        initService();

    }

    /** Unbinds this activity from the service. */
    private void releaseService() {
        unbindService(mConnection);
        mConnection = null;
        Log.d(TAG, "releaseService() unbound.");
    }

    /** Binds this activity to the service. */
    private void initService() {
        mConnection = new MyServiceConnection();
        Intent i = new Intent();

        /* EDIT: Modify the application and class name here */
        //i.setClassName("com.your.app", "com.your.app.Service");

        boolean ret = bindService(i, mConnection, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "initService() bound with " + ret);
    }


    /** Called when the activity is about to be destroyed. */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }

    /**
     * This class represents the actual service connection. It casts the bound
     * stub implementation of the service to the AIDL interface.
     */
    class MyServiceConnection implements ServiceConnection {

        public void onServiceConnected(ComponentName name, IBinder boundService) {

            /* EDIT: Replace with new AIDL class */
            //service = TestInterface.Stub.asInterface((IBinder) boundService);
            Log.d(TAG, "onServiceConnected() : OK ");

            /* Do your transactions here. */
            try {
                /* EDIT: Do your transactions here. */
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnect()");
            service = null;
        }
    };
}
