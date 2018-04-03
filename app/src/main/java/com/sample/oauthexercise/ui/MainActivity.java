package com.sample.oauthexercise.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import com.sample.oauthexercise.R;
import com.sample.oauthexercise.utils.StringUtils;


public class MainActivity extends AppCompatActivity {

    private TextView text;
    private WebView webView;
    private FloatingActionButton fab;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAllViews();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new OAuthWebViewClient(this));

        fab.setOnClickListener(view -> {
            text.setVisibility(View.GONE);
            webView.loadUrl(StringUtils.getAuthorizationURL());

            Snackbar.make(view, R.string.logging_in, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

    private void initAllViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        text = findViewById(R.id.tx_result);
        webView = findViewById(R.id.wv_oauth);
    }

    @SuppressLint("DefaultLocale")
    void updateText(String profileStr) {
        webView.setVisibility(View.INVISIBLE);
        text.setText(String.format("%s%s", getString(R.string.profile_label), profileStr));
        text.setVisibility(View.VISIBLE);
    }

}