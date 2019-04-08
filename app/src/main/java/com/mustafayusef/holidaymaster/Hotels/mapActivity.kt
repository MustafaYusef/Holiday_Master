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
        webView.loadData ( "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1782.1673059264274!2d44.37533523919297!3d33.27978308001305!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x39c0996d5573d9e6!2sNahrain+University!5e1!3m2!1sen!2siq!4v1536337601044\" width=\"400\" height=\"300\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>", "text/html", "utf-8")
    }
}
