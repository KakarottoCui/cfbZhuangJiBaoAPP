package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworldapp.R;

public class ToolActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);

        Button priceAssemble_id = findViewById(R.id.priceAssemble_id);
        Button performanceAssemble_id = findViewById(R.id.performanceAssemble_id);
        Button diyAssemble_id = findViewById(R.id.diyAssemble_id);

        priceAssemble_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToolActivity.this,PriceAssembleActivity.class);
                startActivity(intent);
            }
        });

        performanceAssemble_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToolActivity.this,PerformanceAssembleActivity.class);
                startActivity(intent);
            }
        });

        diyAssemble_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToolActivity.this,DiyAssembleActivity.class);
                startActivity(intent);
            }
        });
    }
}