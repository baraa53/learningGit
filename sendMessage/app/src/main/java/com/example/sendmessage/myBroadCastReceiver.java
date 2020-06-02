package com.example.sendmessage;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class myBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("hi");
        String number=intent.getStringExtra("sms");
        String message=intent.getStringExtra("message");
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    Manifest.permission.SEND_SMS)) {
                // Show an expanation to the user
            } else {

                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.SEND_SMS}, 1);

            }
        }
        else{
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(number,null,message,null,null);
        }

    }
}
