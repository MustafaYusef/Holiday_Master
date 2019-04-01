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
import kotlinx.android.synthetic.main.activity_login_member.*
import kotlinx.android.synthetic.main.activity_show_holiday.*
import kotlinx.android.synthetic.main.activity_show_hotels.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import okhttp3.FormBody
import okhttp3.RequestBody



class ShowHotels : AppCompatActivity() {
    var checkIn: String = ""
    var checkOut: String = ""
    var AdultNo: Int = 0
    var ChildNo: Int = 0
    var CityHot:String=""
    var chAge1:Int=0
    var chAge2:Int=0
    var chAge3:Int=0
    var chAge4:Int=0
    var chAge5:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mustafayusef.holidaymaster.R.layout.activity_show_hotels)

        checkIn=intent.getStringExtra("checkIn")
        checkOut= intent.getStringExtra("checkOut")
        CityHot=intent.getStringExtra("CityHotel").toLowerCase()
        AdultNo=intent.getIntExtra("Adult",0)
        ChildNo=intent.getIntExtra("Child",0)
       chAge1=intent.getIntExtra("chAge1",0)
        chAge2=intent.getIntExtra("chAge2",0)
        chAge3=intent.getIntExtra("chAge3",0)
        chAge4=intent.getIntExtra("chAge4",0)
        chAge5=intent.getIntExtra("chAge5",0)

        CityDateHot.text=CityHot+"   "+checkIn+"   "+checkOut+"  "+AdultNo+"Adult  "+ChildNo+"Child"




       // val url:String="https://favorite-holiday.herokuapp.com/api/holet/all?adt=$AdultNo&in=$checkIn&infint=$InfantNo&ch=$ChildNo&out=$checkOut&city=$CityHot"
        runRequest()
    }
    fun backHotelSearch(view: View){
        val intent=Intent(this@ShowHotels,SearchHotels::class.java)
        startActivity(intent)
    }
    fun runRequest(){
        //val url="https://favorite-holiday.herokuapp.com/api/orders/twoway?from=$from&to=$to&Ddata=$departure&adt=$adult&type=$type&chd=$child&Rdata=$Return"
        Hotels_list.layoutManager= LinearLayoutManager(this)


        val client=OkHttpClient()

        val body = FormBody.Builder()
            .add("in",checkIn)
            .add("out",checkOut)
            .add("adt",AdultNo.toString())
            .add("ch",ChildNo.toString())
            .add("city",CityHot)
            .add("chAge1",chAge1.toString())
            .add("chAge2",chAge2.toString())
            .add("chAge3",chAge3.toString())
            .add("chAge4",chAge4.toString())
            .add("chAge5",chAge5.toString())


            .build()


        val request = Request.Builder()
            .url("https://favorite-holiday.herokuapp.com/api/holet/all/v2")
            .post(body)
            .build()

        try {
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body=response.body()?.string()
                    println(body)
                    val gson= GsonBuilder().create()
                    val HotelsFeed:List<hotel>? = gson.fromJson(body, Array<hotel>::class.java).toList()

                   println(HotelsFeed)

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
