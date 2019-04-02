package com.mustafayusef.holidaymaster.Hotels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.OptionsAdapter
import com.mustafayusef.holidaymaster.Adapters.RoomsAdapter
import com.mustafayusef.holidaymaster.Models.hotel
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_check_rooms.*
import kotlinx.android.synthetic.main.activity_options.*

class Options : AppCompatActivity() {
   lateinit var hotel:hotel
     var hotel1:hotel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

            hotel=intent.getSerializableExtra("hotel") as hotel
            OptionList.layoutManager= LinearLayoutManager(this)
            OptionList?.adapter= OptionsAdapter(this@Options,hotel)
        RoomsList?.adapter= RoomsAdapter(this@Options,hotel)





        if(intent.getSerializableExtra("UpdateRoom")!=null){
            hotel1=intent?.getSerializableExtra("UpdateRoom") as hotel

        }else{
            hotel1=hotel

        }

    }

       fun camcelOp(view: View){
           super.onBackPressed()
       }
    fun applay(view: View){
        val intent= Intent(this@Options,CheckRooms::class.java)
        intent.putExtra("hotel1",hotel1)
        //view.context.startActivity(intent)
        //RoomsList?.adapter= RoomsAdapter(this@Options,hotel)
       startActivity(intent)
    }

}
