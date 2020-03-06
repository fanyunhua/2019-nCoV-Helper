package cn.pfc.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.pfc.myapplication.R;
import cn.pfc.myapplication.adapter.ExpAdapter;
import cn.pfc.myapplication.bean.DataBean;
import cn.pfc.myapplication.bean.Instance;

public class MyFragment extends Fragment {
    private TextView MyQueZhenTextView;
    private TextView MyYiSiTextView;
    private TextView MySiWangTextView;
    private TextView MyZhiYuTextView;
    private ExpandableListView MyExpandableListView;
    ExpAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.layout_my_fragment,null);
        initView(view);

        refreshView(Instance.getResponseData().getCityData());
        setData();


        return view;
    }

    private void setData() {
        try {
            MyQueZhenTextView.setText(Instance.getResponseData().getJsonObject1().getString("gntotal"));
            MySiWangTextView.setText(Instance.getResponseData().getJsonObject1().getString("deathtotal"));
            MyZhiYuTextView.setText(Instance.getResponseData().getJsonObject1().getString("curetotal"));
            MyYiSiTextView.setText(Instance.getResponseData().getJsonObject1().getString("sustotal"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initView(View view) {
        MyQueZhenTextView = (TextView) view.findViewById(R.id.MyQueZhenTextView);
        MyYiSiTextView = (TextView) view.findViewById(R.id.MyYiSiTextView);
        MySiWangTextView = (TextView) view.findViewById(R.id.MySiWangTextView);
        MyZhiYuTextView = (TextView) view.findViewById(R.id.MyZhiYuTextView);
        MyExpandableListView = (ExpandableListView) view.findViewById(R.id.MyExpandableListView);

    }

    public void refreshView(JSONArray arr)
    {

        HashMap<DataBean, List<DataBean>> dataMap = new HashMap<>();
        List<DataBean> parent = new ArrayList<>();
        for(int i = 0;i<arr.length();i++)
        {
            try {
                JSONObject json = arr.getJSONObject(i);
                DataBean pp = new DataBean();
                pp.setCity(json.getString("name"));
                pp.setQuezhen(json.getString("value"));
                pp.setZengjia(json.getString("conadd"));
                pp.setSiwang(json.getString("deathNum"));
                pp.setZhiyu(json.getString("cureNum"));
                JSONArray sub = json.getJSONArray("city");

                List<DataBean> subList = new ArrayList<>();
                for (int j = 0; j <sub.length() ; j++)
                {
                    JSONObject obj =sub.getJSONObject(j);
                    DataBean p = new DataBean();
                    p.setCity(obj.getString("name"));
                    p.setQuezhen(obj.getString("conNum"));
                    p.setZengjia(obj.getString("conadd"));
                    p.setSiwang(obj.getString("deathNum"));
                    p.setZhiyu(obj.getString("cureNum"));
                    subList.add(p);
                }
                dataMap.put(pp,subList);
                parent.add(pp);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            adapter = new ExpAdapter(getContext(),dataMap,parent);
            MyExpandableListView.setAdapter(adapter);


    }
}
