package com.mustafayusef.holidaymaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Adapters.OneDetailsAdapter
import com.mustafayusef.holidaymaster.Models.modelOne
import com.mustafayusef.holidaymaster.Models.modelTow
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_details_one.*
import kotlinx.android.synthetic.main.activity_details_tow.*

class DetailsTow : AppCompatActivity() {
    lateinit var holiday: modelTow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_tow)

        var i:Int=0
        this.holiday = intent.getSerializableExtra("holidaysTow") as modelTow




        detailsTowList.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
        detailsTowList.adapter= OneDetailsAdapter(this@DetailsTow, holiday.arrCityName!!,
            holiday.depCityName!!, holiday.depDateAndTime!!, holiday.arrDateAndTime!!, holiday.airlineLogo!!, holiday.totalDuration!!
        )




        purTow.text="Purchase for "+holiday.price+" $"

    }
    fun backToTow(view: View){
        val intent= Intent(this@DetailsTow,showHoliday::class.java)
        startActivity(intent)

    }

}
