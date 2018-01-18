package com.pangli.slide.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pangli.slide.R;

public class WebViewActivity extends Activity {

    private WebView mWebView;
    private TextView mTitle;
    private ImageButton mBackBtn;
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setContentView(R.layout.activity_web_view);
        super.onCreate(savedInstanceState);
        mBundle = getIntent().getExtras();
        initView();
    }

    public void initView() {
        // TODO Auto-generated method stub
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl(mBundle.getString("url"));
        mWebView.requestFocusFromTouch();
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        /**覆盖调用系统或自带浏览器行为打开网页*/
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
        /**判断加载过程*/
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成

                } else {
                    // 加载中

                }
            }
        });
        initListener();
    }

    public void initListener() {
        // TODO Auto-generated method stub
        /**加载javascript*/
//      WebSettings mWebSetting = mWebView.getSettings();
//      mWebSetting.setJavaScriptEnabled(true);
//      mWebView.addJavascriptInterface(new Object() {
//            public void clickOnAndroid() {
//                new Handler().post(new Runnable() {
//                    public void run() {
//                      mWebView.loadUrl("javascript:wave()");
//                    }
//                });
//            }
//        }, "demo");

        /**打开页面时， 自适应屏幕*/
        WebSettings webSettings =   mWebView .getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);

        /**便页面支持缩放*/
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);

    }
}
