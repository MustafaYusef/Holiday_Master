package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.Models.Data1
import com.mustafayusef.holidaymaster.tickets.oneWay.DetailsOne

import com.mustafayusef.holidaymaster.R


import kotlinx.android.synthetic.main.onewaycard.view.*



class OneWayAdapter(val context:Context,val holidayFeed:List<Data1>?,val SessionId:String?,val adult:Int?) : RecyclerView.Adapter<OneWayAdapter.CustomViewHolder>(){
//


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
           // println(holidayFeed)

            val layoutInflater =LayoutInflater.from(parent.context)
            val cardItem=layoutInflater.inflate(R.layout.onewaycard,parent,false)

            return CustomViewHolder(cardItem)
        }

        override fun getItemCount(): Int {
           // count=holidayFeed!!.count().toString()
        return holidayFeed?.count()!!

        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.view.OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in))
            //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

            // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))

            val holidaysSort=holidayFeed?.sortedWith(compareBy({ it.price }))
                      val holidays=holidaysSort?.get(position)

              //  holder.view?.stops.text=holidays?.stops.toString()+" Stops"
            holder.view?.priceOne .text=holidays?.price.toString()+"$"
            holder.view?.depTime.text= holidays?.depDateAndTime!![0]
            holder.view?.arrTime.text=holidays?.arrDateAndTime[holidays?.arrDateAndTime.lastIndex]        //  holder.view?.AirNameDep.text=holidays?.departingAirportName[0].subSequence(0,11)
            holder.view?.duration.text=holidays?.totalDuration
            holder.view?.AirNameDep .text=holidays?.depCityName [0]
            holder.view?.airNameArr .text=holidays?.arrCityName [holidays?.arrDateAndTime.lastIndex]
            holder.view?.companyNameOne.text=holidays.AirlineName
            holder.view?.duration .text=holidays.layoverTime[0]
            holder.view?.stops.text="Stops:"+holidays.stops.toString()
            Glide.with(context).load(holidays?.mainLogo).apply(RequestOptions.centerCropTransform().centerInside()).into(holder.view.LogoAir)

            holder.itemView.Details.setOnClickListener{
                val intent = Intent(context, DetailsOne::class.java)
                    intent.putExtra("oneway",holidays)
                intent.putExtra("Id",holidays._id)
                intent.putExtra("adult",adult)
                intent.putExtra("sessionId",SessionId)
                context.startActivity(intent)

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

