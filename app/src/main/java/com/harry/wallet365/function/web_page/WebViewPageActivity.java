package com.harry.wallet365.function.web_page;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.harry.wallet365.R;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.base.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Harry on 2019/1/15.
 * 显示WebView页面
 */
public class WebViewPageActivity extends BaseActivity {

    @Override
    protected int setupView() {
        return R.layout.activity_web_page;
    }

    @Override
    protected void initView() {
        String url = getIntent().getStringExtra("url");
        WebView webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl(url);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
