package com.tisen.note;

import android.content.Context;
import android.renderscript.Type;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tisen.note.bean.Person;
import com.tisen.note.bean.School;
import com.tisen.note.utils.XMLRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tisen on 2016/11/1.
 */
public class Juhe {

    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 10000;
    public static final int DEF_READ_TIMEOUT = 10000;
    public static final String APPKEY = "8213d12a880a68dcf6dcea2902881259";
    public static String url = "http://op.juhe.cn/onebox/weather/query";

    public void connect(Context context) {
        Map params = new HashMap();//请求参数
        params.put("cityname", "上海");//要查询的城市，如：温州、上海、北京
        params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype", "xml");//返回数据的格式,xml或json，默认json

        final XMLRequest request = new XMLRequest(url + "?" + urlencode(params), new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onErrorResponse", error.toString(), error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    private void onPostExecute(JSONObject result) {
        if (result != null) {

        }

    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
