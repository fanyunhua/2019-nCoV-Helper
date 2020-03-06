package cn.pfc.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.pfc.myapplication.R;

public class ZouShiFragment extends Fragment {

    private LineChart ZouShiAll;
    private LineChart ZouShiWuHan;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_zoushi_fragment,null);
        initView(view);
        setChartProperty();
        return view;
    }

    private void setChartProperty() {
        ZouShiAll.setDescription("全国疫情累计趋势");
        Legend legend = ZouShiAll.getLegend();  //获取图例
        legend.setTextSize(10f);
        legend.setFormSize(10f);  //设置图例大小
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT); //设置图例位置
        XAxis xAxis = ZouShiAll.getXAxis(); //设置x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //设置x轴在下方显示
        ZouShiAll.getAxisRight().setEnabled(false);


        ZouShiWuHan.setDescription("武汉疫情累计趋势");
        Legend legend1 = ZouShiWuHan.getLegend();  //获取图例
        legend1.setTextSize(10f);
        legend1.setFormSize(10f);  //设置图例大小
        legend1.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT); //设置图例位置
        XAxis xAxis1 = ZouShiWuHan.getXAxis(); //设置x轴
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);  //设置x轴在下方显示
        ZouShiWuHan.getAxisRight().setEnabled(false);

    }

    private void initView(View view) {
        ZouShiAll = (LineChart) view.findViewById(R.id.ZouShiAll);
        ZouShiWuHan = (LineChart) view.findViewById(R.id.ZouShiWuHan);
    }


    public  void drawWuHanLine(JSONArray array)
    {
        List<String> titles = new ArrayList<>();
        List<Entry> conNumList = new ArrayList<>();
        List<Entry> deathNumList = new ArrayList<>();
        List<Entry> cureNumList = new ArrayList<>();
        List<Entry> susNumList = new ArrayList<>();
        int j = 0;
        for (int i = array.length()-1;i>=0;i--)
        {
            try {
                JSONObject json =  array.getJSONObject(i);
                titles.add(json.getString("date"));
                conNumList.add(new Entry(json.getInt("wuhan_conNum"),j));
                deathNumList.add(new Entry(json.getInt("wuhan_deathNum"),j));
                cureNumList.add(new Entry(json.getInt("wuhan_cureNum"),j));
                susNumList.add(new Entry(json.getInt("wjw_susNum"),j));
                j++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LineDataSet  conNumDataSet =   new LineDataSet(conNumList ,"确诊");
        conNumDataSet.setColor(Color.RED);
        conNumDataSet.setCircleColor(Color.RED);
        conNumDataSet.setCircleColorHole(Color.RED);
        LineDataSet  deathNumDataSet = new LineDataSet(deathNumList,"死亡");
        deathNumDataSet.setColor(Color.DKGRAY);
        deathNumDataSet.setCircleColor(Color.DKGRAY);
        deathNumDataSet.setCircleColorHole(Color.DKGRAY);
        LineDataSet  cureNumDataSet =  new LineDataSet(cureNumList ,"治愈");
        cureNumDataSet.setColor(Color.GREEN);
        cureNumDataSet.setColor(Color.GREEN);
        cureNumDataSet.setCircleColorHole(Color.GREEN);
        LineDataSet  susNumDataSet =   new LineDataSet(susNumList ,"疑似");
        susNumDataSet.setColor(Color.YELLOW);
        susNumDataSet.setCircleColor(Color.YELLOW);
        susNumDataSet.setCircleColorHole(Color.YELLOW);

        List<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(conNumDataSet );
        dataSets.add(deathNumDataSet);
        dataSets.add(cureNumDataSet );
        dataSets.add(susNumDataSet );
        LineData lineData = new LineData(titles, dataSets);  //设置x轴和数据

        ZouShiWuHan.setData(lineData);
        ZouShiWuHan.invalidate();
    }
    public void drawAllLine(JSONArray array)
    {
        List<String> titles = new ArrayList<>();
        List<Entry> conNumList = new ArrayList<>();
        List<Entry> deathNumList = new ArrayList<>();
        List<Entry> cureNumList = new ArrayList<>();
        List<Entry> susNumList = new ArrayList<>();
        int j = 0;
        for (int i = array.length()-1;i>=0;i--)
        {
            try {
                JSONObject json =  array.getJSONObject(i);
                titles.add(json.getString("date"));
                conNumList.add(new Entry(json.getInt("cn_conNum"),j));
                deathNumList.add(new Entry(json.getInt("cn_deathNum"),j));
                cureNumList.add(new Entry(json.getInt("cn_cureNum"),j));
                susNumList.add(new Entry(json.getInt("cn_susNum"),j));
                j++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LineDataSet  conNumDataSet =   new LineDataSet(conNumList ,"确诊");
        conNumDataSet.setColor(Color.RED);
        conNumDataSet.setCircleColor(Color.RED);
        conNumDataSet.setCircleColorHole(Color.RED);
        LineDataSet  deathNumDataSet = new LineDataSet(deathNumList,"死亡");
        deathNumDataSet.setColor(Color.DKGRAY);
        deathNumDataSet.setCircleColor(Color.DKGRAY);
        deathNumDataSet.setCircleColorHole(Color.DKGRAY);
        LineDataSet  cureNumDataSet =  new LineDataSet(cureNumList ,"治愈");
        cureNumDataSet.setColor(Color.GREEN);
        cureNumDataSet.setColor(Color.GREEN);
        cureNumDataSet.setCircleColorHole(Color.GREEN);
        LineDataSet  susNumDataSet =   new LineDataSet(susNumList ,"疑似");
        susNumDataSet.setColor(Color.YELLOW);
        susNumDataSet.setCircleColor(Color.YELLOW);
        susNumDataSet.setCircleColorHole(Color.YELLOW);

        List<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(conNumDataSet );
        dataSets.add(deathNumDataSet);
        dataSets.add(cureNumDataSet );
        dataSets.add(susNumDataSet );
        LineData lineData = new LineData(titles, dataSets);  //设置x轴和数据

        ZouShiAll.setData(lineData);
        ZouShiAll.invalidate();
    }
}
