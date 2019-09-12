package com.mustafayusef.holidaymaster.Hotels

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Adapters.HotelsAdapter
import com.mustafayusef.holidaymaster.Models.group
import com.mustafayusef.holidaymaster.Models.hotel
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.login.LoginViewModel
import com.mustafayusef.holidaymaster.login.LoginViewModelFactory
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_show_hotels.*
import okhttp3.*
import java.io.IOException
import okhttp3.FormBody


class ShowHotels : Fragment(),lesener {
    override fun OnStart() {
        animation_viewHotel?.visibility=View.VISIBLE
        animation_viewHotel?.playAnimation()
    }

    override fun onFailer(message: String) {
        animation_viewHotel?.visibility=View.GONE
        animation_viewHotel?.pauseAnimation()
        noResultHot?.text="no Result found"
    }

    override fun onSucsess(Response: List<hotel>) {
        Hotels_list?.layoutManager= LinearLayoutManager(context)
        Hotels_list?.adapter= HotelsAdapter(context!!,Response)
        animation_viewHotel?.visibility=View.GONE
        animation_viewHotel?.pauseAnimation()
        noResultHot?.text=Response?.size.toString()+" Result found"
    }

    var checkIn: String = ""
    var checkOut: String = ""
    var AdultNo: Int = 0
    var ChildNo: Int = 0
    var CityHot:String=""
    var chAge1:Int=0
    var chAge2:Int=0
    var chAge3:Int=0
    var chAge4:Int=0
    var chAge5:Int=0

    var hotelsViewModel:HotelsViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_show_hotels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        checkIn=arguments?.getString("checkIn")!!
        checkOut= arguments?.getString("checkOut")!!
        CityHot=arguments!!.getString("CityHotel")!!.toLowerCase()
        AdultNo=arguments!!.getInt("Adult")
        ChildNo=arguments!!.getInt("Child")
       chAge1=arguments!!.getInt("chAge1")
        chAge2=arguments!!.getInt("chAge2")
        chAge3=arguments!!.getInt("chAge3")
        chAge4=arguments!!.getInt("chAge4")
        chAge5=arguments!!.getInt("chAge5")

        CityDateHot.text=CityHot+"   "+checkIn+"   "+checkOut+"  "+AdultNo+"Adult  "+ChildNo+"Child"

        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= hotelViewModelFactory(repostary)
        hotelsViewModel = ViewModelProviders.of(this,factory).get(HotelsViewModel::class.java)
        hotelsViewModel?.dataLesener=this

        hotelsViewModel!!.GetHotels( checkIn,
                checkOut,
         AdultNo.toString(),
         ChildNo.toString(),
         CityHot,
         chAge1.toString(),
         chAge2.toString(),
         chAge3.toString(),
         chAge4.toString(),
         chAge5.toString())
       // val url:String="https://favorite-holiday.herokuapp.com/api/holet/all?adt=$AdultNo&in=$checkIn&infint=$InfantNo&ch=$ChildNo&out=$checkOut&city=$CityHot"

    }


}
