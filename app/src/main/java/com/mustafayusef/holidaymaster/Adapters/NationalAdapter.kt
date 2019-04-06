package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Hotels.DetailsHotel
import com.mustafayusef.holidaymaster.Models.country
import com.mustafayusef.holidaymaster.Models.hotel
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.hotel_card.view.*
import kotlinx.android.synthetic.main.visa_card.view.*

class NationalAdapter(val context: Context, val countryFeed:List<country>?) : RecyclerView.Adapter<NationalAdapter.CustomViewHolder>(){
//


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.visa_card,parent,false)

        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
        return countryFeed?.count()!!

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//            holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.list_animation))
//            holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.list_animation))

        //val HotelsFeed=HotelsFeed?.sortedWith(compareBy({ it.price }))
        val Visa=countryFeed?.get(position)
        holder.view.nameCountry.text= Visa!!.name
        holder.view.Country .text= Visa!!.country
        holder.view.Approv .text= Visa!!.APPROVED
        holder.view.Description .text= Visa!!.Description

        holder.view.PriceVisa .text= Visa!!.price.toString()+" $"






        // Glide.with(context).load(Hotels?.img).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.HotelImage)

//        holder.itemView.SelectVisa.setOnClickListener{
//            val intent = Intent(context, DetailsHotel::class.java)
////            val b=Bundle.p
//            intent.putExtra("Hotels",Hotels)
//            context.startActivity(intent)
//        }

    }



    class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        init {

        }


    }

}