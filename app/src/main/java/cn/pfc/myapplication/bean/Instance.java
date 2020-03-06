package cn.pfc.myapplication.bean;

public class Instance {
    public static ResponseData responseData;

    public static ResponseData getResponseData() {
        if(responseData==null)
        {
            responseData = new ResponseData();
        }
        return  responseData;
    }
}
