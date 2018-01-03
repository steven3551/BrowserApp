package com.wuwg.browser.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wuwg.browser.R;
import com.wuwg.browser.base.BaseActivity;
import com.wuwg.browser.utils.Constants;
import com.wuwg.browser.widget.CustomWebView;

public class MainActivity extends BaseActivity {

    private CustomWebView mCustomWebView;
    private SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected int obtainLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mCustomWebView = (CustomWebView) findViewById(R.id.webview);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
    }

    @Override
    protected void initData() {
        mCustomWebView.loadUrl(Constants.HOME_URL);
        mCustomWebView.getSettings().setJavaScriptEnabled(true);  //加上这一行网页为响应式的
        mCustomWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }
        });
        mCustomWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mSwipeRefresh.setRefreshing(false);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void inirListener() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCustomWebView.loadUrl(Constants.HOME_URL);
            }
        });
    }
}
