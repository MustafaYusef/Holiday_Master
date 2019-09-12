package com.mustafayusef.holidaymaster.Hotels


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.mustafayusef.holidaymaster.Models.hotel
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_details_hotel.*



class DetailsHotel : AppCompatActivity() {

    lateinit var hotel:hotel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_hotel)


         hotel=intent.getSerializableExtra("hotels")as hotel
        if(hotel.address!!.length>30){
            AdressD.text= hotel.address!!.subSequence(0,35).toString()+".."
        }else{
            AdressD.text=hotel.address
        }
        ratingBard.rating=hotel.stars!!.toFloat()
        NameD.text=hotel.name.toString()

        if (hotel?.body!!.length>250){
            bodyD.text= hotel.body!!.subSequence(0,250).toString()+".."
        }else{
            bodyD.text=hotel.body

        }
        // Glide.with(this).load(hotel.img).apply(RequestOptions.centerCropTransform()).into(ImageD)


    }
    fun backToHotel(view:View){
        super.onBackPressed()
    }
    fun ShowMap(view: View){
//        println(hotel.map.toString())
         val intent=Intent(this@DetailsHotel,mapActivity::class.java)
        intent.putExtra("iframe",hotel.map)
          startActivity(intent)
    }
    fun CheckRoom(view: View){
        val intent=Intent(this@DetailsHotel,CheckRooms::class.java)
        intent.putExtra("hotelOld",hotel)
        intent.putExtra("oldHot",hotel)
        startActivity(intent)
    }
}
