package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworldapp.R;

//根据价格组装机
public class PriceAssembleActivity extends BaseActivity {
    private EditText price_id =null;
    private Button priceButton_id = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_assemble);

        price_id = findViewById(R.id.price_id);
        priceButton_id = findViewById(R.id.priceButton_id);

        priceButton_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(price_id.getText())){
                    Toast.makeText(PriceAssembleActivity.this,"请输入预算！",Toast.LENGTH_SHORT).show();
                }else {
                    int price = Integer.parseInt(price_id.getText().toString());
                    if (price <= 2000){
                        Toast.makeText(PriceAssembleActivity.this,"预算过低！",Toast.LENGTH_SHORT).show();
                    } else if (price <= 4000){
                        Log.i("price:","1");
                        Intent intent = new Intent(PriceAssembleActivity.this, AssembleListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("type","1");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else if (price <= 8000){
                        Log.i("price:","2");
                        Intent intent = new Intent(PriceAssembleActivity.this, AssembleListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("type","2");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else {
                        Log.i("price:","3");
                        Intent intent = new Intent(PriceAssembleActivity.this, AssembleListActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("type","3");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}