package ru.thstdio.dogphoto.ui.web

import android.webkit.WebViewClient
import android.webkit.WebResourceRequest
import android.webkit.WebView



class MyWebViewClient: WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        view.loadUrl(request.url.toString())
        return true
    }
}