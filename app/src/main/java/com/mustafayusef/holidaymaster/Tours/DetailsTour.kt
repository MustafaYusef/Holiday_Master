package com.mustafayusef.holidaymaster.Tours

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_details_tour.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.CenterCrop




class DetailsTour : Fragment() {
    var tour: Tours? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_details_tour, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tour= arguments!!.getSerializable ("Tour") as Tours?



        var requestOptions = RequestOptions()
       requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(40))
        Glide.with(this).load("https://favorite-holiday.herokuapp.com/"+tour!!.img).apply(requestOptions)
         .into(ImageTourD)


        //ImageTourD.setBackgroundResource(R.drawable.img_rudus)
        NameTourD.text= tour!!.name
        bodyTourD.text= tour!!.body
    }

}
