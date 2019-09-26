package com.mustafayusef.holidaymaster.login.profile.dashBoard.adaptersProfile

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Models.otherOrd.otherOrderRes

import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.other_order_card.view.*


import kotlinx.android.synthetic.main.ticket_order_card.view.*
import kotlinx.android.synthetic.main.ticket_order_card.view.conOr


class OtherTicketAdapter(
    val context:Context,
    val holidayFeed: otherOrderRes
 ) : RecyclerView.Adapter<OtherTicketAdapter.CustomViewHolder>(){
//


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
           // println(holidayFeed)

            val layoutInflater =LayoutInflater.from(parent.context)
            val cardItem=layoutInflater.inflate(R.layout.other_order_card,parent,false)

            return CustomViewHolder(cardItem)
        }

        override fun getItemCount(): Int {
           // count=holidayFeed!!.count().toString()
        return holidayFeed?.result!!.size!!

        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.view.conOr.startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in))
          var data=holidayFeed.result[position]
            holder.view.TimeOT.text=data.uptime
            holder.view.priceOT.text=data.price.toString() +"$"
            if(data.payed){
                holder.view. statuseOT.text="App"
            }else{
                holder.view. statuseOT.text="Waiting"
            }

            holder.view. typeO.text=data.type
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

