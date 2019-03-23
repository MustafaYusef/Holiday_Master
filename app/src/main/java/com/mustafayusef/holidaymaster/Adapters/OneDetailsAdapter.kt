package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.mustafayusef.holidaymaster.DetailsTow
import com.mustafayusef.holidaymaster.Models.modelOne
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.onewaycard.view.*









    class OneDetailsAdapter(val context: Context, val holidayFeed: modelOne,var stops:Int) : RecyclerView.Adapter<OneDetailsAdapter.CustomViewHolder>() {
        //
//        val titels = listOf<String>("mustafa", "yusef", "latif")
//        private var lastPosition = -1
//        private val FADE_DURATION: Long = 1000
    var index:Int=0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            println(holidayFeed)

            val layoutInflater = LayoutInflater.from(parent.context)
            val cardItem = layoutInflater.inflate(R.layout.onewaycard, parent, false)
            return CustomViewHolder(cardItem)
        }

        override fun getItemCount(): Int {

            return holidayFeed.stops.toInt()+1

        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

            val holidays = holidayFeed

           // holder.view.stopsRet.text = holidays.stops.toString() + " Stops"
            //holder.view.priceOne.text = holidays.price + "$"
            holder.view.depTime.text = holidays.depDateAndTime[stops].subSequence(11, holidays.depDateAndTime[stops].length)
            holder.view.arrTime.text = holidays.arrDateAndTime[stops].subSequence(
                11,
                holidays.arrDateAndTime[stops].length
            )
            holder.view.AirNameDep.text = holidays.departingAirportName[stops].subSequence(0, 11)
            holder.view.duration.text = holidays.totalDuration
            holder.view.airNameArr.text = holidays.arrAirportName[stops].subSequence(0, 11)


            Glide.with(context).load(holidays.airlineLogo[stops]).apply(RequestOptions.centerCropTransform().circleCrop())
                .into(holder.view.LogoAir)

           // index++


        }
        class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        }


    }



