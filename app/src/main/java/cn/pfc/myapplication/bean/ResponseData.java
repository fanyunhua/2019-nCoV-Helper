package cn.pfc.myapplication.bean;

import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseData {
    private JSONObject jsonObject;


    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
    public JSONObject getJsonObject1() throws JSONException {

        JSONObject json = jsonObject.getJSONObject("data");
        return json;
    }


    public String getUpdateTime()
    {
        String data = null;
//        Log.e("error",this.jsonObject.toString());
        try {
            JSONObject json = jsonObject.getJSONObject("data");
            data = json.getString("times");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  data;
    }
    public JSONArray getHistoryList()
    {
        JSONArray jsonArray = null;
        try {
            JSONObject json = jsonObject.getJSONObject("data");
            jsonArray = json.getJSONArray("historylist");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return  jsonArray;
    }

    public  JSONArray getCityData()
    {
        JSONArray jsonArray = null;
        try {
            JSONObject json = jsonObject.getJSONObject("data");
            jsonArray = json.getJSONArray("list");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonArray;
    }

//    public JSONObject dt()
//    {
//        JSONObject jsonObject = null;
//        try {
//            JSONObject json = jsonObject.getJSONObject("data");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
}
