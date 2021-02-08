package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.helloworldapp.R;

public class SettingActivity extends BaseActivity {
    public String username = null;
    private Button safe_btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        safe_btn = findViewById(R.id.safe_btn);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getCharSequence("username").toString();
        Log.i("Setting-username",username);

        safe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SettingActivity.this,SafeActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putCharSequence("username",username);
                intent1.putExtras(bundle1);
                startActivity(intent1);
            }
        });
    }
}