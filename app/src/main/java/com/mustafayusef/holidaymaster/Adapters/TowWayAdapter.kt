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
import com.mustafayusef.holidaymaster.Models.DataTow

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.tickets.towWay.ReturnTowWay
import kotlinx.android.synthetic.main.onewaycard.view.*


class TowWayAdapter(val context:Context,  val holidayTowWay:List<DataTow>?,val sessionId:String?,val adult:Int?): RecyclerView.Adapter<TowWayAdapter.CustomViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
       // println(holidayFeed)
        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.onewaycard,parent,false)
        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {

        return holidayTowWay?.count()!!
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
       // val holidaysSort=holidayTowWay?.sortedWith(compareBy({ it.price }))
        holder.view.OneContainer  .startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in))
       holder.view.Details.text="Next"
        holder.view.purch .visibility=View.GONE
        val holidays=holidayTowWay?.get(position)

        holder.view?.priceOne .text=holidays?.price.toString()+"$"
        holder.view?.depTime.text= holidays?.depDateAndTime!![0]
        holder.view?.arrTime.text=holidays?.arrDateAndTime[holidays?.arrDateAndTime.lastIndex]        //  holder.view?.AirNameDep.text=holidays?.departingAirportName[0].subSequence(0,11)
        holder.view?.duration.text=holidays?.totalDuration
        holder.view?.AirNameDep .text=holidays?.depCityName [0]
        holder.view?.airNameArr .text=holidays?.arrCityName [holidays?.arrDateAndTime.lastIndex]
        holder.view?.companyNameOne.text=holidays.AirlineName
        holder.view?.duration .text=holidays.layoverTime[0]
        holder.view?.stops.text="Stops:"+holidays.stops.toString()
        Glide.with(context).load(holidays?.mainLogo).apply(RequestOptions.centerCropTransform().centerInside()).into(holder.view.LogoAir)

        holder.itemView.Details.setOnClickListener{
            val intent = Intent(context, ReturnTowWay::class.java)
            intent.putExtra("TowWay",holidays)
            intent.putExtra("sessionId",sessionId)

            intent.putExtra("adult",adult)
            context.startActivity(intent)
        }


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