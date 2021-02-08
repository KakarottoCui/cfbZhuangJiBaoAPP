package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.helloworldapp.R;
import com.example.helloworldapp.net.HttpManager;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private String jsondata;
    private String usernames,passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        Button submit = findViewById(R.id.submit);

        //点击登录的时候判断用户名和密码，检查非空后提交，执行网络连接请求并解析回传的JSON数据
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText())) {
                    Toast.makeText(MainActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(MainActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                } else {
                    usernames = username.getText().toString();
                    passwords = password.getText().toString();
                    okhttpData();
                    finish();
                }
            }
        });

    }
//get同步请求
//    private void okhttpData(){
//        Log.i("TAG","--ok-");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client=new OkHttpClient();
//                Request request=new Request.Builder().url("http://192.168.1.6:8080/login?username="+usernames+"&password="+passwords).build();
//                try {
//                    Response sponse=client.newCall(request).execute();
//                    jsondata = sponse.body().string();
//
//                    //解析
//                    jsonJXData(jsondata);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//    }

    //post异步请求
    private void okhttpData(){
        Log.i("TAG","--ok-");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                //Form表单格式的参数传递
                FormBody formBody = new FormBody
                        .Builder()
                        .add("username",usernames)//设置参数名称和参数值
                        .add("PassWord",passwords)//设置参数名称和参数值
                        .build();
                Request request = new Request
                        .Builder()
                        .post(formBody)//Post请求的参数传递
                        .url(HttpManager.loginUrl)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {}

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //此方法运行在子线程中，不能在此方法中进行UI操作。
                        jsondata = response.body().string();
                        //解析
                        jsonJXData(jsondata);
                        response.body().close();
                    }
                });
            }
        }).start();
    }

    //解析JSON数据
    private void jsonJXData(String jsondata) {
        if(jsondata!=null) {
            try {
                JSONObject jsonObject = new JSONObject(jsondata);
                String data = jsonObject.getString("message");
                if (data.equals("登录成功")){
                    Looper.prepare();
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, TabHostActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("usernames",usernames);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Looper.loop();
                }else if (data.equals("登录失败")){
                    Looper.prepare();
                    Toast.makeText(MainActivity.this,"用户名或密码不正确",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}