package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Models.modelTow
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.card_nested.view.*
import kotlinx.android.synthetic.main.nested.view.*
import java.text.SimpleDateFormat

class MainAdapter(val context: Context, val holiday: modelTow, val header:List<String>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

//    val byDates = locationList.groupBy { it["time"] }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        // Update date label
//        val sdf = SimpleDateFormat("MM/dd/yyyy")
//        val dateList = byDates.values.toMutableList()
        holder.date.text = header[position]
        // Create vertical Layout Manager

        holder.rv?.locationList?.layoutManager = LinearLayoutManager(holder.itemView.context)
        if(position==0){
//                    detailsTowList.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
////        detailsTowList.addItemDecoration(detailsTowList.)
            holder.rv?.locationList!!.adapter= OneDetailsAdapter(context, holiday.arrCityName!!,
            holiday.depCityName!!, holiday.depDateAndTime!!, holiday.arrDateAndTime!!
            , holiday.airlineLogo!!, holiday.totalDuration!!, holiday.layOverCity!!, holiday.airlineName!!
            , holiday.flightModel!!)
        }else{
            holder.rv?.locationList!!.adapter= OneDetailsAdapter(context,
            holiday.ReturnArrCityName!!,
            holiday.ReturnDepCityName!!, holiday.ReturnAepDateAndTime!!, holiday.ReturnArrDateAndTime!!
            , holiday.ReturnairlineLogo!!, holiday.ReturnTotalDuration!!, holiday.ReturnLayOverCity!!
            , holiday.ReturnAirlineName!!, holiday.ReturnFlightModel!!)

        }
        // Access RecyclerView Adapter and load the data
//        var adapter = OneDetailsAdapter(dateList[position] as ArrayList<HashMap<String, String>>)
//        holder.rv?.adapter = adapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_nested, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return header.count()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val date = itemView.findViewById<TextView>(R.id.header)
        val rv = itemView.findViewById<RecyclerView>(R.id.locationList)
    }
}