package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworldapp.R;

//根据性能要求组装机
public class PerformanceAssembleActivity extends BaseActivity {

    private Button work = null;
    private Button develop = null;
    private Button cartoon = null;
    private Button tx = null;
    private Button steam = null;
    private Button aaa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_assemble);

        work = findViewById(R.id.work_id);
        develop = findViewById(R.id.develop_id);
        cartoon = findViewById(R.id.cartoon_id);
        tx = findViewById(R.id.tx_id);
        steam = findViewById(R.id.steam_id);
        aaa = findViewById(R.id.aaa_id);

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerformanceAssembleActivity.this,AssembleListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("type","1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        develop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerformanceAssembleActivity.this,AssembleListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("type","2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerformanceAssembleActivity.this,AssembleListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("type","3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerformanceAssembleActivity.this,AssembleListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("type","1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        steam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerformanceAssembleActivity.this,AssembleListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("type","2");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        aaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerformanceAssembleActivity.this,AssembleListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("type","3");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}