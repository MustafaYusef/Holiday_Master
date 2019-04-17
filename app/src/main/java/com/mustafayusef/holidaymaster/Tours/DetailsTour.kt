package com.mustafayusef.holidaymaster.Tours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.shape.RoundedCornerTreatment
import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_details_tour.*
import kotlinx.android.synthetic.main.tour_card.view.*
import kotlin.math.round
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop




class DetailsTour : AppCompatActivity() {
    var tour: Tours? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mustafayusef.holidaymaster.R.layout.activity_details_tour)

        tour= intent.getSerializableExtra("Tour") as Tours?

        ImageTourD.clipToOutline=true

        var requestOptions = RequestOptions()
       requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(40))
        Glide.with(this).load("https://favorite-holiday.herokuapp.com/"+tour!!.img).apply(requestOptions)
         .into(ImageTourD)


        //ImageTourD.setBackgroundResource(R.drawable.img_rudus)
        NameTourD.text= tour!!.name
        bodyTourD.text= tour!!.body
    }

    fun backToTour(view: View){
        val intent= Intent(this@DetailsTour, Tours::class.java)
        startActivity(intent)
    }
}
