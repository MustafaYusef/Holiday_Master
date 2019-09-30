package com.mustafayusef.holidaymaster.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager


import com.mustafayusef.holidaymaster.Adapters.OneWayAdapter
import com.mustafayusef.holidaymaster.Adapters.TowWayAdapter
import com.mustafayusef.holidaymaster.Models.Result
import com.mustafayusef.holidaymaster.Models.ResultTow
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.tickets.leseners.getDataLesener
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.tickets.searchTicket.searchViewModel
import com.mustafayusef.holidaymaster.tickets.searchTicket.searchViewModelFactory
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary

import kotlinx.android.synthetic.main.activity_show_holiday.*

class showHoliday : Fragment(), getDataLesener {


    var flag:Boolean=true
    var adult:Int=0
    var child:Int=0
    var infant:Int=0
    var departure=""
    var Return=""
    var cabin=1
    var from=""
    var to=""
    var direct=0

    var TowWayResponse: ResultTow?=null
   var ResponseOneWay: Result?=null
 var searchviewModel:searchViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_show_holiday, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val networkIntercepter=networkIntercepter(context!!)
        val api=myApis(networkIntercepter)
        val repostary=userRepostary(api)
        val factory=searchViewModelFactory(repostary)
                  flag=arguments!!.getBoolean("flage",true)
         adult= arguments!!.getInt("adult",1)
         child=arguments!!.getInt("child",0)
        infant=arguments!!.getInt("infant",0)
         departure=arguments!!.getString("departure")!!
         Return= arguments!!.getString("Return") !!
        cabin =arguments!!.getInt("Type",1)
         from=arguments!!.getString("fromSelect")!!
         to=arguments!!.getString("toSelect")!!
        direct=arguments!!.getInt("Direct",0)

        searchviewModel = ViewModelProviders.of(this,factory).get(searchViewModel::class.java)
        searchviewModel?.dataLesener=this

        if(ResponseOneWay==null&&flag){
            searchviewModel!!.getdata(infant,child,
                adult,departure,
                Return,
                from, to,
                direct,cabin,flag)
        }else if (ResponseOneWay!=null&&flag){
            Holiday_list?.layoutManager= LinearLayoutManager(context!!)
            Holiday_list?.adapter= OneWayAdapter(context!!, ResponseOneWay!!)
        }else if(TowWayResponse==null&&!flag){
            searchviewModel!!.getdata(infant,child,
                adult,departure,
                Return,
                from, to,
                direct,cabin,flag)

        }else if(TowWayResponse!=null&&!flag){
            Holiday_list?.layoutManager= LinearLayoutManager(context)
            Holiday_list?.adapter= TowWayAdapter(context!!, TowWayResponse!!)
        }



        fromcity?.text=from
        toShow?.text=to
        classShow?.text= "Adult:${adult} Children:${child}" +
                " class:${cabin} Dep:${departure}"




        var url:String=""

        println("ddddddddddddddddddddddddddddddddd after change economy ")


        refresh?.setOnRefreshListener {
            searchviewModel!!.getdata(infant,child,
                adult,departure,
                Return,
                from, to,
                direct,cabin,flag)
            Holiday_list?.visibility=View.GONE
            refresh?.isRefreshing=false
        }
    }

    override fun OnStart() {
   // this.toast("start")
    animation_view?.playAnimation()
    animation_view?.visibility=View.VISIBLE
    }

    override fun onSucsess(ResponseOneWay1: Result) {
       // this.toast(ResponseOneWay.sessionID)
        ResponseOneWay=ResponseOneWay1
        if(ResponseOneWay?.data!=null|| ResponseOneWay?.data?.get(0)?.Duration?.isNotEmpty()!!){
            ResponseOneWay?.data=ResponseOneWay?.data!!.sortedBy { it!!.price }
            noResult?.text=ResponseOneWay?.data?.size .toString()+" Result Found"
            Holiday_list?.visibility=View.VISIBLE
            Holiday_list?.layoutManager= LinearLayoutManager(context!!)
            Holiday_list?.adapter= OneWayAdapter(context!!, ResponseOneWay!!)
            // animation_view.enableMergePathsForKitKatAndAbove(true)

        }else{
            noResult?.text=" Result not Found"

        }
        animation_view?.visibility=View.GONE
    }

    override fun onSucsessTow(TowWayResponse1: ResultTow) {
        TowWayResponse=TowWayResponse1
        if(TowWayResponse?.data!=null|| TowWayResponse?.data?.get(0)?.Duration?.isNotEmpty()!!){
            TowWayResponse?.data=TowWayResponse?.data!!.sortedBy { it!!.price }
            noResult?.text=TowWayResponse?.data?.size .toString()+" Result Found"
            Holiday_list?.visibility=View.VISIBLE
            Holiday_list?.layoutManager= LinearLayoutManager(context)
            Holiday_list?.adapter= TowWayAdapter(context!!, TowWayResponse!!)
            // animation_view.enableMergePathsForKitKatAndAbove(true)

        }else{
            noResult?.text=" Result not Found"

        }
        animation_view?.visibility=View.GONE
    }



    override fun onFailer(message: String) {
     context?.toast(message)
        animation_view?.visibility=View.GONE
        noResult?.text=" Result not Found"
    }

}


