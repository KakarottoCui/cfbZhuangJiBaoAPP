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

//修改密码
public class PasswordActivity extends BaseActivity {
    public String username = null;
    //获取的json数据
    public String data;
    private EditText edit_password = null;
    private EditText old_password = null;
    private Button submit_password = null;
    private String password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        edit_password = findViewById(R.id.edit_password);
        old_password = findViewById(R.id.old_password);
        submit_password = findViewById(R.id.submit_password);


        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getCharSequence("username").toString();
        Log.i("Password-username",username);

        submit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(old_password.getText())) {
                    Toast.makeText(PasswordActivity.this,"请输入原密码",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edit_password.getText())) {
                    Toast.makeText(PasswordActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                } else {
                    okhttpData();
                    Intent intent1 = new Intent(PasswordActivity.this,MainActivity.class);
                    startActivity(intent1);
                    removeALLActivity();//执行移除所有Activity方法
                }

            }
        });

    }
    //post异步请求
    private void okhttpData(){
        Log.i("TAG","--ok-");
        password = edit_password.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                //Form表单格式的参数传递
                FormBody formBody = new FormBody
                        .Builder()
                        .add("username",username)//设置参数名称和参数值
                        .add("PassWord",password)//设置参数名称和参数值
                        .build();
                Request request = new Request
                        .Builder()
                        .post(formBody)//Post请求的参数传递
                        .url(HttpManager.UpdatePass)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {}

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //此方法运行在子线程中，不能在此方法中进行UI操作。
                        data = response.body().string();
                        //解析
                        jsonJXData(data);
                        response.body().close();
                    }
                });
            }
        }).start();
    }
    //解析JSON数据
    private void jsonJXData(String data) {
        if(data!=null) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String datas = jsonObject.getString("result");
                if (datas.equals("success")){
                    Looper.prepare();
                    Toast.makeText(PasswordActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }else if (datas.equals("failed")){
                    Looper.prepare();
                    Toast.makeText(PasswordActivity.this,"密码修改失败",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}