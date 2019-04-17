package com.mustafayusef.holidaymaster.Hotels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.RoomsAdapter
import com.mustafayusef.holidaymaster.LoginMember.cacheObj.ListCost
import com.mustafayusef.holidaymaster.Models.hotel
import kotlinx.android.synthetic.main.activity_check_rooms.*


import android.view.Gravity

import android.widget.LinearLayout

import android.app.Dialog
import android.graphics.Point
import com.mustafayusef.holidaymaster.Adapters.OptionsAdapter
import kotlinx.android.synthetic.main.activity_options.view.*


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
//        if(intent.getSerializableExtra("UpdateRoom")!=null){
//            hotel=intent?.getSerializableExtra("UpdateRoom") as hotel
//            RoomsList?.adapter = RoomsAdapter(this@CheckRooms, hotel)
//        }else{
//            hotel=hotel
//
//        }

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


//        val intent= Intent(this@CheckRooms,Options::class.java)
//
           for( i in 0 until hotel.Rooms!!.count()){
            hotel.Rooms!![i].cost=ListCost[i]
        }
//
//        intent.putExtra("hotel",hotel)
//
//        startActivity(intent)


                val view = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.bottom_sheet_options , null)
//        val spin1 = view.findViewById(R.id.spin1) as Spinner
//        val spin2 = view.findViewById(R.id.spin2) as Spinner
//        val catList = view.findViewById(R.id.listItems) as ListView
//        val btnDone = view.findViewById(R.id.btnDone) as Button
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y


        view.minimumHeight=600
        val mBottomSheetDialog = Dialog(
            this@CheckRooms,
            com.mustafayusef.holidaymaster.R.style.MaterialDialogSheet
        )
        mBottomSheetDialog.setContentView(view)
        mBottomSheetDialog.setCancelable(true)
        mBottomSheetDialog.window.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mBottomSheetDialog.window.setGravity(Gravity.BOTTOM)
        view.OptionList.layoutManager= LinearLayoutManager(this)
        view.OptionList.adapter = OptionsAdapter(this@CheckRooms, hotel)
        mBottomSheetDialog.show()

//        spin1.setAdapter(ArrayAdapter<String>(this@RepActivity, android.R.layout.simple_dropdown_item_1line, items))
//        spin2.setAdapter(ArrayAdapter<String>(this@RepActivity, android.R.layout.simple_dropdown_item_1line, items))
//
//        catList.setAdapter(categoryListAdapter)

        view.ApplayOp.setOnClickListener{ mBottomSheetDialog.dismiss()
            RoomsList.adapter!!.notifyDataSetChanged()
        }
        view.CancelOp.setOnClickListener {
            mBottomSheetDialog.dismiss()
        }

    }


//    fun openBottomSheet() {
//
//        val view = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.bottom_sheet_options , null)
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
