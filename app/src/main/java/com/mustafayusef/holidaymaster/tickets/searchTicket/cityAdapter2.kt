package com.mustafayusef.holidaymaster.tickets.searchTicket

import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.Models.Result
import com.mustafayusef.holidaymaster.tickets.oneWay.DetailsOne

import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.airport_card.view.*


import kotlinx.android.synthetic.main.onewaycard.view.*



class cityAdapter2(
    val context:Context,
    var onNoteLisener: OnNoteLisener2,
    val city:List<String>,val country:List<String>,val name:List<String>,val short:List<String>
 ) : RecyclerView.Adapter<cityAdapter2.CustomViewHolder>(){
//
private  var mOnNotlesener2=onNoteLisener

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
           // println(holidayFeed)

            val layoutInflater =LayoutInflater.from(parent.context)
            val cardItem=layoutInflater.inflate(R.layout.airport_card,parent,false)

            return CustomViewHolder(cardItem,mOnNotlesener2)
        }

        override fun getItemCount(): Int {
           // count=holidayFeed!!.count().toString()
        return country.size!!

        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int){

            var country1=country[position]
            var city1=city[position]
            var name1=name[position]
            var short1=short[position]

           holder.view. nameBig?.text=country1+", "+city1+" ($short1)"

            holder.view. nameSmall?.text=name1
        }


    class CustomViewHolder(val view : View, var onNoteLisener2: OnNoteLisener2)
        : RecyclerView.ViewHolder(view), View.OnClickListener{
        var OnNotlesener2:OnNoteLisener2
        override fun onClick(view: View?) {

                onNoteLisener2.onNoteClick2(layoutPosition)
        }

        init {
            this.OnNotlesener2=onNoteLisener2
            view.setOnClickListener(this)

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
interface OnNoteLisener2 {
    fun onNoteClick2( position: Int)
}
        }

