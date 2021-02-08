package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.helloworldapp.R;

public class SafeActivity extends BaseActivity {
    public String username = null;
    private Button password_btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe);

        password_btn = findViewById(R.id.password_btn);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getCharSequence("username").toString();
        Log.i("Safe-username",username);

        password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SafeActivity.this,PasswordActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putCharSequence("username",username);
                intent1.putExtras(bundle1);
                startActivity(intent1);
            }
        });
    }
}