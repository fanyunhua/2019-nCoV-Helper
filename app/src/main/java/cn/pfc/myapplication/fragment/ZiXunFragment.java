package cn.pfc.myapplication.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.myapplication.R;
import cn.pfc.myapplication.activity.AnswerActivity;
import cn.pfc.myapplication.activity.PlayVideoActivity;
import cn.pfc.myapplication.util.StringUtil;

public class ZiXunFragment extends Fragment {


    private TextView ZiXunXiShoutextView;
    private TextView ZiXunDaiKouZhaotextView;

    private TextView ZiXunFangYitextView;
    private VideoView videoView;
    private TextView ZiXunZhiShitextView;


    int current = 0;
    boolean playerStatus = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_zixun_fragment,null);
        initView(view);
        listener();
        
        handlerVideoPlayer();
        return view;
    }

    private void handlerVideoPlayer() {
        final List<String>  videoPathList = new ArrayList<>();
        videoPathList.add("android.resource://"+getContext().getPackageName()+"/raw/"+R.raw.video_01);
        videoPathList.add("android.resource://"+getContext().getPackageName()+"/raw/"+R.raw.video_02);
        videoPathList.add("android.resource://"+getContext().getPackageName()+"/raw/"+R.raw.video_03);


        current = 0;
        videoView.setVideoPath(videoPathList.get(current++));
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(current<videoPathList.size())
                {
                    videoView.setVideoPath(videoPathList.get(current++));
                    videoView.requestFocus();
                    videoView.start();
                }
                else
                {
                    current = 0;
                    videoView.setVideoPath(videoPathList.get(current++));
                    videoView.requestFocus();
                    videoView.start();
                }

            }
        });
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerStatus==true)
                {
                    videoView.pause();
                }
                else
                {
                    videoView.start();
                }
                playerStatus = !playerStatus;

            }
        });
    }

    private void listener() {
        ZiXunDaiKouZhaotextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra("video", StringUtil.DAI_KOU_ZHAO_VIDEO);
                startActivity(intent);
            }
        });
        ZiXunXiShoutextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra("video", StringUtil.XI_SHOU_VIDEO);
                startActivity(intent);
            }
        });
        ZiXunFangYitextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra("video", StringUtil.FANG_YI_VIDEO);
                startActivity(intent);
            }
        });

        ZiXunZhiShitextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AnswerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        ZiXunXiShoutextView = (TextView) view.findViewById(R.id.ZiXunXiShoutextView);
        ZiXunDaiKouZhaotextView = (TextView) view.findViewById(R.id.ZiXunDaiKouZhaotextView);
        ZiXunFangYitextView = (TextView) view.findViewById(R.id.ZiXunFangYitextView);
        videoView = (VideoView) view.findViewById(R.id.videoView);

        ZiXunZhiShitextView = (TextView) view.findViewById(R.id.ZiXunZhiShitextView);

    }
}
