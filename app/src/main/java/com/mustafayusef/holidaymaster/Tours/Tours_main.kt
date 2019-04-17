package com.mustafayusef.holidaymaster.Tours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Adapters.ToursAdapter
import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.dashboard
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_show_holiday.*
import kotlinx.android.synthetic.main.activity_tours.*
import okhttp3.*
import java.io.IOException

class Tours_main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tours)
        animation_viewTour.playAnimation()
        animation_viewTour.speed= 3F

        Run()
    }
    fun backtoDash(view: View){
        val intent= Intent(this@Tours_main, dashboard::class.java)
        startActivity(intent)
    }

    fun Run(){
     Tours_list?.layoutManager= LinearLayoutManager(this)
        val url="https://favorite-holiday.herokuapp.com/api/Tours/all"
        val request= Request.Builder().url(url).build()
        val client= OkHttpClient()

        client.newCall(request).enqueue(object : Callback {


            override fun onResponse(call: Call, response: Response) {

                val body=response.body()?.string()
                if(body!!.length>50){
                    println(body)
                    val gson= GsonBuilder().create()
                    val TourFeed:List<Tours>? = gson.fromJson(body, Array<Tours>::class.java).toList()
                    println("the array objects")

                    runOnUiThread {

//                        noResult?.text=holidayFeed?.size.toString()+" Result Found"
                        Tours_list?.adapter= ToursAdapter(this@Tours_main,TourFeed)
                        animation_viewTour.translationZ= 0F
                        animation_viewTour.pauseAnimation()
                    }
                }else{
                    runOnUiThread {
//                        noResult?.setTextColor(-0x01ffff)
//                        noResult?.text=" There is no result Found"
                        animation_view.translationZ= 0F
                        animation_view.pauseAnimation()
                    }
                }


            }
            override fun onFailure(call: Call, e: IOException) {
                val intent=Intent(this@Tours_main, Tours::class.java)
                startActivity(intent)
//                   Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }


        })
    }


}
