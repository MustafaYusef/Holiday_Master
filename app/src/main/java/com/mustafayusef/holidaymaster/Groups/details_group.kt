package com.mustafayusef.holidaymaster.Groups

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.Models.group
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_details_group.*

class details_group : Fragment() {
    var group: group? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_details_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        group = arguments!!.getSerializable("Group") as group?



        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(40))
        Glide.with(context!!).load("https://favorite-holiday.com/server/"+group!!.img).apply(requestOptions)
            .into(ImageGroupD)


        //ImageTourD.setBackgroundResource(R.drawable.img_rudus)
        NameGroupD.text= group!!.name
        bodyGroupD.text= group!!.body

        showPdf?.setOnClickListener {
            var browserIntent =  Intent(Intent.ACTION_VIEW,
                Uri.parse("https://favorite-holiday.com//"+group?.pserverdf));
            startActivity(browserIntent);

        }
    }
}
