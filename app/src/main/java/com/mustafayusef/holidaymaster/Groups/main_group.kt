package com.mustafayusef.holidaymaster.Groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Models.Buggage
import com.mustafayusef.holidaymaster.Models.TourOrder
import com.mustafayusef.holidaymaster.Models.group
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.Tours.tok
import com.mustafayusef.holidaymaster.networks.msg
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.tickets.searchTicket.searchViewModel
import com.mustafayusef.holidaymaster.tickets.searchTicket.searchViewModelFactory
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_main_group.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.progress2.*
import okhttp3.*
import java.io.IOException

class main_group : Fragment(),lesener {
    override fun onSucsessFinalBookGroup(Response: msg) {

    }

    override fun onSucsessGetOrderGroup(Response: TourOrder) {

    }

    override fun onSucsessBook(Response: tok) {

    }

    var GroupviewModel:groupsViewModel?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= groupViewModelFactory(repostary)
        GroupviewModel = ViewModelProviders.of(this,factory).get(groupsViewModel::class.java)
        GroupviewModel?.dataLesener=this
        GroupviewModel?.GetGroups()
    }
    override fun OnStart() {
        animation_viewTourPub?.visibility= View.VISIBLE
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        animation_viewTourPub?.visibility= View.GONE

    }

    override fun onSucsess(BuggageResponse: List<group>) {
        animation_viewTourPub?.visibility= View.GONE

        Groups_list?.layoutManager= LinearLayoutManager(context)
        Groups_list?.adapter= groupsAdapter(context!!, BuggageResponse)
    }

//    fun Run(){
//        Groups_list?.layoutManager= LinearLayoutManager(context)
//        val url="https://favorite-holiday.herokuapp.com/api/Group/all"
//        val request= Request.Builder().url(url).build()
//        val client= OkHttpClient()
//
//        client.newCall(request).enqueue(object : Callback {
//
//
//            override fun onResponse(call: Call, response: Response) {
//
//                val body=response.body()?.string()
//                if(body!!.length>50){
//                    println(body)
//                    val gson= GsonBuilder().create()
//                    val GroupFeed:List<group>? = gson.fromJson(body, Array<group>::class.java).toList()
//                    println("the array objects")
//
//                    runOnUiThread {
//
//                        //                        noResult?.text=holidayFeed?.size.toString()+" Result Found"
//                        Groups_list?.adapter=
//                            groupsAdapter(this@main_group, GroupFeed)
//                        animation_viewGroup.translationZ= 0F
//                        animation_viewGroup.pauseAnimation()
//                    }
//                }else{
//                    runOnUiThread {
//                        //                        noResult?.setTextColor(-0x01ffff)
////                        noResult?.text=" There is no result Found"
//                        animation_viewGroup.translationZ= 0F
//                        animation_viewGroup.pauseAnimation()
//                    }
//                }
//
//
//            }
//            override fun onFailure(call: Call, e: IOException) {
//          runOnUiThread{
//              Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
//          }
////
//            }
//
//
//        })
//    }


}
