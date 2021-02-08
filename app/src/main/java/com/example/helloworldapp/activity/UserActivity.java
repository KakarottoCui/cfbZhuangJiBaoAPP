package com.example.helloworldapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.helloworldapp.R;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//用户中心获取用户头像和昵称
public class UserActivity extends BaseActivity{
    private TextView user_text = null;
    private ImageView user_image = null;
    public String username = null;
    public String imageurl = null;
    private Button set = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //作用是防止禁止主线程不能访问网络的问题“AndroidBlockGuardPolicy.onNetwork”
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        user_text = findViewById(R.id.user_text);
        user_image = findViewById(R.id.user_image);
        set = findViewById(R.id.set);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getCharSequence("username").toString();
        imageurl = bundle.getCharSequence("imageurl").toString();
        user_text.setText(username);
        final Bitmap bitmap = getHttpBitmap(imageurl);
        user_image.setImageBitmap(bitmap);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(UserActivity.this,SettingActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putCharSequence("username",username);
                intent1.putExtras(bundle1);
                startActivity(intent1);
            }
        });

    }

    /**
     * 获取网落图片资源
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}