package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.TypedValue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.mustafayusef.holidaymaster.Models.RoundedCornersTransformation
import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.tickets.DetailsOne

import com.mustafayusef.holidaymaster.Models.modelOne
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.Tours.DetailsTour
import com.mustafayusef.holidaymaster.Tours.Select_Tour
import com.squareup.picasso.Picasso


import kotlinx.android.synthetic.main.onewaycard.view.*
import kotlinx.android.synthetic.main.tour_card.view.*
import java.net.URL


class ToursAdapter(val context:Context,val ToursFeed:List<Tours>?) : RecyclerView.Adapter<ToursAdapter.CustomViewHolder>(){
//


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater =LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.tour_card,parent,false)

        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
        return ToursFeed?.count()!!

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view. OneContainerTour.startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in))
        //holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_in_list))

        // holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.left_to_right))


        val holidays=ToursFeed?.get(position)
        val img=holidays?.img
        //val img = holidays?.img?.toUrl()
//        holder.view.imageTour.clipToOutline=true
        holder.view.nameTour.text=holidays!!.name
        holder.view.countryTour.text=holidays!!.Country
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(30))

//        context: Context, radius: Int, margin: Int,
//        cornerType: CornerType = CornerType.ALL
//        val borderWidth= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4F, context.resources.displayMetrics).toInt()
//        Glide.with(context)
//            .asBitmap()
//            .load("https://favorite-holiday.herokuapp.com/"+img)
//            .apply(RequestOptions.centerCropTransform())
//            .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(context,
//                40, 10,RoundedCornersTransformation.CornerType.TOP_LEFT)))
//            .into(object : BitmapImageViewTarget(holder.view.imageTour) {
//
//            })



       Glide.with(context).load("https://favorite-holiday.herokuapp.com/"+img)
           .apply(requestOptions).into(holder.view.imageTour)

      // Picasso.with(context) .load().resize(100,100) .into(holder.view.imageTour)
        holder.itemView.readMore .setOnClickListener{
            val intent = Intent(context, DetailsTour::class.java)
            intent.putExtra("Tour",holidays)
            context.startActivity(intent)
        }

        holder.itemView. SelectTour.setOnClickListener {
            val intent = Intent(context, Select_Tour::class.java)
            intent.putExtra("Tour",holidays)
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

