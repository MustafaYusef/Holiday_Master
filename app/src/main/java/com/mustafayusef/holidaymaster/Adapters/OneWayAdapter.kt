package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.mustafayusef.holidaymaster.DetailsTow
import com.mustafayusef.holidaymaster.Models.modelOne
import com.mustafayusef.holidaymaster.R


import kotlinx.android.synthetic.main.onewaycard.view.*



class OneWayAdapter(val context:Context,val holidayFeed:List<modelOne> ) : RecyclerView.Adapter<OneWayAdapter.CustomViewHolder>(){
//
val titels= listOf<String>("mustafa","yusef","latif")
    private var lastPosition = -1
    private val FADE_DURATION:Long = 1000


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            println(holidayFeed)

            val layoutInflater =LayoutInflater.from(parent.context)
            val cardItem=layoutInflater.inflate(R.layout.onewaycard,parent,false)
            return CustomViewHolder(cardItem)
        }

        override fun getItemCount(): Int {
           // count=holidayFeed!!.count().toString()
        return holidayFeed!!.count()

        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

//            val movieAvatarImage : View = holder.findViewById(R.id.LogoAir)
//            val t=titels.get(position)
//            holder.view.stopsText.text=t
            val holidaysSort=holidayFeed.sortedWith(compareBy({ it.stops }))
                      val holidays=holidaysSort.get(position)

                holder.view.stopsRet.text=holidays.stops.toString()+" Stops"
            holder.view.priceOne .text=holidays.price+"$"
            holder.view.depTime.text=holidays.depDateAndTime[0].subSequence(11,holidays.depDateAndTime[0].length)
            holder.view.arrTime.text=holidays.arrDateAndTime[holidays.arrDateAndTime.lastIndex].subSequence(11,holidays.arrDateAndTime[0].length)
            holder.view.AirNameDep.text=holidays.departingAirportName[0].subSequence(0,11)
            holder.view.duration.text=holidays.totalDuration
            holder.view.airNameArr .text=holidays.arrAirportName[holidays.arrAirportName.lastIndex].subSequence(0,11)


            Glide.with(context).load(holidays.airlineLogo[0]).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.LogoAir)
            holder.itemView.Details.setOnClickListener{
                val intent = Intent(context, DetailsTow::class.java)
                    intent.putExtra("holidaysTow",holidays)
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

