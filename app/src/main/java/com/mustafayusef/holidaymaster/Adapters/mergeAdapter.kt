package com.mustafayusef.holidaymaster.Adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.details_card.view.*


class mergeAdapter(val context: Context,var count: Int, val arrCityName:List<String>,
                        val depCityName:List<String>,val depDateAndTime:List<String>,
                        val arrDateAndTime:List<String>,val airlineLogo:List<String>
                        ,val totalDuration:String,val layOverCity:List<String>,val airlineName:List<String>
                        ,val flightModel:List<String>) : RecyclerView.Adapter<mergeAdapter.CustomViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val cardItem = layoutInflater.inflate(R.layout.details_card, parent, false)
        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {

            return arrCityName.size




    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

    val arrCityName = arrCityName.get(position)
    val depCityName = depCityName.get(position)
    val depDateAndTime = depDateAndTime.get(position)
    val airlineLogo = airlineLogo.get(position)
    val arrDateAndTime = arrDateAndTime.get(position)

    val layOverCity=layOverCity.get(position)
    val airlineName=airlineName.get(position)
    val flightModel=flightModel.get(position)
    "2019-03-13T19:35:00"


    holder.view?.FromTo.text="From "+depCityName+" to "+arrCityName
    holder.view?.depTimeDO .text = depDateAndTime.subSequence(11, depDateAndTime.length-3)
    holder.view?.arrTimeDO .text = arrDateAndTime.subSequence(11, arrDateAndTime.length-3)
    holder.view?.durationDO.text=totalDuration
    holder.view?.AirNameDepDO.text=depCityName
    holder.view?.LayOver.text="Layover:"+layOverCity
    holder.view?.FlightModel.text=flightModel
    holder.view?.AirLineName .text=airlineName
    holder.view?.airNameArrDO .text=arrCityName

    Glide.with(context).load(airlineLogo).apply(RequestOptions.centerCropTransform().circleCrop())
        .into(holder.view.LogoAirDO)





        // holder.view.stopsRet.text = holidays.stops.toString() + " Stops"
        //holder.view.priceOne.text = holidays.price + "$"
//            holder.view.depTime.text = holidays.depDateAndTime[position].subSequence(11, holidays.depDateAndTime[position].length)
//            holder.view.arrTime.text = holidays.arrDateAndTime[position].subSequence(
//                11,
//                holidays.arrDateAndTime[position].length
//            )
//            holder.view.AirNameDep.text = holidays.departingAirportName[position].subSequence(0, 11)
//            holder.view.duration.text = holidays.totalDuration
//            holder.view.airNameArr.text = holidays.arrAirportName[position].subSequence(0, 11)
//
//
//            Glide.with(context).load(holidays.airlineLogo[position]).apply(RequestOptions.centerCropTransform().circleCrop())
//                .into(holder.view.LogoAir)

        //index++


    }
    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    }


}



