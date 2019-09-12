package com.mustafayusef.holidaymaster.Adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.onewaycard.view.*
//holder.rv?.locationList!!.adapter= OneDetailsAdapter(context, holiday.depDateAndTime,
//holiday.arrDateAndTime,holiday.layoverTime,holiday.depCityName
//,holiday.arrCityName,holiday.totalDuration,holiday.logos,holiday.airNames)

class OneDetailsAdapter(
    val context: Context, val depDateAndTime:List<String>,
    val arrDateAndTime:List<String>, val layoverTime:List<String>,
    val depCityName:List<String>, val arrCityName:List<String>,val totalDuration:List<String>
    , val logos:List<String>, val airNames:List<String>
    ) : RecyclerView.Adapter<OneDetailsAdapter.CustomViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val cardItem = layoutInflater.inflate(R.layout.onewaycard, parent, false)
        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {

               return arrCityName.size



    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val arrCityName = arrCityName.get(position)
        val depCityName = depCityName.get(position)
        val depDateAndTime = depDateAndTime.get(position)
        val airlineLogo = logos.get(position)
        val arrDateAndTime = arrDateAndTime.get(position)

        val layOverTime=layoverTime.get(0)
        val airlineName=airNames.get(position)
        val totalDuration=totalDuration.get(position)

        holder.view?.line.visibility=View.VISIBLE
        holder.view?.bottonsCon.visibility=View.GONE


        holder.view?.depTime.text= depDateAndTime
        holder.view?.arrTime.text=arrDateAndTime
        holder.view?.duration.text=totalDuration
        holder.view?.AirNameDep .text=depCityName
        holder.view?.airNameArr .text=arrCityName
        holder.view?.companyNameOne.text=airlineName
        holder.view?.duration .text=layOverTime

        Glide.with(context).load(airlineLogo).apply(RequestOptions.centerCropTransform().centerInside()).into(holder.view.LogoAir)

        // Glide.with(context).load(holidays?.mainLogo).apply(RequestOptions.centerCropTransform().centerInside()).into(holder.view.LogoAir)



       // holder.view?.mounthN.text=depDateAndTime.toString()
//        when(mN){
//            "1"->holder.view?.mounthN.text="Jan"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "2"->holder.view?.mounthN.text="Feb"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "3"->holder.view?.mounthN.text="Mar"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "4"->holder.view?.mounthN.text="April"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "5"->holder.view?.mounthN.text="May"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "6"->holder.view?.mounthN.text="June"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "7"->holder.view?.mounthN.text="July"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "8"->holder.view?.mounthN.text="August"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "9"->holder.view?.mounthN.text="Sep"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "10"->holder.view?.mounthN.text="Oct"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "11"->holder.view?.mounthN.text="Nov"+" "+depDateAndTime.subSequence(8, 10).toString()
//            "12"->holder.view?.mounthN.text="Dec"+" "+depDateAndTime.subSequence(8, 10).toString()
//
//
//
//
//        }

    }
    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    }


}



