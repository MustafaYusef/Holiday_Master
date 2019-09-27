package com.mustafayusef.holidaymaster.login.profile.dashBoard

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.fragment_web_view.*
import kotlinx.android.synthetic.main.progress2.*


class webView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }
    var url:String?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        url=arguments!!.getString("url")
        println("url   "+url)

        animation_viewTourPub?.visibility=View.VISIBLE
        val settings = webViewT.settings;

        // Enable java script in web view
        settings.javaScriptEnabled = true

        // Enable and setup web view cache
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        //settings.setAppCachePath(cacheDir.path)


        // Enable zooming in web view
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true


        // Zoom web view text
        //settings.textZoom = 125


        // Enable disable images in web view
        settings.blockNetworkImage = false
        // Whether the WebView should load image resources
        settings.loadsImagesAutomatically = true


        // More web view settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }
        //settings.pluginState = WebSettings.PluginState.ON
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            settings.mediaPlaybackRequiresUserGesture = false
        }

        // More optional settings, you can enable it by yourself
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.allowUniversalAccessFromFileURLs = true
        }

        settings.allowFileAccess = true

        // WebView settings
        webViewT.fitsSystemWindows = true

        /* if SDK version is greater of 19 then activate hardware acceleration
        otherwise activate software acceleration  */

        webViewT.setLayerType(View.LAYER_TYPE_HARDWARE, null)

        webViewT.loadUrl(url)

        // Set web view client
        webViewT.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something

            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                // Enable disable back forward button
                animation_viewTourPub?.visibility=View.GONE
            }
        }
    }

}
