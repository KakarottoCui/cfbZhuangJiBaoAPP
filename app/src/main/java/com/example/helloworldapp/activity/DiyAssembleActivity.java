package com.example.helloworldapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.helloworldapp.R;

public class DiyAssembleActivity extends BaseActivity {

    private Spinner cpuSpinner = null;
    private Spinner gpuSpinner = null;
    private Spinner boardSpinner = null;
    private Spinner diskSpinner = null;
    private Spinner memorySpinner = null;
    private Spinner powerSpinner = null;
    private Button resultBt = null;
    private TextView result = null;
    String[] cpu = new String[]{"i3-10100","i5-10400","i7-10900"};
    String[] gpu = new String[]{"1050ti","1060s","2080ti"};
    String[] board = new String[]{"微星B460","微星B460","华硕Z490"};
    String[] disk = new String[]{"西部数据1TB","金士顿500GB固态","三星1TB固态"};
    String[] memory = new String[]{"金士顿8G","金士顿8G*2","三星16G*2"};
    String[] power = new String[]{"先马300W","爱国者450W","爱国者500W"};
    private String cpuSelect = null;
    private String gpuSelect = null;
    private String boardSelect = null;
    private String memorySelect = null;
    private String diskSelect = null;
    private String powerSelect = null;
    private String cpuSelectResult = null;
    private String gpuSelectResult = null;
//    private String boardSelectResult = null;
//    private String memorySelectResult = null;
//    private String diskSelectResult = null;
    private String powerSelectResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_assemble);

        cpuSpinner = findViewById(R.id.cpuSpinner_id);
        gpuSpinner = findViewById(R.id.gpuSpinner_id);
        boardSpinner = findViewById(R.id.boardSpinner_id);
        diskSpinner = findViewById(R.id.diskSpinner_id);
        memorySpinner = findViewById(R.id.memorySpinner_id);
        powerSpinner = findViewById(R.id.powerSpinner_id);
        resultBt = findViewById(R.id.resultBt_id);
        result = findViewById(R.id.result_id);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(DiyAssembleActivity.this, android.R.layout.simple_spinner_item,cpu);
        cpuSpinner.setAdapter(arrayAdapter1);
        cpuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cpuSelect = cpuSpinner.getItemAtPosition(i).toString();
                Log.i("cpu:",cpuSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //获取下拉选择的值
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(DiyAssembleActivity.this, android.R.layout.simple_spinner_item,gpu);
        gpuSpinner.setAdapter(arrayAdapter2);
        gpuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gpuSelect = gpuSpinner.getItemAtPosition(i).toString();
                Log.i("gpu:",gpuSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(DiyAssembleActivity.this, android.R.layout.simple_spinner_item,board);
        boardSpinner.setAdapter(arrayAdapter3);
        boardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                boardSelect = boardSpinner.getItemAtPosition(i).toString();
                Log.i("board:",boardSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(DiyAssembleActivity.this, android.R.layout.simple_spinner_item,disk);
        diskSpinner.setAdapter(arrayAdapter4);
        diskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                diskSelect = diskSpinner.getItemAtPosition(i).toString();
                Log.i("disk:",diskSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<String>(DiyAssembleActivity.this, android.R.layout.simple_spinner_item,memory);
        memorySpinner.setAdapter(arrayAdapter5);
        memorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                memorySelect = memorySpinner.getItemAtPosition(i).toString();
                Log.i("memory:",memorySelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapter6 = new ArrayAdapter<String>(DiyAssembleActivity.this, android.R.layout.simple_spinner_item,power);
        powerSpinner.setAdapter(arrayAdapter6);
        powerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                powerSelect = powerSpinner.getItemAtPosition(i).toString();
                Log.i("power:",powerSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //智能诊断结果
        resultBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (cpuSelect) {
                    case "i3-10100":
                        cpuSelectResult = "已选择的CPU属于低性能影音娱乐CPU，";
                        break;
                    case "i5-10400":
                        cpuSelectResult = "已选择的CPU属于中端办公或游戏使用，";
                        break;
                    case "i7-10900":
                        cpuSelectResult = "已选择的CPU适合高性能办公或游戏使用，";
                        break;
                }

                switch (gpuSelect) {
                    case "1050ti":
                        gpuSelectResult = "GPU属于低端图形处理芯片，";
                        break;
                    case "1060s":
                        gpuSelectResult = "GPU属于一般性能图形处理芯片，";
                        break;
                    case "2080ti":
                        gpuSelectResult = "GPU属于高性能图形处理芯片，";
                        break;
                }

                switch (powerSelect) {
                    case "先马300W":
                        powerSelectResult = powerSelect+"适合搭配低性能的芯片";
                        break;
                    case "爱国者450W":
                        powerSelectResult = powerSelect+"适合搭配一般性能的芯片";
                        break;
                    case "爱国者500W":
                        powerSelectResult = powerSelect+"适合搭配高性能的芯片";
                        break;
                }
                result.setText(cpuSelectResult+gpuSelectResult+powerSelectResult);
                result.setVisibility(View.VISIBLE);
            }
        });
    }
}