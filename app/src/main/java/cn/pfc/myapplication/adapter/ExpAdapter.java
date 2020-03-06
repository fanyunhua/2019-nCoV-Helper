package cn.pfc.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cn.pfc.myapplication.R;
import cn.pfc.myapplication.bean.DataBean;

public class ExpAdapter extends BaseExpandableListAdapter {

    Context context;
    HashMap<DataBean,List<DataBean>> dataMap;
    List<DataBean> parent;
    public ExpAdapter(Context context, HashMap<DataBean, List<DataBean>> dataMap, List<DataBean> parent)
    {
        this.context = context;
        this.dataMap = dataMap;
        this.parent = parent;
    }
    @Override
    public int getGroupCount() {
        return dataMap.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataMap.get(parent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataMap.get(parent.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_exp_parent,null);
        LinearLayout Explayout = view.findViewById(R.id.Explayout);
        Explayout.setBackgroundColor(0xFC0FFFFF);
        TextView itemTextViewCity;
        TextView itemTextViewAdd;
        TextView itemTextViewQueZhen;
        TextView itemTextViewZhiYu;
        TextView itemTextViewSiWang;

        itemTextViewCity = (TextView) view.findViewById(R.id.itemTextViewCity);
        itemTextViewAdd = (TextView) view.findViewById(R.id.itemTextViewAdd);
        itemTextViewQueZhen = (TextView) view.findViewById(R.id.itemTextViewQueZhen);
        itemTextViewZhiYu = (TextView) view.findViewById(R.id.itemTextViewZhiYu);
        itemTextViewSiWang = (TextView) view.findViewById(R.id.itemTextViewSiWang);

        DataBean data =  this.parent.get(groupPosition);

        itemTextViewCity.setText(data.getCity());
        itemTextViewAdd.setText(data.getZengjia());
        itemTextViewQueZhen.setText(data.getQuezhen());
//        itemTextViewZhiYu.setText(data.getZhiyu());
//        itemTextViewSiWang.setText(data.getSiwang());

        itemTextViewZhiYu.setText(data.getSiwang());
        itemTextViewSiWang.setText(data.getZhiyu());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_exp_parent,null);


        TextView itemTextViewCity;
        TextView itemTextViewAdd;
        TextView itemTextViewQueZhen;
        TextView itemTextViewZhiYu;
        TextView itemTextViewSiWang;

        itemTextViewCity = (TextView) view.findViewById(R.id.itemTextViewCity);
        itemTextViewAdd = (TextView) view.findViewById(R.id.itemTextViewAdd);
        itemTextViewQueZhen = (TextView) view.findViewById(R.id.itemTextViewQueZhen);
        itemTextViewZhiYu = (TextView) view.findViewById(R.id.itemTextViewZhiYu);
        itemTextViewSiWang = (TextView) view.findViewById(R.id.itemTextViewSiWang);

        DataBean data =  dataMap.get(this.parent.get(groupPosition)).get(childPosition);

        itemTextViewCity.setText(data.getCity());
        itemTextViewAdd.setText(data.getZengjia());
        itemTextViewQueZhen.setText(data.getQuezhen());
//        itemTextViewZhiYu.setText(data.getZhiyu());
//        itemTextViewSiWang.setText(data.getSiwang());
        itemTextViewZhiYu.setText(data.getSiwang());
        itemTextViewSiWang.setText(data.getZhiyu());

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
