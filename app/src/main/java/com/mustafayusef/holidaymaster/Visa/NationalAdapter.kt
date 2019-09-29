package com.mustafayusef.holidaymaster.Visa

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Models.country
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.visa_card.view.*
import kotlinx.android.synthetic.main.visa_popup.view.*

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
       // holder.view.Description .text= Visa!!.Description

        holder.view.PriceVisa .text= Visa!!.price.toString()+" $"


        holder.view.detVisa.setOnClickListener {
            val dview: View = View.inflate(context, R.layout.visa_popup, null)
            val builder = context?.let { AlertDialog.Builder(it).setView(dview) }
            val malert= builder?.show()

            malert?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dview?.detailsVisa?.text=Visa!!.Description

//                context?.toast(context?.getResources().getString(R.string.deletSucc))

        }



        // Glide.with(context).load(hotels?.img).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.HotelImage)

        holder.itemView.SelectVisa.setOnClickListener{
            var bundel=Bundle()
            bundel.putSerializable("visa",Visa)
          holder.itemView.findNavController()?.navigate(R.id.fromVisaToFormOne,bundel)
        }

    }



    class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        init {

        }


    }

}