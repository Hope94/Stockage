package com.example.nesrine.stockage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nesrine on 16/04/2017.
 */

public class SharedExample {
    private Context context;

    public SharedExample(Context context) {
        this.context = context;
    }

    public boolean saveConnectUser(String userName)
    {
        SharedPreferences pref=context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean("connected",true);
        editor.putString("username",userName);
        return editor.commit();
    }
    public  boolean isConnected(){
        SharedPreferences pref=context.getSharedPreferences("user",Context.MODE_PRIVATE);
        return  pref.getBoolean("connected",false);

    }
    public  String getUserConnected() {
        SharedPreferences pref = context.getSharedPreferences("user", Context.MODE_PRIVATE);

        return pref.getString("username", null);

    }


}
