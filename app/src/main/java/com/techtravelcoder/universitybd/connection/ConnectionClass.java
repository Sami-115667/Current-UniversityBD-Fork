package com.techtravelcoder.universitybd.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.NetworkInterface;

public class ConnectionClass {

    public static boolean isConnectedToInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){
            NetworkInfo[] info =connectivityManager.getAllNetworkInfo();
            if(info != null){
                for(int i=0 ;i<info.length ;i++){
                    if(info[i].getState() == NetworkInfo.State.CONNECTED){
                        return  true;
                    }
                }
            }

        }
        return false;
    }
}
