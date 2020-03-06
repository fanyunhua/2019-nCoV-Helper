package cn.pfc.myapplication.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class HttpThread extends Thread{
    JSONObject json;
    String URL;
    RequestQueue mQueue;
    OnRequest onRequest;
    public HttpThread(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public HttpThread setOnRequest(OnRequest onRequest) {
        this.onRequest = onRequest;
        return this;
    }

    @Override
    public void run() {
        Log.d("JSONOBJECT",json.toString());
        Log.d("URL",URL.toString());
        StringRequest jsonObjectRequest = new StringRequest(
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String jsonObject) {
                        Log.d("sucss","");
                        if(onRequest!=null)
                        {
                            try {
                                onRequest.onOk(new String(jsonObject.getBytes(),"utf-8"),true);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("error",volleyError.toString());
                    }
                }
        );
        mQueue.add(jsonObjectRequest);
    }

    public HttpThread setJson(JSONObject json) {
        this.json = json;
        return this;
    }

    public HttpThread setURL(String URL) {
        this.URL = URL;
        return this;
    }
    public interface OnRequest{
        void onOk(String jsonObject, boolean isOK) throws JSONException;
    }
}
