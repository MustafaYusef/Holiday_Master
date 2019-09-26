package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.Models.modelTow
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.details_card.view.*
import kotlinx.android.synthetic.main.header_ticket.view.*


class ReturnDetails(val context: Context, val holiday: modelTow) : RecyclerView.Adapter<ReturnDetails.CustomViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
               val cardItem = layoutInflater.inflate(R.layout.details_card, parent, false)
               return CustomViewHolder(cardItem)
//           if(viewType==0){
//               val layoutInflater = LayoutInflater.from(parent.context)
//               val cardItem = layoutInflater.inflate(R.layout.details_card, parent, false)
//               return CustomViewHolder(cardItem)
//           }else{
//               val layoutInflater = LayoutInflater.from(parent.context)
//               val cardItem = layoutInflater.inflate(R.layout.header_ticket, parent, false)
//               return CustomViewHolder(cardItem)
//           }

    }

    override fun getItemCount(): Int {

        return (holiday.depCityName!!.size+holiday.ReturnAepDateAndTime!!.size)

    }

//    override fun getItemViewType(position: Int): Int {
//        var viewType=0
//
//        if(position==holiday.depCityName!!.size)viewType=1
//        return viewType
//    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

 if(position<holiday.layOverMinutes!!.size){
    val arrCityName = holiday.arrCityName!!.get(position)
    val depCityName = holiday.depCityName!!.get(position)
    val depDateAndTime =holiday. depDateAndTime!!.get(position)
    val airlineLogo = holiday.airlineLogo!!.get(position)
    val arrDateAndTime = holiday.arrDateAndTime!!.get(position)



    holder.view?.FromTo.text="From "+depCityName+" to "+arrCityName
    holder.view?.depTimeDO .text = depDateAndTime.subSequence(11, depDateAndTime.length-3)
    holder.view?.arrTimeDO .text = arrDateAndTime.subSequence(11, arrDateAndTime.length-3)
    holder.view?.durationDO.text=holiday.totalDuration
    holder.view?.AirNameDepDO.text=depCityName
    holder.view?.airNameArrDO .text=arrCityName
    Glide.with(context).load(airlineLogo).apply(RequestOptions.centerCropTransform().circleCrop())
        .into(holder.view.LogoAirDO)
}


    }
    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    }


}



