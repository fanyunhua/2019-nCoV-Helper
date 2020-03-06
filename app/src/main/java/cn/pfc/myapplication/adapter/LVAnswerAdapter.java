package cn.pfc.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.pfc.myapplication.R;
import cn.pfc.myapplication.bean.AnswerBean;

public class LVAnswerAdapter extends BaseAdapter {

    List<AnswerBean> list;
    LayoutInflater layoutInflater;
    Context context;
    public LVAnswerAdapter(Context context,List<AnswerBean> list)
    {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = layoutInflater.inflate(R.layout.layout_answer,null);
        TextView textView;
        final RadioGroup radioGroup;
        RadioButton radioButton0;
        RadioButton radioButton1;
        RadioButton radioButton2;
        RadioButton radioButton3;

        textView = (TextView) view.findViewById(R.id.textView);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioButton0 = (RadioButton) view.findViewById(R.id.radioButton0);
        radioButton1 = (RadioButton) view.findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) view.findViewById(R.id.radioButton3);

        AnswerBean bean = list.get(position);
        textView.setText(bean.getQuestio());
        List<String> option = bean.getOption();
        if(bean.getOption().size()==2)
        {
            radioButton2.setVisibility(View.GONE);
            radioButton3.setVisibility(View.GONE);
            radioButton0.setText(option.get(0));
            radioButton1.setText(option.get(1));
        }
        else if(bean.getOption().size()==3)
        {
            radioButton3.setVisibility(View.GONE);
            radioButton0.setText(option.get(0));
            radioButton1.setText(option.get(1));
            radioButton2.setText(option.get(2));
        }
        else if(bean.getOption().size()==4)
        {
            radioButton0.setText(option.get(0));
            radioButton1.setText(option.get(1));
            radioButton2.setText(option.get(2));
            radioButton3.setText(option.get(3));
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton bb = view.findViewById(checkedId);
//                Toast.makeText(context,bb.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
