package cn.pfc.myapplication;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.myapplication.adapter.ViewPagerAdapter;
import cn.pfc.myapplication.bean.Instance;
import cn.pfc.myapplication.fragment.MyFragment;
import cn.pfc.myapplication.fragment.ZiXunFragment;
import cn.pfc.myapplication.fragment.ZouShiFragment;
import cn.pfc.myapplication.http.HttpThread;

public class MainActivity extends AppCompatActivity {

    private ViewPager MainViewPager;
    private TextView[] textViews = new TextView[3];

    private LinearLayout main;


    List<Fragment> list;
    private TextView MainTextViewTime;

    ZiXunFragment ziXunFragment;
    ZouShiFragment zouShiFragment;
    MyFragment myFragment;


    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = (LinearLayout) findViewById(R.id.main);
        main.setVisibility(View.GONE);
        initData();
        requestData();
//        try {
//            Thread.currentThread().sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        initView();

        initViewPager();
        MainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int j) {


                if(j!=0)
                {
                    main.setVisibility(View.VISIBLE);
                }
                else
                {
                    main.setVisibility(View.GONE);
                }
                for(int i = 0;i<textViews.length;i++)
                {

                    if(i==j)
                    {
                        textViews[i].setTextColor(Color.RED);
                    }
                    else
                    {
                        textViews[i].setTextColor(Color.BLACK);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i==2)
                {
//                    myFragment.refreshView(Instance.getResponseData().getCityData());
                }
            }
        });
    }

    private void initData() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//                if(msg.what==0)
//                {
                    MainTextViewTime.setText(Instance.getResponseData().getUpdateTime());
                    zouShiFragment.drawAllLine(Instance.getResponseData().getHistoryList());
                    zouShiFragment.drawWuHanLine(Instance.getResponseData().getHistoryList());

//                }
            }
        };
    }

    private void requestData() {
        HttpThread htt = new HttpThread(this);
        htt.setURL("https://gwpre.sina.cn/interface/fymap2020_data.json?random=0.9268957923795904&_=1582259706740&callback=blankCallBack")
                .setJson(new JSONObject())
                .setOnRequest(new HttpThread.OnRequest() {
                    @Override
                    public void onOk(String jsonObject, boolean isOK)  {

                        String [] ff =   jsonObject.split("\n");
                        for(int i = 0;i<ff.length;i++)
                        {
                            Log.e("sssssssss"+i,ff[i]);
                        }

                        JSONObject json = null;
                        try {
                            Log.e("vbbbb",ff[2]);
                            json = new JSONObject(ff[4]);
                            Instance.getResponseData().setJsonObject(json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                        Log.e("logloglog","ssssssssss");
                    }
                }).run();
    }

    private void initViewPager() {
        list = new ArrayList<>();

        ziXunFragment = new ZiXunFragment();
        zouShiFragment = new ZouShiFragment();
        myFragment  = new MyFragment();



        list.add(ziXunFragment );
        list.add(zouShiFragment);
        list.add(myFragment  );
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),list);
        MainViewPager.setAdapter(adapter);
        MainViewPager.setCurrentItem(0);
    }

    private void initView() {
        MainViewPager = (ViewPager) findViewById(R.id.MainViewPager);
        textViews[0] = (TextView) findViewById(R.id.MaintextViewZiXun);
        textViews[1] = (TextView) findViewById(R.id.MaintextViewZouShi);
        textViews[2] = (TextView) findViewById(R.id.MaintextViewMy);
        MainTextViewTime = (TextView) findViewById(R.id.MainTextViewTime);

        textViews[0].setTextColor(Color.RED);
    }
    private boolean isFirstRun()
    {
        return true;
    }
}
