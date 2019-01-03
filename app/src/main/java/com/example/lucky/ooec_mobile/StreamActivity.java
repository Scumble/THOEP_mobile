package com.example.lucky.ooec_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class StreamActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        String html ="<html><body><iframe width=\"450\" height=\"260\" style=\"border: 1px solid #cccccc;\" src=\"https://player.twitch.tv/?channel=weplay_main\" frameborder=\"0\" allowfullscreen=\"true\" scrolling=\"no\" height=\"378\" width=\"620\"></iframe></body></html>";
        webView=(WebView) findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        //webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
      //  webView.setWebViewClient(new WebViewClient());
       // webView.setWebChromeClient(new WebChromeClient());
       // webView.getSettings().setDomStorageEnabled(true);
      //  webView.setWebChromeClient(new WebChromeClient());
     //   webView.setWebViewClient(new WebViewClient());
        //webView.loadData(html, "text/html", null);
        webView.loadUrl("https://www.twitch.tv/weplay_main");


    }
}
