package com.mustafayusef.holidaymaster.Hotels

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
        hotel=intent.getSerializableExtra("hotel") as hotel

        OptionList.layoutManager= LinearLayoutManager(this)
        OptionList?.adapter= OptionsAdapter(this@Options,hotel)
    }

       fun camcelOp(view: View){
           super.onBackPressed()
       }
    fun applay(view: View){
      //updateCost(hotel.Rooms)
        super.onBackPressed()
    }

}
