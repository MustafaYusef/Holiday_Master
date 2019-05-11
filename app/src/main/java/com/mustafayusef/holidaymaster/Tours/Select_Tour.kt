package com.mustafayusef.holidaymaster.Tours

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.OptionsAdapter
import com.mustafayusef.holidaymaster.LoginMember
import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_check_rooms.*
import kotlinx.android.synthetic.main.activity_options.view.*
import kotlinx.android.synthetic.main.activity_options.view.CancelOp
import kotlinx.android.synthetic.main.activity_select__tour.*
import kotlinx.android.synthetic.main.bottom_sheet_date_tour.view.*
import kotlinx.android.synthetic.main.show_national.view.*

class Select_Tour : AppCompatActivity() {
    var tour: Tours? = null
    var date=""
    var index=0
    var seat:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select__tour)
         tour= intent.getSerializableExtra("Tour") as Tours
        //priceButton.visibility=View.INVISIBLE
        wrap.visibility=View.INVISIBLE

        seat=(tour!!.Data?.get(index)!!.ToursSets)!!.toDouble()

        priceAdult.text= tour!!.price!!.toString()+" $"
        priceChild.text= tour!!.priceCh!!.toString()+" $"



        subAd.setOnClickListener {
            var adnum=adNo.text.toString().toInt()

            if(adNo.text.toString().toInt()>1){
                adnum--
                seat++
                seatsNo.text="Available Seats:"+seat
                adNo.text=adnum.toString()
                priceButton.text= ((priceButton.text.toString().subSequence(0,priceButton.text.toString().length-2).toString().toInt() -tour!!.price!!.toInt()).toString()+" $")

            }


        }
        plusAd.setOnClickListener {
            var adnum=adNo.text.toString().toInt()


            if(adNo.text.toString().toInt()<10){
                adnum++
                seat--
                seatsNo.text="Available Seats:"+seat
                adNo.text=adnum.toString()
                priceButton.text= ((priceButton.text.toString().subSequence(0,priceButton.text.toString().length-2).toString().toInt() +tour!!.price!!.toInt()).toString()+" $")

            }


        }


        plusCh.setOnClickListener {
            var adnum=chNo.text.toString().toInt()


            if(chNo.text.toString().toInt()<10){
            adnum++
                seat-=0.5
            seatsNo.text="Available Seats:"+seat
            chNo.text=adnum.toString()
                priceButton.text= ((priceButton.text.toString().subSequence(0,priceButton.text.toString().length-2).toString().toInt() +tour!!.priceCh!!.toInt()).toString()+" $")

        }


    }


        subch.setOnClickListener {
            var adnum=chNo.text.toString().toInt()

            if(chNo.text.toString().toInt()>0){
                adnum--
                seat+=0.5
                seatsNo.text="Available Seats:"+seat
                chNo.text=adnum.toString()
                priceButton.text= ((priceButton.text.toString().subSequence(0,priceButton.text.toString().length-2).toString().toInt() -tour!!.priceCh!!.toInt()).toString()+" $")

            }


        }


    }
    @SuppressLint("SetTextI18n")
    fun selectDate(view: View){


        val view = layoutInflater.inflate(R.layout.bottom_sheet_date_tour , null)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y


        view.minimumHeight=600
        val mBottomSheetDialog = Dialog(
            this@Select_Tour,
            R.style.MaterialDialogSheet
        )
        mBottomSheetDialog.setContentView(view)
        mBottomSheetDialog.setCancelable(true)
        mBottomSheetDialog.window.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mBottomSheetDialog.window.setGravity(Gravity.BOTTOM)
//        view.OptionList.layoutManager= LinearLayoutManager(this)
//        view.OptionList.adapter = OptionsAdapter(this@Select_Tour, hotel)
        view.datesPickers.minValue = 0
        view.datesPickers.maxValue = tour!!.Data!!.size-1
        view.datesPickers.wrapSelectorWheel = true
        val datesArray= mutableListOf<String>()
        for(i in 0 until  tour!!.Data!!.size){
            datesArray.add(tour!!.Data!![i].ToursDate.toString())
        }
        view.datesPickers.displayedValues = datesArray.toTypedArray()
        date= datesArray[0]
        view.datesPickers.setOnValueChangedListener { picker, oldVal, newVal ->
            //
            //Display the newly selected number to text view
            date = datesArray[newVal]
            // println(country +"   cooodkl,dl")
            index=newVal
        }


        mBottomSheetDialog.show()

        view.ApplayT.setOnClickListener {
            dateTour.text=date
            wrap.visibility=View.VISIBLE
            seatsNo.text="Available Seats:"+((tour!!.Data?.get(index)!!.ToursSets)!!.toInt())
            adNo.text="1"
            priceButton.text=tour!!.price.toString()+" $"
            mBottomSheetDialog.dismiss()
        }


//        spin1.setAdapter(ArrayAdapter<String>(this@RepActivity, android.R.layout.simple_dropdown_item_1line, items))
//        spin2.setAdapter(ArrayAdapter<String>(this@RepActivity, android.R.layout.simple_dropdown_item_1line, items))
//
//        catList.setAdapter(categoryListAdapter)


        view.CancelT.setOnClickListener {
            mBottomSheetDialog.dismiss()
        }

    }



}
