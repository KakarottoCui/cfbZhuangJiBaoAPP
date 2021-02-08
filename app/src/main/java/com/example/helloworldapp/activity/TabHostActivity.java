package com.example.helloworldapp.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import com.example.helloworldapp.R;
import com.example.helloworldapp.activity.HomeActivity;
import com.example.helloworldapp.net.HttpManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TabHostActivity extends ActivityGroup {

    public String data;
    public String username;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
        tabHost = findViewById(R.id.tabHost);
        tabHost.setup(this.getLocalActivityManager());
        TabHost.TabSpec ts1 = tabHost.newTabSpec("li1").setIndicator("首页新闻").setContent(new Intent(this, HomeActivity.class));
        tabHost.addTab(ts1);
        TabHost.TabSpec ts2 = tabHost.newTabSpec("li2").setIndicator("快速装机").setContent(new Intent(this,ToolActivity.class));
        tabHost.addTab(ts2);

        okhttpData();
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getCharSequence("usernames").toString();
        Log.i("username",username);
//        Intent intent1 = new Intent(TabHostActivity.this,UserActivity.class);
//        Bundle bundle1 = new Bundle();
//        bundle1.putCharSequence("username",username);
//        intent1.putExtras(bundle1);
//        TabHost.TabSpec ts3 = tabHost.newTabSpec("li3").setIndicator("个人中心").setContent(intent1);
//        tabHost.addTab(ts3);

    }
    //get同步请求
    private void okhttpData(){
        Log.i("TAG","--ok-");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(HttpManager.ImageUrl +"?username="+username).build();
                try {
                    Response sponse=client.newCall(request).execute();
                    data = sponse.body().string();

                    //解析
                    jsonJXData(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void jsonJXData(String data) {
        if(data!=null) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONObject jsonObject1 = jsonObject.getJSONObject("image");
                String imageurl = jsonObject1.getString("userImage");
                Log.i("imageurl",imageurl);
                final Intent intent1 = new Intent(TabHostActivity.this,UserActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putCharSequence("username",username);
                bundle1.putCharSequence("imageurl",imageurl);
                intent1.putExtras(bundle1);

                TabHostActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        TabHost.TabSpec ts3 = tabHost.newTabSpec("li3").setIndicator("个人中心").setContent(intent1);
                        tabHost.addTab(ts3);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}