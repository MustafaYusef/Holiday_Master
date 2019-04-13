package com.mustafayusef.holidaymaster.tickets

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder


import com.mustafayusef.holidaymaster.Adapters.OneWayAdapter
import com.mustafayusef.holidaymaster.Adapters.TowWayAdapter
import com.mustafayusef.holidaymaster.Models.modelOne
import com.mustafayusef.holidaymaster.Models.modelTow
import com.mustafayusef.holidaymaster.R

import kotlinx.android.synthetic.main.activity_show_holiday.*
import okhttp3.*

import java.io.IOException

class showHoliday : AppCompatActivity() {
    var flag:Boolean=true
    var adult=0
    var child=0
    var ifant=0
    var departure=""
    var Return=""
    var type=""
    var from=""
    var to=""


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_holiday)
        animation_view.playAnimation()
        animation_view.speed= 3F

      println("ddddddddddddddddddddddddddddddddd after animation")


          flag=intent.getBooleanExtra("flage",true)
         adult=intent.getIntExtra("adult",1)
         child=intent.getIntExtra("child",0)
         ifant=intent.getIntExtra("infant",0)
         departure=intent.getStringExtra("departure")
         Return=intent.getStringExtra("Return")
         type=intent.getStringExtra("Type")
         from=intent.getStringExtra("fromSelect")
         to=intent.getStringExtra("toSelect")
        println("ddddddddddddddddddddddddddddddddd after get extras")


        //Holiday_list.layoutManager= LinearLayoutManager(this)
        fromcity.text=from
        toShow.text=to
        classShow.text= "Adult:$adult Children:$child class:$type Dep:$departure"
        println("ddddddddddddddddddddddddddddddddd after set text view")



        var url:String=""
        if(type=="Economy"){type="e"}
        if(type=="Business"){type="b"}
        if(type=="First"){type="f"}
        println("ddddddddddddddddddddddddddddddddd after change economy ")


        Holiday_list.layoutManager= LinearLayoutManager(this)
        if(flag){
            // url="https://favorite-holiday.herokuapp.com/api/orders/oneway?from=BGW&to=BEY&data=2019-03-24&adt=1&type=e&chd=0"
            println("ddddddddddddddddddddddddddddddddd after check flage one ")
            runRequestOne()
        } else{
//            https://favorite-holiday.herokuapp.com/api/orders/twoway?from=BGW&to=BEY&Ddata=2019-03-22&adt=1&type=e&chd=0&Rdata=2019-04-14

            println("ddddddddddddddddddddddddddddddddd after check flage tow ")

            runRequestTow()
        }




    }
    fun backSearch(view: View){
        val intent = Intent(this, searchActivity::class.java)
        startActivity(intent)
    }


    fun runRequestOne(){
        println("ddddddddddddddddddddddddddddddddd Request one ")
       val url="https://favorite-holiday.herokuapp.com/api/orders/oneway?from=$from&to=$to&data=$departure&adt=$adult&type=$type&chd=$child&Infant=$ifant"
        val request=Request.Builder().url(url).build()
         val client=OkHttpClient()

            client.newCall(request).enqueue(object :Callback {


                override fun onResponse(call: Call, response: Response) {

                    val body=response.body()?.string()
                     if(body!!.length>50){
                         println(body)
                         val gson= GsonBuilder().create()
                         val holidayFeed:List<modelOne>? = gson.fromJson(body, Array<modelOne>::class.java).toList()
                         println("the array objects")
                         println(holidayFeed)
                         runOnUiThread {

                             noResult?.text=holidayFeed?.size.toString()+" Result Found"
                             Holiday_list?.adapter= OneWayAdapter(this@showHoliday,holidayFeed)
                             animation_view.translationZ= 0F
                             animation_view.pauseAnimation()
                         }
                     }else{
                         runOnUiThread {
                             noResult?.setTextColor(-0x01ffff)
                             noResult?.text=" There is no result Found"
                             animation_view.translationZ= 0F
                             animation_view.pauseAnimation()
                         }
                     }


                }
                override fun onFailure(call: Call, e: IOException) {
                    val intent=Intent(this@showHoliday, searchActivity::class.java)
                    startActivity(intent)
//                   Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }


            })


    }


    fun runRequestTow(){
       //val url="https://favorite-holiday.herokuapp.com/api/orders/twoway?from=$from&to=$to&Ddata=$departure&adt=$adult&type=$type&chd=$child&Rdata=$Return"
        println("ddddddddddddddddddddddddddddddddd Request towwwww ")

       val url="https://favorite-holiday.herokuapp.com/api/orders/twoway?from=$from&to=$to&Ddata=$departure&adt=$adult&type=$type&chd=$child&Rdata=$Return&Infant=$ifant"
        val request=Request.Builder().url(url).build()
        val client=OkHttpClient()
            client.newCall(request).enqueue(object :Callback {


                override fun onResponse(call: Call, response: Response) {

                    val body=response.body()?.string()
                    if(body!!.length>50){
                    println(body)
                    val gson= GsonBuilder().create()
                    val holidayFeedTow:List<modelTow>? = gson.fromJson(body, Array<modelTow>::class.java).toList()
                     println("the array objects")
                    println(holidayFeedTow)

                    runOnUiThread {
                        noResult?.text=holidayFeedTow?.size.toString()+" Result Found"

                        Holiday_list?.adapter= TowWayAdapter(this@showHoliday,holidayFeedTow)
                        animation_view.translationZ= 0F
                        animation_view.pauseAnimation()
                    }
                }
                else{
                        runOnUiThread {
                            noResult?.setTextColor(-0x01ffff)

                            noResult?.text=" There is no result Found"
                            animation_view.translationZ= 0F
                            animation_view.pauseAnimation()
                        }
                    }}

                override fun onFailure(call: Call, e: IOException) {
                    val intent=Intent(this@showHoliday, searchActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }


            })


    }

}