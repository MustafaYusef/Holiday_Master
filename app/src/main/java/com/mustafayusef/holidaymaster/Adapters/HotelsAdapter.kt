package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

import com.mustafayusef.holidaymaster.Hotels.DetailsHotel
import com.mustafayusef.holidaymaster.Models.hotel

import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.hotel_card.view.*


class HotelsAdapter(val context: Context, val HotelsFeed:List<hotel>?) : RecyclerView.Adapter<HotelsAdapter.CustomViewHolder>(){
//


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        val cardItem=layoutInflater.inflate(R.layout.hotel_card,parent,false)

        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
        return HotelsFeed?.count()!!

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view. OneContainerHotel.startAnimation(AnimationUtils.loadAnimation(context,R.anim.zoom_in))

//            holder.view. OneContainer.startAnimation(AnimationUtils.loadAnimation(context,R.anim.list_animation))
//            holder.view.LogoAir .startAnimation(AnimationUtils.loadAnimation(context,R.anim.list_animation))

        //val HotelsFeed=HotelsFeed?.sortedWith(compareBy({ it.price }))
        val Hotels=HotelsFeed?.get(position)

        holder.view?.HotelName.text=Hotels?.name.toString()
        if (Hotels?.body!!.length>160){
            holder.view?.BodyHotel.text=Hotels?.body.subSequence(0,160).toString()+".."
        }else{
            holder.view?.BodyHotel.text=Hotels?.body

        }
        holder.view?.ratingBar.rating=Hotels?.stars!!.toFloat()
        if(Hotels.address!!.length>25){
            holder.view?.Location.text=Hotels?.address.subSequence(0,25).toString()+".."
        }else{
            holder.view?.Location.text=Hotels?.address
        }

        if(Hotels.breakfast!!){
            holder.view?.BreakImg.setBackgroundResource(R.drawable.bread2)
        }else{
            holder.view?.BreakImg.setBackgroundResource(R.drawable.bread1)
        }
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(10))
        Glide.with(holder.view)
            .load("https://favorite-holiday.com/server/"+Hotels.img.toString())
            .placeholder(R.drawable.hotel_placeholder)
            .apply(requestOptions).into(holder.view?.HotelImage)





        // Glide.with(context).load(hotels?.img).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.HotelImage)

        holder.itemView.DetailsHotel.setOnClickListener{
            val intent = Intent(context, DetailsHotel::class.java)
//            val b=Bundle.p
            intent.putExtra("hotels",Hotels)
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



