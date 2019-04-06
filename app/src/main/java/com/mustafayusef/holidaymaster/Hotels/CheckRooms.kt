package com.mustafayusef.holidaymaster.Hotels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Adapters.OneWayAdapter
import com.mustafayusef.holidaymaster.Adapters.RoomsAdapter
import com.mustafayusef.holidaymaster.LoginMember.cacheObj.ListCost
import com.mustafayusef.holidaymaster.Models.hotel
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_check_rooms.*


import android.widget.ArrayAdapter
import android.view.Gravity

import android.widget.LinearLayout

import android.app.Dialog
import android.graphics.Point
import kotlinx.android.synthetic.main.bottom_sheet_emp_cov.*
import kotlinx.android.synthetic.main.bottom_sheet_emp_cov.view.*


class CheckRooms : AppCompatActivity() {
  lateinit var hotel:hotel
    // var hotel1:hotel?=null
   // var ListCost= mutableListOf<Long>(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mustafayusef.holidaymaster.R.layout.activity_check_rooms)
        RoomsList.layoutManager= LinearLayoutManager(this)




        if(intent.getSerializableExtra("hotel1") !=null) {
            hotel = intent.getSerializableExtra("hotel1") as hotel
            RoomsList?.adapter = RoomsAdapter(this@CheckRooms, hotel)

        }else{
            hotel = intent.getSerializableExtra("hotelOld") as hotel
            for( i in 0 until hotel.Rooms!!.count()){
            ListCost[i]= hotel.Rooms!![i].cost!!
                println(ListCost)
            }
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
        val intent=Intent(this@CheckRooms,DetailsHotel::class.java)
        startActivity(intent)
    }
    fun ShowOptions(view:View){


        val intent= Intent(this@CheckRooms,Options::class.java)

           for( i in 0 until hotel.Rooms!!.count()){
            hotel.Rooms!![i].cost=ListCost[i]
        }

        intent.putExtra("hotel",hotel)

        startActivity(intent)
    }


//    fun openBottomSheet() {
//
//        val view = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.bottom_sheet_emp_cov , null)
////        val spin1 = view.findViewById(R.id.spin1) as Spinner
////        val spin2 = view.findViewById(R.id.spin2) as Spinner
////        val catList = view.findViewById(R.id.listItems) as ListView
////        val btnDone = view.findViewById(R.id.btnDone) as Button
//        val display = windowManager.defaultDisplay
//        val size = Point()
//        display.getSize(size)
//        val width = size.x
//        val height = size.y
//
//
//        view.minimumHeight=height/2
//        val mBottomSheetDialog = Dialog(
//            this@CheckRooms,
//            com.mustafayusef.holidaymaster.R.style.MaterialDialogSheet
//        )
//        mBottomSheetDialog.setContentView(view)
//        mBottomSheetDialog.setCancelable(true)
//        mBottomSheetDialog.getWindow().setLayout(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM)
//        mBottomSheetDialog.show()
//
////        spin1.setAdapter(ArrayAdapter<String>(this@RepActivity, android.R.layout.simple_dropdown_item_1line, items))
////        spin2.setAdapter(ArrayAdapter<String>(this@RepActivity, android.R.layout.simple_dropdown_item_1line, items))
////
////        catList.setAdapter(categoryListAdapter)
//
//        view.btnDone.setOnClickListener{ mBottomSheetDialog.dismiss() }
//
//    }


}
