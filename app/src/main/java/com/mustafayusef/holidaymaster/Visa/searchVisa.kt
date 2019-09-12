package com.mustafayusef.holidaymaster.Visa

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Models.country
import com.mustafayusef.holidaymaster.R

import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_search_visa.*
import kotlinx.android.synthetic.main.show_national.view.*


class searchVisa : Fragment(),lesener {

    var country:String=""

    var national= mutableListOf<String>()
   var visaViewModel:VisaViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_search_visa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // animation_view3.translationZ=1F

        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= VisaViewModelFactory(repostary)
        visaViewModel = ViewModelProviders.of(this,factory).get(VisaViewModel::class.java)
        visaViewModel?.dataLesener=this


        showN.visibility=View.INVISIBLE
        visaViewModel?.GetVisa()



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showN.setOnClickListener {
            showDilog()
        }
    }
    override fun OnStart() {
        //showN?.visibility=View.INVISIBLE
        animation_view3?.visibility=View.VISIBLE
        animation_view3?.playAnimation()

    }

    override fun onFailer(message: String) {
        animation_view3?.visibility=View.INVISIBLE
        animation_view3?.pauseAnimation()
    }
    override fun onSucsessSearch(Response: List<country>) {
        Visa_list?.layoutManager= LinearLayoutManager(context!!)
        Visa_list?.adapter= NationalAdapter(context!!, Response)
        animation_view3?.visibility=View.GONE
        animation_view3?.pauseAnimation()
    }

    override fun onSucsess(Response: List<country>) {
        national.clear()
        for(i in 0 until Response!!.size){
                national.add( Response!![i].Nationality!!)
            }
        showN?.visibility=View.VISIBLE
        animation_view3?.visibility=View.GONE
    }

        fun showDilog() {

            val dview: View = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.show_national, null)

            val builder = AlertDialog.Builder(context!!).setView(dview)



           val malert= builder.show()
            malert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dview.NationalPicker.minValue = 0
            dview.NationalPicker.maxValue = national.size-1
            dview.NationalPicker.wrapSelectorWheel = true
            dview.NationalPicker.displayedValues = national.toTypedArray()
            country=national[0]
            dview.NationalPicker.setOnValueChangedListener { picker, oldVal, newVal ->

                //Display the newly selected number to text view
                country = national[newVal]
           // println(country +"   cooodkl,dl")
                }
//            }
//                 dilog.NationalPicker.minValue = 0
//            dilog.NationalPicker .maxValue = 2
//            dilog.NationalPicker!!.wrapSelectorWheel = true
                dview.selectN.setOnClickListener {
                    visaViewModel?.GetSearchVisa(country)
                    malert.dismiss()
                   // national.clear()
                }


        }



}
