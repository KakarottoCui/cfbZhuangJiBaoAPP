package com.example.helloworldapp.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.helloworldapp.R;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class ContentActivity extends BaseActivity {
    private String htmlContents = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        HtmlTextView htmlTextView = findViewById(R.id.html_text);
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        htmlContents = bundle.getCharSequence("contents").toString();
        //html内容转化
        htmlTextView.setHtml(htmlContents, new HtmlHttpImageGetter(htmlTextView));

    }
}