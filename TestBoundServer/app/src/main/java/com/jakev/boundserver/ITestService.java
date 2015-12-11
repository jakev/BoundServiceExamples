package com.jakev.boundserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.jakev.boundserver.aidl.TestInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ITestService extends Service {

    private static final String TAG = "ITestService";

    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG, "onBind() called!");

        return new TestInterface.Stub() {

            @Override
            public String exec(String cmd)
                    throws RemoteException {

                if (cmd == null) {
                    Log.e(TAG, "cmd is required!");
                    return null;
                }

                return execCommand(cmd);
            };
        };
    }

    private String execCommand(String cmd) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String response = output.toString();
        return response;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
