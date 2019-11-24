package com.naledi.buzazi;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Uri uri = Uri.parse(url);
        if (uri.getHost() != null && uri.getHost().contains("buzazi.com")) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }
    //Dialog loadingDialog = new Dialog(this);


    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
        System.out.println("----- Erreur code: " + errorCode);
        System.out.println("----- Erreur url: " + description);


    }
}
