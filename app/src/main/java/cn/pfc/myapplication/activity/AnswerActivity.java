package cn.pfc.myapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.myapplication.R;
import cn.pfc.myapplication.adapter.LVAnswerAdapter;
import cn.pfc.myapplication.bean.AnswerBean;
import cn.pfc.myapplication.util.StringUtil;

public class AnswerActivity extends AppCompatActivity {

    List<AnswerBean> list;
    List<ViewHoler> listHolder;
    private Button submit;


    List<String> answerList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        initView();

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> ansList = new ArrayList<>();
                for(int i = 0;i<listHolder.size();i++)
                {
                    RadioButton rb = findViewById(listHolder.get(i).rg.getCheckedRadioButtonId());
                    if(rb!=null)
                    {

                        String ans = rb.getText().toString();
                        if(ans!=null)
                        {
                            ansList.add(ans);
                        }
                    }
                }
                if(ansList.size()!=10)
                {
                            Toast.makeText(AnswerActivity.this,
                                    "请答完题目",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int count = 0;
                    for(int i = 0;i<ansList.size();i++)
                    {
                        if(ansList.get(i).equals(answerList.get(i)))
                        {
                            count++;
                        }
                    }
                    String var = "";
                    if(count>6)
                        var = "再接再厉哟";
                    else
                        var = "加油呀";
                    AlertDialog.Builder builder = new AlertDialog.Builder(AnswerActivity.this)
                            .setMessage(var)
                            .setPositiveButton("你答对了"+count+"道题", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    builder.create().show();
//                    Toast.makeText(AnswerActivity.this,
//                            "你答对了"+count+"道题",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {

        listHolder = new ArrayList<>();

        ViewHoler viewHoler1 = new ViewHoler();
        viewHoler1.tv = findViewById(R.id.textView1);
        viewHoler1.r0 = findViewById(R.id.radioButton10);
        viewHoler1.r1 = findViewById(R.id.radioButton11);
        viewHoler1.r2 = findViewById(R.id.radioButton12);
        viewHoler1.r3 = findViewById(R.id.radioButton13);
        viewHoler1.rg = findViewById(R.id.radioGroup1);


        ViewHoler viewHoler2 = new ViewHoler();
        viewHoler2.tv = findViewById(R.id.textView2);
        viewHoler2.r0 = findViewById(R.id.radioButton20);
        viewHoler2.r1 = findViewById(R.id.radioButton21);
        viewHoler2.r2 = findViewById(R.id.radioButton22);
        viewHoler2.r3 = findViewById(R.id.radioButton23);
        viewHoler2.rg = findViewById(R.id.radioGroup2);

        ViewHoler viewHoler3 = new ViewHoler();
        viewHoler3.tv = findViewById(R.id.textView3);
        viewHoler3.r0 = findViewById(R.id.radioButton30);
        viewHoler3.r1 = findViewById(R.id.radioButton31);
        viewHoler3.r2 = findViewById(R.id.radioButton32);
        viewHoler3.r3 = findViewById(R.id.radioButton33);
        viewHoler3.rg = findViewById(R.id.radioGroup3);

        ViewHoler viewHoler4 = new ViewHoler();
        viewHoler4.tv = findViewById(R.id.textView4);
        viewHoler4.r0 = findViewById(R.id.radioButton40);
        viewHoler4.r1 = findViewById(R.id.radioButton41);
        viewHoler4.r2 = findViewById(R.id.radioButton42);
        viewHoler4.r3 = findViewById(R.id.radioButton43);
        viewHoler4.rg = findViewById(R.id.radioGroup4);

        ViewHoler viewHoler5 = new ViewHoler();
        viewHoler5.tv = findViewById(R.id.textView5);
        viewHoler5.r0 = findViewById(R.id.radioButton50);
        viewHoler5.r1 = findViewById(R.id.radioButton51);
        viewHoler5.r2 = findViewById(R.id.radioButton52);
        viewHoler5.r3 = findViewById(R.id.radioButton53);
        viewHoler5.rg = findViewById(R.id.radioGroup5);


        ViewHoler viewHoler6 = new ViewHoler();
        viewHoler6.tv = findViewById(R.id.textView6);
        viewHoler6.r0 = findViewById(R.id.radioButton60);
        viewHoler6.r1 = findViewById(R.id.radioButton61);
        viewHoler6.r2 = findViewById(R.id.radioButton62);
        viewHoler6.r3 = findViewById(R.id.radioButton63);
        viewHoler6.rg = findViewById(R.id.radioGroup6);


        ViewHoler viewHoler7 = new ViewHoler();
        viewHoler7.tv = findViewById(R.id.textView7);
        viewHoler7.r0 = findViewById(R.id.radioButton70);
        viewHoler7.r1 = findViewById(R.id.radioButton71);
        viewHoler7.r2 = findViewById(R.id.radioButton72);
        viewHoler7.r3 = findViewById(R.id.radioButton73);
        viewHoler7.rg = findViewById(R.id.radioGroup7);



        ViewHoler viewHoler8 = new ViewHoler();
        viewHoler8.tv = findViewById(R.id.textView8);
        viewHoler8.r0 = findViewById(R.id.radioButton80);
        viewHoler8.r1 = findViewById(R.id.radioButton81);
        viewHoler8.r2 = findViewById(R.id.radioButton82);
        viewHoler8.r3 = findViewById(R.id.radioButton83);
        viewHoler8.rg = findViewById(R.id.radioGroup8);


        ViewHoler viewHoler9 = new ViewHoler();
        viewHoler9.tv = findViewById(R.id.textView9);
        viewHoler9.r0 = findViewById(R.id.radioButton90);
        viewHoler9.r1 = findViewById(R.id.radioButton91);
        viewHoler9.r2 = findViewById(R.id.radioButton92);
        viewHoler9.r3 = findViewById(R.id.radioButton93);
        viewHoler9.rg = findViewById(R.id.radioGroup9);



        ViewHoler viewHolerX = new ViewHoler();
        viewHolerX.tv = findViewById(R.id.textViewX);
        viewHolerX.r0 = findViewById(R.id.radioButtonX0);
        viewHolerX.r1 = findViewById(R.id.radioButtonX1);
        viewHolerX.r2 = findViewById(R.id.radioButtonX2);
        viewHolerX.r3 = findViewById(R.id.radioButtonX3);
        viewHolerX.rg = findViewById(R.id.radioGroupX);



        listHolder.add(viewHoler1);
        listHolder.add(viewHoler2);
        listHolder.add(viewHoler3);
        listHolder.add(viewHoler4);
        listHolder.add(viewHoler5);
        listHolder.add(viewHoler6);
        listHolder.add(viewHoler7);
        listHolder.add(viewHoler8);
        listHolder.add(viewHoler9);
        listHolder.add(viewHolerX);
        parserJSON();


    }

    private void parserJSON() {
        list = new ArrayList<>();
        answerList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(StringUtil.question);
            for(int i = 0;i<jsonArray.length();i++)
            {
                JSONObject obj  =  jsonArray.getJSONObject(i);
                AnswerBean bean = new AnswerBean();
                bean.setAnswer( obj.getString("answer"));
                bean.setQuestio(obj.getString("question"));
                List<String> option = new ArrayList<>();

                JSONArray arr = obj.getJSONArray("option");
                for (int j = 0; j <arr.length() ; j++) {
                    option.add(arr.getString(j));
                }
                bean.setOption(option);

                listHolder.get(i).tv.setText(i+1+". "+bean.getQuestio());
                if(bean.getOption().size()==2)
                {
                    listHolder.get(i).r2.setVisibility(View.GONE);
                    listHolder.get(i).r3.setVisibility(View.GONE);
                    listHolder.get(i).r0.setText(option.get(0));
                    listHolder.get(i).r1.setText(option.get(1));
                }
                else if(bean.getOption().size()==3)
                {
                    listHolder.get(i).r3.setVisibility(View.GONE);
                    listHolder.get(i).r0.setText(option.get(0));
                    listHolder.get(i).r1.setText(option.get(1));
                    listHolder.get(i).r2.setText(option.get(2));
                }
                else if(bean.getOption().size()==4)
                {
                    listHolder.get(i).r0.setText(option.get(0));
                    listHolder.get(i).r1.setText(option.get(1));
                    listHolder.get(i).r2.setText(option.get(2));
                    listHolder.get(i).r3.setText(option.get(3));
                }

                answerList.add(obj.getString("answer"));
                list.add(bean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

class ViewHoler
{
    TextView tv;
    RadioButton r0,r1,r2,r3;
    RadioGroup rg;
}
