package com.mustafayusef.holidaymaster.login.profile.dashBoard.adaptersProfile

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Models.TicketOrder.TicketOrderRes

import com.mustafayusef.holidaymaster.R


import kotlinx.android.synthetic.main.ticket_order_card.view.*


class orderTicketAdapter(
    val context:Context,
    val holidayFeed: TicketOrderRes
 ) : RecyclerView.Adapter<orderTicketAdapter.CustomViewHolder>(){
//


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
           // println(holidayFeed)

            val layoutInflater =LayoutInflater.from(parent.context)
            val cardItem=layoutInflater.inflate(R.layout.ticket_order_card,parent,false)

            return CustomViewHolder(cardItem)
        }

        override fun getItemCount(): Int {
           // count=holidayFeed!!.count().toString()
        return holidayFeed?.result!!.size!!

        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.view.conOr.startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in))
          var data=holidayFeed.result[position]
            holder.view.TimeO.text=data.uptime
            holder.view.priceO.text=data.price+"$"
            holder.view. statuse.text=data.issue
           holder.view.viewT?.setOnClickListener {
               var bundel=Bundle()

               if(data.type=="holiday"){
//                   https://fholiday.herokuapp.com/holidayPhone?item=5d823afbfdd2895f609517b7
                   bundel.putString("url","https://fholiday.herokuapp.com/holidayPhone?item=${data._id}")
               }else{
                   bundel.putString("url","https://fholiday.herokuapp.com/ShowTicketPhone?item=${data.AirPNR}")
               }

               holder.view.findNavController().navigate(R.id.webView2,bundel)
           }


        }
//  Glide.with(fragment)
//    .load(currentUrl)
//    .override(imageWidthPixels, imageHeightPixels)
//    .into(imageView);
//        fun setMovies(data: List<modelOne>) {
//            holiday.addAll(data)
//            notifyDataSetChanged()
//        }


    class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view){
            init {

            }


    }
//        inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//            val movieTitleTxt : TextView = itemView.findViewById(R.id.fromTime)
////            val movieGenreTxt : TextView = itemView.findViewById(R.id.movieGenre)
////            val movieYearTxt : TextView = itemView.findViewById(R.id.movieYear)
////            val movieAvatarImage : ImageView = itemView.findViewById(R.id.movieAvatar)



//            fun bindModel(movie: modelOne) {
//                data= listOf(movie)
//                movieTitleTxt.text = movie.stops.toString()
//                print(movie.stops.toString())
////                movieGenreTxt.text = modelOne.genre
////                movieYearTxt.text = modelOne.year
////                Picasso.get().load(modelOne.poster).into(movieAvatarImage)
//            }
        }

