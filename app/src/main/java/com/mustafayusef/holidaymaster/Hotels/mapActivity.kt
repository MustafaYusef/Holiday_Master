package com.mustafayusef.holidaymaster.Hotels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_map.*


class mapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val map=intent.getStringExtra("iframe")
        println(map)
        webView.loadDataWithBaseURL (null, map, "text/html", "utf-8",   "about:blank");
    }
}
