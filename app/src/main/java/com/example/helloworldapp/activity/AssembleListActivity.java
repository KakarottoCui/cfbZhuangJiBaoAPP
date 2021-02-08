package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.helloworldapp.R;
import com.example.helloworldapp.net.HttpManager;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AssembleListActivity extends BaseActivity {
    private String data;
    private TextView cpu = null;
    private TextView gpu = null;
    private TextView board = null;
    private TextView disk = null;
    private TextView memory = null;
    private TextView power = null;
    private String type = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_assemble_list);

        cpu = findViewById(R.id.cpu_id);
        gpu = findViewById(R.id.gpu_id);
        board = findViewById(R.id.board_id);
        disk = findViewById(R.id.disk_id);
        memory = findViewById(R.id.memory_id);
        power = findViewById(R.id.power_id);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        type = bundle.getCharSequence("type").toString();
        Log.i("type:",type);

        okhttpData();
    }
    //get同步请求
    private void okhttpData(){
        Log.i("TAG","--ok-");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(HttpManager.PriceAssembleUrl+"?type="+type).build();
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

    //解析JSON数据
    private void jsonJXData(String data) {
        if(data!=null) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");

                String cpu_json = jsonObject1.getString("cpu");
                String gpu_json = jsonObject1.getString("gpu");
                String board_json = jsonObject1.getString("board");
                String disk_json = jsonObject1.getString("disk");
                String memory_json = jsonObject1.getString("memory");
                String power_json = jsonObject1.getString("power");

                cpu.setText(cpu_json);
                gpu.setText(gpu_json);
                board.setText(board_json);
                disk.setText(disk_json);
                memory.setText(memory_json);
                power.setText(power_json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}