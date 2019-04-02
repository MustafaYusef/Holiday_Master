package com.mustafayusef.holidaymaster.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Hotels.Options
import com.mustafayusef.holidaymaster.Models.Option
import com.mustafayusef.holidaymaster.Models.Room
import com.mustafayusef.holidaymaster.Models.hotel
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_check_rooms.*
import kotlinx.android.synthetic.main.activity_options.view.*
import kotlinx.android.synthetic.main.option_card.view.*
import kotlinx.android.synthetic.main.rooms_card.view.*

class OptionsAdapter(val context: Context, val OptionFeed:hotel?) : RecyclerView.Adapter<OptionsAdapter.CustomViewHolder>() {
//


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // println(holidayFeed)

        val layoutInflater = LayoutInflater.from(parent.context)
        val cardItem = layoutInflater.inflate(R.layout.option_card, parent, false)

        return CustomViewHolder(cardItem)
    }

    override fun getItemCount(): Int {
        // count=holidayFeed!!.count().toString()
        return OptionFeed?.options?.count()!!

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        //val holidaysSort=holidayFeed?.sortedWith(compareBy({ it.price }))
        val options = OptionFeed?.options?.get(position)

        holder.view?.nameOption .text = options?.name
        holder.view?.priceOption .text = options?.cost.toString() + "$"
        //holder.view?.bedNo.text=rooms?.beds.toString()


        //Glide.with(context).load(holidays?.logoCover).apply(RequestOptions.centerCropTransform().circleCrop()).into(holder.view.LogoAir)
//
        var OptionFeedUp=OptionFeed
      holder.itemView.switch1.setOnClickListener {
          if(holder.itemView.switch1.isChecked){
              for(i in 0..(OptionFeed?.Rooms!!.count()-1)){
                  OptionFeedUp!!.Rooms!![i].cost=(options?.cost!! * OptionFeed.Nights!!.toInt())+ OptionFeed.Rooms[i].cost!!.toInt()
                 // println(OptionFeed.Rooms[i].cost)

              }
             // RoomsList?.adapter= RoomsAdapter(this@Options,hotel)

          }else{
              for(i in 0..(OptionFeed?.Rooms!!.count()-1)){
                  OptionFeedUp!!.Rooms!![i].cost=OptionFeed.Rooms[i].cost
                 // println(OptionFeed.Rooms[i].cost)

              }
          }
          holder?.OptionFeed=OptionFeedUp

         // holder.view=OptionFeed
      }



    }
//    fun upCost(holder: CustomViewHolder,pos:Int){
//
//    }


    class CustomViewHolder(val view: View,var OptionFeed:hotel?=null) : RecyclerView.ViewHolder(view) {
        init {
            view.switch1 .setOnClickListener {
//                if(view.switch1.isChecked){
                    val intent=Intent(view.context,Options::class.java)
                    intent.putExtra("UpdateRoom",OptionFeed)

                    //view.context.startActivity(intent)
//                }

            }


        }


    }
}