package com.mustafayusef.holidaymaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray


import com.mustafayusef.holidaymaster.Adapters.OneWayAdapter
import com.mustafayusef.holidaymaster.Adapters.TowWayAdapter
import com.mustafayusef.holidaymaster.Models.modelOne
import com.mustafayusef.holidaymaster.Models.modelTow

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_holiday)

          flag=intent.getBooleanExtra("flage",true)
         adult=intent.getIntExtra("adult",0)
         child=intent.getIntExtra("child",0)
         ifant=intent.getIntExtra("ifant",0)
         departure=intent.getStringExtra("departure")
         Return=intent.getStringExtra("Return")
         type=intent.getStringExtra("Type")
         from=intent.getStringExtra("fromSelect")
         to=intent.getStringExtra("toSelect")

        var url:String=""
        if(type=="Economy"){type="e"}
        if(type=="Class"){type="c"}
        if(type=="First"){type="f"}
        if(flag){
            // url="https://favorite-holiday.herokuapp.com/api/orders/oneway?from=BGW&to=BEY&data=2019-03-24&adt=1&type=e&chd=0"
            url="https://favorite-holiday.herokuapp.com/api/orders/oneway?from=$from&to=$to&data=$departure&adt=$adult&type=$type&chd=$child&Infant=$ifant"
            runRequestOne(url)
        } else{
//            https://favorite-holiday.herokuapp.com/api/orders/twoway?from=BGW&to=BEY&Ddata=2019-03-22&adt=1&type=e&chd=0&Rdata=2019-04-14

            url="https://favorite-holiday.herokuapp.com/api/orders/twoway?from=$from&to=$to&Ddata=$departure&adt=$adult&type=$type&chd=$child&Rdata=$Return&Infant=$ifant"
            runRequestTow(url)
        }


     fromcity.text=from
        toShow.text=to
        classShow.text="Adult:"+adult.toString()+" Children:"+child.toString()+" class:"+type+" Dep:"+departure

    }
    fun backSearch(view: View){
        val intent = Intent(this,searchActivity::class.java)
        startActivity(intent)
    }


    fun runRequestOne(url:String){
        Holiday_list.layoutManager= LinearLayoutManager(this)

        val request=Request.Builder().url(url).build()
         val client=OkHttpClient()
        try {
            client.newCall(request).enqueue(object :Callback {

                override fun onResponse(call: Call, response: Response) {

                    val body=response.body()?.string()
                     if(body!!.length>50){
                         //println(body)
                         val gson= GsonBuilder().create()
                         val holidayFeed:List<modelOne>? = gson.fromJson(body, Array<modelOne>::class.java).toList()

                         runOnUiThread {

                             noResult?.text=holidayFeed?.size.toString()+" Result"
                             Holiday_list?.adapter= OneWayAdapter(this@showHoliday,holidayFeed)

                         }
                     }else{
                         runOnUiThread {
                             noResult?.text=" There is no result Found"

                         }
                     }


                }
                override fun onFailure(call: Call, e: IOException) {
                    val intent=Intent(this@showHoliday,searchActivity::class.java)
                    startActivity(intent)
//                   Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }


            })
        }catch (e:Exception){
           Toast.makeText(applicationContext, "something Wrong", Toast.LENGTH_SHORT).show()

        }

    }


    fun runRequestTow(url:String){
       //val url="https://favorite-holiday.herokuapp.com/api/orders/twoway?from=$from&to=$to&Ddata=$departure&adt=$adult&type=$type&chd=$child&Rdata=$Return"
        Holiday_list.layoutManager=LinearLayoutManager(this)

        val request=Request.Builder().url(url).build()
        val client=OkHttpClient()
        try {
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
                        noResult?.text=holidayFeedTow?.size.toString()+" Result"

                        Holiday_list?.adapter= TowWayAdapter(this@showHoliday,holidayFeedTow)

                    }
                }
                else{
                        runOnUiThread {
                            noResult?.text=" There is no result Found"

                        }
                    }}

                override fun onFailure(call: Call, e: IOException) {
                    val intent=Intent(this@showHoliday,searchActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }


            })
        }catch (e:Exception){
            Toast.makeText(applicationContext, "something Wrong", Toast.LENGTH_SHORT).show()

        }

    }

}