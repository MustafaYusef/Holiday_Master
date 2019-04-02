package com.mustafayusef.holidaymaster.Hotels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.OneWayAdapter
import com.mustafayusef.holidaymaster.Adapters.RoomsAdapter
import com.mustafayusef.holidaymaster.Models.hotel
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_check_rooms.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_holiday.*
import kotlinx.android.synthetic.main.check_rooms2.*
import kotlinx.android.synthetic.main.option_card.*

class CheckRooms : AppCompatActivity() {
  lateinit var hotel:hotel
     var hotel1:hotel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_rooms)
        RoomsList.layoutManager= LinearLayoutManager(this)




        if(intent.getSerializableExtra("hotel1") !=null) {
            hotel = intent.getSerializableExtra("hotel1") as hotel
            RoomsList?.adapter = RoomsAdapter(this@CheckRooms, hotel)

        }else{
            hotel = intent.getSerializableExtra("hotelOld") as hotel
            RoomsList?.adapter = RoomsAdapter(this@CheckRooms, hotel)
        }

       // hotel=intent.getSerializableExtra("hotel1") as hotel
       // println(hotel)


//        RoomsList6.layoutManager= LinearLayoutManager(this)
//        RoomsList6?.adapter= RoomsAdapter(this@CheckRooms,hotel)
//
//        ApplayOp.setOnClickListener{
//            linearLay9out2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.top_bottum))
//
//        }
//          if(intent.getSerializableExtra("hotelOld") !=null){
//              hotel1 = intent.getSerializableExtra("hotelOld") as hotel
//              RoomsList?.adapter= RoomsAdapter(this@CheckRooms,hotel1)
//
//        }

    }
    fun backHotelD(view:View){
        super.onBackPressed()
    }
    fun ShowOptions(view:View){

        val intent= Intent(this@CheckRooms,Options::class.java)



        intent.putExtra("hotel",hotel)

        startActivity(intent)
    }


}
