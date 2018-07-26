package com.mahirfloralandevents.mahirfloral;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class MainActivity extends BaseActivity {

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initToolbar();

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient() {
            ProgressBar progressBar;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                if (progressBar == null) {
                    // in standard case YourActivity.this
                    progressBar = (ProgressBar) findViewById(R.id.loadingProgterm);

                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {

                    progressBar.setVisibility(View.INVISIBLE);


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Allow third party cookies for Android Lollipop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptThirdPartyCookies(myWebView,true);
        }

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAppCacheEnabled(true);
        myWebView.getSettings().setDatabaseEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWebView.getSettings().setSupportMultipleWindows(true);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setAppCachePath(appCachePath);


        myWebView.loadUrl("https://mahirfloralevents.com/floraltest/index.php#/");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }
}