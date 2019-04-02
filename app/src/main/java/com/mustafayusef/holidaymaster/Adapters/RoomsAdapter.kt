package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.DetailsOne
import com.mustafayusef.holidaymaster.Models.Room
import com.mustafayusef.holidaymaster.Models.hotel
import com.mustafayusef.holidaymaster.Models.modelOne
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.onewaycard.view.*
import kotlinx.android.synthetic.main.rooms_card.view.*

class RoomsAdapter(val context: Context, val RoomFeed:hotel?) : RecyclerView.Adapter<RoomsAdapter.CustomViewHolder>(){
//


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater = LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.rooms_card,parent,false)

        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
     return RoomFeed?.Rooms?.count()!!

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        //val holidaysSort=holidayFeed?.sortedWith(compareBy({ it.price }))
        val rooms=RoomFeed?.Rooms?.get(position)

        holder.view?.nameRoom.text=rooms?.name
        holder.view?.priceRoom.text=rooms?.cost.toString()+"$"
        holder.view?.bedNo.text=rooms?.beds.toString()



        //Glide.with(context).load(holidays?.logoCover).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.LogoAir)
//
//        holder.itemView.b .setOnClickListener{
//            val intent = Intent(context, DetailsOne::class.java)
//            intent.putExtra("holidaysTow",holidays)
//            context.startActivity(intent)
//        }

    }



    class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        init {

        }


    }

}