package com.tisen.note.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tisen.note.MainActivity;
import com.tisen.note.activity.LoginActivity;

import org.json.JSONObject;

/**
 * Created by tisen on 2016/11/10.
 */
public class BaseUiListener implements IUiListener {
    private Context context;
    private String scope;

    public BaseUiListener() {
    }

    public BaseUiListener(Context context, String scope) {
        this.context = context;
        this.scope = scope;
    }

    @Override
    public void onComplete(Object response) {
        doComplete((JSONObject)response);
    }
    protected void doComplete(JSONObject value){

    }

    @Override
    public void onError(UiError uiError) {
        Log.d("onError",uiError.toString());
    }

    @Override
    public void onCancel() {
        Log.d("onCancel","onCancel");
    }
}
