package com.mustafayusef.holidaymaster.Visa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Adapters.NationalAdapter
import com.mustafayusef.holidaymaster.Models.country

import com.mustafayusef.holidaymaster.dashboard
import kotlinx.android.synthetic.main.activity_search_visa.*
import kotlinx.android.synthetic.main.show_national.view.*
import okhttp3.*
import java.io.IOException


class searchVisa : AppCompatActivity() {
   var country:String=""

    var national= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mustafayusef.holidaymaster.R.layout.activity_search_visa)
       // animation_view3.translationZ=1F


        showN.visibility=View.INVISIBLE
        run()
//        showN.visibility=View.VISIBLE
    }

  fun  showVisa(country:String){
      animation_view3.translationZ=1F
      animation_view3.playAnimation()
     // animation_view3.speed=4F
//      println("this is method n hhb"+country)
      Visa_list.layoutManager= LinearLayoutManager(this)

      val body = FormBody.Builder()
          .add("Nationality",country)


          .build()
      val request= Request.Builder().url("https://favorite-holiday.herokuapp.com/api/visa/")
          .post(body)
          .build()
      val client= OkHttpClient()
      try {
          client.newCall(request).enqueue(object : Callback {

              override fun onResponse(call: Call, response: Response) {

                  val body=response.body()?.string()
                  if(body!!.length>50){
                      //println(body)
                      val gson= GsonBuilder().create()
                      val countFeed:List<country> = gson.fromJson(body, Array<country>::class.java).toList()
                         println(countFeed)
                      runOnUiThread {
                        available?.text= countFeed!!.size.toString()+" Visa Available for your country"
                          Visa_list?.adapter= NationalAdapter(this@searchVisa,countFeed)
                          animation_view3.translationZ=0F
                          animation_view3.pauseAnimation()

                      }
                  }else{
                      available?.setTextColor(-0x01ffff)
                      available?.text="There is no Result Found"
                      animation_view3.translationZ=0F
                      animation_view3.pauseAnimation()
                  }


              }
              override fun onFailure(call: Call, e: IOException) {
                  Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
              }


          })
      }catch (e:Exception){
          Toast.makeText(applicationContext, "something Wrong", Toast.LENGTH_SHORT).show()

      }

  }
        fun showDilog(view: View) {

//            NationalPicker.setOnValueChangedListener { picker, oldVal, newVal ->
//
//                //Display the newly selected number to text view
//                country= newVal.toString()
//            }

            val dview: View = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.show_national, null)

            val builder = AlertDialog.Builder(this@searchVisa).setView(dview)



           val malert= builder.show()
            malert.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dview.NationalPicker.minValue = 0
            dview.NationalPicker.maxValue = national.size-1
            dview.NationalPicker.wrapSelectorWheel = true
            dview.NationalPicker.displayedValues = national.toTypedArray()
            country=national[0]
            dview.NationalPicker.setOnValueChangedListener { picker, oldVal, newVal ->
                //
                //Display the newly selected number to text view
                country = national[newVal]
           // println(country +"   cooodkl,dl")
                }
//            }
//                 dilog.NationalPicker.minValue = 0
//            dilog.NationalPicker .maxValue = 2
//            dilog.NationalPicker!!.wrapSelectorWheel = true
                dview.selectN.setOnClickListener {
                    showVisa(country)
                    malert.dismiss()
                   // national.clear()
                }

//
//            dilog.NationalPicker.displayedValues = arrayOf("aaaaaa","Poland")
//             dilog.NationalPicker.setOnValueChangedListener{ picker, oldVal, newVal ->
//
//                 //Display the newly selected number to text view
//                 country= newVal.toString()
//
//             }

//      dilog.selectN.setOnClickListener {
//          dilog.dismiss()
//      }



        }

    fun run(){
        animation_view3.playAnimation()
        animation_view3.speed=2F
        val request= Request.Builder().url("https://favorite-holiday.herokuapp.com/api/visa/").build()
        val client= OkHttpClient()
        try {
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {

                    val body=response.body()?.string()
                    if(body!!.length>30){
                        //println(body)
                        val gson= GsonBuilder().create()
                        var countryFeed:List<country>?= gson.fromJson(body, Array<country>::class.java).toList()
                        println("Hello      "+countryFeed)

            for(i in 0 until countryFeed!!.size){
                national.add( countryFeed!![i].Nationality!!)
            }
                        println(national)
                        runOnUiThread {
                            showN.visibility=View.VISIBLE
                            //   noResult?.text=countryFeed?.size.toString()+" Result"
                            animation_view3.translationZ=0F
                            animation_view3.pauseAnimation()

                        }
                    }else{

                    }


                }
                override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }


            })
        }catch (e:Exception){
            Toast.makeText(applicationContext, "something Wrong", Toast.LENGTH_SHORT).show()

        }
        animation_view3.translationZ=0F
        animation_view3.pauseAnimation()
        showN.isClickable=true
    }


fun backDVisa(view: View){
    val intent=Intent(this@searchVisa,dashboard::class.java)
    startActivity(intent)
}
}
