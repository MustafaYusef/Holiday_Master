package com.mustafayusef.holidaymaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder


import com.mustafayusef.holidaymaster.Adapters.OneWayAdapter
import com.mustafayusef.holidaymaster.Adapters.TowWayAdapter
import com.mustafayusef.holidaymaster.Models.modelOne
import com.mustafayusef.holidaymaster.Models.modelTow

import kotlinx.android.synthetic.main.activity_show_holiday.*
import okhttp3.*

import java.io.IOException

class showHoliday : AppCompatActivity() {
  var flag:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_holiday)
         flag=intent.getBooleanExtra("flage",true)
        var adult=intent.getIntExtra("adult",0)
        var child=intent.getIntExtra("child",0)
        var ifant=intent.getIntExtra("ifant",0)
        var departure=intent.getStringExtra("departure")
        var Return=intent.getStringExtra("Return")
        var type=intent.getStringExtra("Type")
        var from=intent.getStringExtra("fromSelect")
        var to=intent.getStringExtra("toSelect")




     fromcity.text=from
        toShow.text=to
        classShow.text=type
        AdultShow.text=adult.toString()+"Adult"
        childShow.text=child.toString()+"child"
        dateShow.text=departure


//     OneWay   params: from = country ,to=country ,Ddata=Departing Date
// , type=Class,chd= Number of Children adt=0


//  Tow way      params: from = country
//        ,to=country ,Ddata=Departing Date , Rdata=Returning Date ,
//        type=Class,chd= Number of Children adt=0
        var url:String=""
  if(type=="Economy"){type="e"}
  if(flag){
      url="https://favorite-holiday.herokuapp.com/api/orders/oneway?from=BGW&to=BEY&data=2019-03-24&adt=1&type=e&chd=0"
     // url="https://favorite-holiday.herokuapp.com/api/orders/oneway?from={$from}&to={$to}&data={$departure}&adt={$adult}&type={$type}&chd={$child}"
      runRequestOne(url)
  } else{

      url="https://favorite-holiday.herokuapp.com/api/orders/twoway?from=BGW&to=BEY&Ddata=2019-03-22&adt=1&type=e&chd=0&Rdata=2019-04-14"
      runRequestTow(url)
  }








    }
    fun backSearch(view: View){
        val intent = Intent(this,searchActivity::class.java)
        startActivity(intent)
    }

  //data class HolidayOne(val holiday: ArrayList<modelOne>)

    fun runRequestOne(url:String){
        Holiday_list.layoutManager= LinearLayoutManager(this)
        val request=Request.Builder().url(url).build()
         val client=OkHttpClient()
           client.newCall(request).enqueue(object :Callback {

               override fun onResponse(call: Call, response: Response) {
                   val body=response.body()?.string()

                   println(body)
                   val gson= GsonBuilder().create()
                   val  holidayFeed:List<modelOne> = gson.fromJson(body, Array<modelOne>::class.java).toList()
                 //  val homedateList: List<HomeDate> = gson.fromJson(body, Array<HomeDate>::class.java).toList()
                  // val holidayFeed:List<HolidayOne>=gson.fromJson(body,Array<HolidayOne>::class.java).tolist()
                  // val holidayFeed=gson?.fromJson(body,HolidayOne::class.java)
//                   Array<WeatherObject>::class.java).toList()
                   //println(holidayFeed)
                   //result.text=holidayFeed.size.toString()
               runOnUiThread {

                    Holiday_list.adapter= OneWayAdapter(this@showHoliday,holidayFeed)
                }

               }
               override fun onFailure(call: Call, e: IOException) {
                   Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
               }


           })

    }
    //data class HolidaysTow(val holiday: List<modelTow>)

    fun runRequestTow(url:String){

        Holiday_list.layoutManager=LinearLayoutManager(this)

        val request=Request.Builder().url(url).build()
        val client=OkHttpClient()
        client.newCall(request).enqueue(object :Callback {

            override fun onResponse(call: Call, response: Response) {
                val body=response.body()?.string()
                println(body)
                val gson= GsonBuilder().create()
                val  holidayFeed:List<modelTow> = gson.fromJson(body, Array<modelTow>::class.java).toList()
                println(holidayFeed)

                runOnUiThread {

                    Holiday_list.adapter= TowWayAdapter(this@showHoliday,holidayFeed)
                   // result.text=holidayFeed.lastIndex.toString()

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }


        })



    }






}
