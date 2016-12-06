package com.tisen.note.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tisen.note.model.User;
import com.tisen.note.utils.BaseApiListener;
import com.tisen.note.utils.BaseUiListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by tisen on 2016/11/10.
 */
public class LoginActivity extends BaseActivity {

    private Tencent tencent;
    public final static String APP_ID = "1105727545";
    private UserInfo userInfo;
    private IUiListener listener = new IUiListener(){

        @Override
        public void onComplete(Object o) {
            Log.d("onComplete",o.toString());
            JSONObject jsonObject = (JSONObject) o;
            try {

                String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
                String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
                String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);

                BmobUser.BmobThirdUserAuth bmobThirdUserAuth = new BmobUser.BmobThirdUserAuth(BmobUser.BmobThirdUserAuth.SNS_TYPE_QQ
                        ,token,expires,openId);
                User user = new User();
                user.loginWithAuthData(bmobThirdUserAuth, new LogInListener<JSONObject>() {
                    @Override
                    public void done(JSONObject jsonObject, BmobException e) {

                    }
                });

                Log.d("json",token+expires+openId+jsonObject.toString());
                tencent.setAccessToken(token,expires);
                tencent.setOpenId(openId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            userInfo = new UserInfo(LoginActivity.this,tencent.getQQToken());
            userInfo.getUserInfo(listener);
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    };
    private IUiListener mListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            Log.d("MonComplete",o.toString());
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tencent = Tencent.createInstance(APP_ID,this.getApplicationContext());
        login();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode,resultCode,data,mListener);
    }
    private void login(){
        if(!tencent.isSessionValid()){
            tencent.login(this,"all",listener);
        }
    }

}
