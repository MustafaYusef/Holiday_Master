package com.mustafayusef.holidaymaster.Hotels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Adapters.HotelsAdapter
import com.mustafayusef.holidaymaster.Adapters.TowWayAdapter
import com.mustafayusef.holidaymaster.Models.hotel
import com.mustafayusef.holidaymaster.Models.modelTow
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.searchActivity
import kotlinx.android.synthetic.main.activity_show_holiday.*
import kotlinx.android.synthetic.main.activity_show_hotels.*
import okhttp3.*
import java.io.IOException

class ShowHotels : AppCompatActivity() {
    var checkIn: String = ""
    var checkOut: String = ""
    var AdultNo: Int = 0
    var ChildNo: Int = 0
    var InfantNo: Int = 0
    var CityHot:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_hotels)

        checkIn=intent.getStringExtra("checkIn")
        checkOut= intent.getStringExtra("checkOut")
        CityHot=intent.getStringExtra("CityHotel").toLowerCase()
        AdultNo=intent.getIntExtra("Adult",0)
        ChildNo=intent.getIntExtra("Child",0)
        InfantNo=intent.getIntExtra("Infant",0)
        CityDateHot.text=CityHot+"   "+checkIn+"   "+checkOut+"  "+AdultNo
        val url:String="https://favorite-holiday.herokuapp.com/api/holet/all?adt=$AdultNo&in=$checkIn&infint=$InfantNo&ch=$ChildNo&out=$checkOut&city=$CityHot"
        runRequest(url)
    }
    fun backHotelSearch(view: View){
        val intent=Intent(this@ShowHotels,SearchHotels::class.java)
        startActivity(intent)
    }
    fun runRequest(url:String){
        //val url="https://favorite-holiday.herokuapp.com/api/orders/twoway?from=$from&to=$to&Ddata=$departure&adt=$adult&type=$type&chd=$child&Rdata=$Return"
        Hotels_list.layoutManager= LinearLayoutManager(this)

        val request= Request.Builder().url(url).build()
        val client= OkHttpClient()
        try {
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body=response.body()?.string()

                    val gson= GsonBuilder().create()
                    val HotelsFeed:List<hotel>? = gson.fromJson(body, Array<hotel>::class.java).toList()



                    runOnUiThread {
                        noResultHot?.text=HotelsFeed?.size.toString()+" Result"

                        Hotels_list?.adapter= HotelsAdapter(this@ShowHotels,HotelsFeed)

                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    val intent=Intent(this@ShowHotels, SearchHotels::class.java)
                    startActivity(intent)
                    //Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }


            })
        }catch (e:Exception){
            Toast.makeText(applicationContext, "something Wrong", Toast.LENGTH_SHORT).show()

        }

    }


}
