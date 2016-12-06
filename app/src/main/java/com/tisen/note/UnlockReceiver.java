package com.tisen.note;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by tisen on 2016/11/2.
 */
public class UnlockReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case Intent.ACTION_SCREEN_OFF:
                Log.d("onReceive","SCREEN_OFF");break;
            case Intent.ACTION_SCREEN_ON:
                Log.d("onReceive","SCREEN_ON");break;
            case Intent.ACTION_USER_PRESENT:
                Log.d("onReceive","USER_PRESENT");break;
        }
    }
}
