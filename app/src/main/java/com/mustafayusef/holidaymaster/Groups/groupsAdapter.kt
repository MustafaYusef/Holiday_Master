package com.mustafayusef.holidaymaster.Groups



import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.Models.group

import com.mustafayusef.holidaymaster.R


import kotlinx.android.synthetic.main.tour_card.view.*


class groupsAdapter(val context:Context,val GroupsFeed:List<group>?) : RecyclerView.Adapter<groupsAdapter.CustomViewHolder>(){
//


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.tour_card,parent,false)

        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
        return GroupsFeed?.count()!!

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view. OneContainerTour.startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))


        val holidays=GroupsFeed?.get(position)
        val img=holidays?.img
        //val img = holidays?.img?.toUrl()
//        holder.view.imageTour.clipToOutline=true
        holder.view.nameTour.text=holidays!!.name
        holder.view.countryTour.text=holidays!!.Country
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(30))




        Glide.with(context).load("https://favorite-holiday.herokuapp.com/"+img)
            .apply(requestOptions).into(holder.view.imageTour)


        holder.itemView.readMore .setOnClickListener{
            var bundle = bundleOf("Group" to holidays)
            holder.itemView.findNavController()?.navigate(R.id.details_group,bundle)


        }

        holder.itemView. SelectTour.setOnClickListener {
            var bundle = bundleOf("Group" to holidays)
            holder.itemView.findNavController()?.navigate(R.id.select_group,bundle)
        }

    }



    class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        init {

        }


    }

}

