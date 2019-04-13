package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.mustafayusef.holidaymaster.tickets.DetailsTow
import com.mustafayusef.holidaymaster.Models.modelTow
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.cardtowway.view.*


class TowWayAdapter(val context:Context,  val holidayTowWay:List<modelTow>?): RecyclerView.Adapter<TowWayAdapter.CustomViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
       // println(holidayFeed)
        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.cardtowway,parent,false)
        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {

        return holidayTowWay?.count()!!
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
       // val holidaysSort=holidayTowWay?.sortedWith(compareBy({ it.price }))
        holder.view. TowContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in))

        val holidays=holidayTowWay?.get(position)


        holder.view.stopsDep.text= holidays!!.stops.toString()+" Stops"

        holder.view.depTimeDep.text= holidays.depDateAndTime!![0].subSequence(11,holidays.depDateAndTime[0].length)
        holder.view.arrTimeDep .text=
            holidays.arrDateAndTime!![holidays.arrDateAndTime.lastIndex].subSequence(11,holidays.arrDateAndTime[0].length)
        holder.view.AirNameDepDep .text= holidays.depCityName!![0]
        holder.view.durationDep. text=holidays.totalDuration
        holder.view.AirNameArrDep .text= holidays.arrCityName!![holidays.arrCityName.lastIndex]

        Glide.with(holder.view).load(holidays.logoCover).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.LogoAirDep)



        holder.view.stopsRet .text=holidays?.ReturnStops .toString()+" Stops"
           holder.view?.DepTimeRet .text= holidays.ReturnAepDateAndTime!![0].subSequence(11,holidays.depDateAndTime[0].length)
////
////
       holder.view.arrTimeRet.text= holidays.ReturnArrDateAndTime!![holidays.ReturnArrDateAndTime.lastIndex].subSequence(11,holidays.ReturnArrDateAndTime[0].length)
       holder.view.AirNameDepRet.text= holidays.ReturnDepCityName !![0]
        holder.view.AirNameArrRet .text=holidays.ReturnArrCityName !![holidays.ReturnArrCityName.lastIndex]
        holder.view.durationRet.text=holidays.ReturnTotalDuration
        Glide.with(holder.view).load(holidays.ReturnLogoCover).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.returnLogoCover)
        holder.view.priceTow .text=holidays.price+"$"
//       holder.view.AirNameDepRet.text= holidays.depCityName!![0]
//        holder.view.AirNameArrRet .text=holidays.arrCityName!![holidays.arrCityName.lastIndex]
//        holder.view.durationRet.text=holidays.totalDuration
//      Glide.with(holder.view).load(holidays.returnLogoCover).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.returnLogoCover)
//

//        "ReturnStops": 2,
//        "ReturnDepartingAirportName": [
//        "Baghdad Intl."
//        ],
//        "ReturnArrivalAirportName": [
//        "Beirut Rafic Hariri International Airport"
//        ],
//        "ReturnLogoCover": "https://static.flyinstatic.com/common/themes/v2/img/multiAirline.png",
//        "ReturnDepCityName": [
//        "Baghdad",
//        "Cairo",
//        "Amman"
//        ],
//        "ReturnArrCityName": [
//        "Cairo",
//        "Amman",
//        "Beirut"
//        ],
//        "ReturnAepDateAndTime": [
//        "2019-03-20T12:20:00",
//        "2019-03-20T17:05:00",
//        "2019-03-20T23:50:00"
//        ],
//        "ReturnArrDateAndTime": [
//        "2019-03-20T12:20:00",
//        "2019-03-20T17:05:00",
//        "2019-03-20T23:50:00"
//        ],
//        "ReturnAirlineName": [
//        "Egypt Air",
//        "Egypt Air",
//        "Royal Jordanian"
//        ],
//        "ReturnFlightNumber": [
//        "638",
//        "701",
//        "3317"
//        ],
//        "ReturnLayOverTime": [
//        "3h:0m",
//        "5h:30m",
//        "0h:0m"
//        ],
//        "ReturnLayOverMinutes": [
//        "3h:0m",
//        "5h:30m",
//        "0h:0m"
//        ],
//        "ReturnLayOverCity": [
//        "Cairo",
//        "Amman",
//        "Beirut"
//        ],
//        "ReturnArrAirportName": [
//        "Cairo International Airport",
//        "Queen Alia International Airport",
//        "Beirut Rafic Hariri International Airport"
//        ],
//        "ReturnFlightModel": [
//        "Airbus A320",
//        "Boeing 737-800",
//        "Airbus A321"
//        ]



        holder.itemView.detailsTow .setOnClickListener{
            val intent = Intent(context, DetailsTow::class.java)
            intent.putExtra("holidaysTow",holidays)
            context.startActivity(intent)
        }
        // holder.bindModel(holiday[position])

    }



    class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){
//       init {
//        view.setOnClickListener{
//            val intent = Intent(view.context, DetailsTow::class.java)
//                    intent.putExtra("holidaysOne",holiday.get())
//            view.context.startActivity(intent)
//
//        }
//       }

    }
}