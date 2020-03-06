package cn.pfc.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.VideoView;

import cn.pfc.myapplication.R;
import cn.pfc.myapplication.util.StringUtil;

public class PlayVideoActivity extends AppCompatActivity {
    private VideoView videoView;
    private WebView webView;


    AlertDialog.Builder builder;
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        videoView = (VideoView) findViewById(R.id.videoView);
        webView = (WebView) findViewById(R.id.webView);

//        initVWebView();
        Intent intent = getIntent();


        if(intent.getIntExtra("video",0)== StringUtil.DAI_KOU_ZHAO_VIDEO)
        {
            videoView.setVideoPath("android.resource://"+getPackageName()+"/raw/"+R.raw.daikouzhao);
            url = "http://www.pfc.edu.cn/yw/2045.jhtml";
        }
        else if(intent.getIntExtra("video",0)== StringUtil.XI_SHOU_VIDEO)
        {
            videoView.setVideoPath("android.resource://"+getPackageName()+"/raw/"+R.raw.xishou);
            url = "http://www.pfc.edu.cn/yw/2049.jhtml";
        }
        else if (intent.getIntExtra("video",0)== StringUtil.FANG_YI_VIDEO)
        {
            videoView.setVideoPath("android.resource://"+getPackageName()+"/raw/"+R.raw.fangyi);
            url = "https://mp.weixin.qq.com/s/MZr-ThPwvo0hs3jVtzbXZQ";
        }
        builder = new AlertDialog.Builder(this)
                .setMessage("你学会了吗")
                .setPositiveButton("学会了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("没学会，再看一遍", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        videoView.start();
                    }
                });


        videoView.start();
        initVWebView();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                builder.create().show();
            }
        });
    }

    private void initVWebView() {

        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl(url);

    }
}
