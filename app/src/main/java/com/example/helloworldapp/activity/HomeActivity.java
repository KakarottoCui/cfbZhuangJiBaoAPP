package com.example.helloworldapp.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.bumptech.glide.Glide;
import com.example.helloworldapp.R;
import com.example.helloworldapp.adapter.recy_item_Adapter;
import com.example.helloworldapp.net.HttpManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends BaseActivity implements OnBannerListener {
    private ArrayList<String> list_path;
    //获取的json数据
    public String data;
    public String title;
    public String images;
    public String contents;
    public String authors;
    public RecyclerView recyclerview;
    public List<Map<String,Object>> list=new ArrayList<>();
    public SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        okhttpData();
        recyclerview= findViewById(R.id.recy);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里获取数据的逻辑
                okhttpData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
    //okhttp网络连接
    private void okhttpData() {
        Log.i("TAG","--ok-");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(HttpManager.ArticleListUrl).build();
                try {
                    Response sponse=client.newCall(request).execute();
                    data=sponse.body().string();
                    //解析
                    jsonJXData(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    //解析JSON数据
    private void jsonJXData(String data) {
        if(data!=null) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String datas = jsonObject.getString("result");
                JSONArray array = new JSONArray(datas);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    title = object.getString("title");
                    images = object.getString("image");
                    contents = object.getString("content");
                    authors = object.getString("author");
                    Drawable drawable = loadImageFromNetwork(images);

                    Map<String, Object> map = new HashMap<>();
                    map.put("title", title);
                    map.put("images",drawable);
                    map.put("contents",contents);
                    map.put("authors",authors);
                    list.add(map);

                }
                Message msg=new Message();
                msg.what=1;
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //添加分割线
                    recyclerview.addItemDecoration(new DividerItemDecoration(
                            HomeActivity.this, DividerItemDecoration.VERTICAL));
                    recy_item_Adapter recy=new recy_item_Adapter(list,HomeActivity.this);
                    //设置布局显示格式
                    recyclerview.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                    recyclerview.setAdapter(recy);
                    break;


            }
        }
    };
    private Drawable loadImageFromNetwork(String imageUrl)
    {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }

        return drawable ;
    }
    /**
     * 初始化控件
     */
    private void initView() {
        //轮播图
        carousel();
    }
    //轮播图
    public void carousel() {
        //初始化轮播图网络地址
        list_path = new ArrayList<>();
        list_path.add(HttpManager.RationUrl + "/1.jpg");
        list_path.add(HttpManager.RationUrl + "/2.jpg");
        list_path.add(HttpManager.RationUrl + "/3.jpg");

        initCarousel();
    }

    //初始化轮播图
    private void initCarousel() {
        Banner banner = findViewById(R.id.banner);
        banner.setImageLoader(new MyLoader());
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(list_path).setOnBannerListener(this).start();
    }

    @Override
    public void OnBannerClick(int position) {

    }

    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path).error(R.drawable.error)
                    .into(imageView);
        }
    }

}