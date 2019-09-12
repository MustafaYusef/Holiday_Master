package com.mustafayusef.holidaymaster.Adapters



import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Models.ResultBug

import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.buggage_card.view.*


class buggageAdapter(val context: Context, val buggage:List<ResultBug>?) : RecyclerView.Adapter<buggageAdapter.CustomViewHolder>(){
//


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.buggage_card,parent,false)

        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
        return buggage?.count()!!

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
       // holder.view.OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))


        val buggage=buggage?.get(position)
           holder.view.rootBugg.text=buggage!!.root
        holder.view.Kgcabin.text=buggage!!.ADTCabin
        holder.view.adtCheck.text=buggage!!.ADTCheckIn
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

