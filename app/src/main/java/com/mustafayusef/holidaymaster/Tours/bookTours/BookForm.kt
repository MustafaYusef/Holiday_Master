package com.mustafayusef.holidaymaster.Tours.bookTours

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.MainActivity
import com.mustafayusef.holidaymaster.Models.TourOrder
import com.mustafayusef.holidaymaster.Models.Tours

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.Tours.ToursViewModel
import com.mustafayusef.holidaymaster.Tours.lesener
import com.mustafayusef.holidaymaster.Tours.tok
import com.mustafayusef.holidaymaster.Tours.toursViewModelFactory
import com.mustafayusef.holidaymaster.login.LoginMember
import com.mustafayusef.holidaymaster.networks.msg
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_select__tour.*
import kotlinx.android.synthetic.main.book_form_fragment.*
import kotlinx.android.synthetic.main.progress.*

class BookForm : Fragment(),lesener {
    override fun onSucsessBookTour(message: msg) {
        context?.toast(message.msg.toString())
        view?.findNavController()?.navigate(R.id.tours_main)
        bookLoading?.visibility=View.GONE
    }

    override fun onSucsessGetOrder(Response: TourOrder) {
        //context?.toast(Response.toString())
        myOrder=Response
        bookLoading?.visibility=View.GONE
    }
  var myOrder:TourOrder?=null


    override fun OnStart() {
      //  context?.toast("start")
        bookLoading?.visibility=View.VISIBLE

    }

    override fun onFailer(message: String) {
        context?.toast(message.toString())
        bookLoading?.visibility=View.GONE
    }

    override fun onSucsess(Response: List<Tours>) {
    }

    override fun onSucsessBook(Response: tok) {
        bookLoading?.visibility=View.GONE
    }

    companion object {
        fun newInstance() = BookForm()
    }

    private lateinit var GroupviewModel: ToursViewModel
  var email=""
    var phone=""
    var name=""
    var hotel=""
    var national=""
    var infant=0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_form_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= toursViewModelFactory(repostary)

        GroupviewModel = ViewModelProviders.of(this,factory).get(ToursViewModel::class.java)
        GroupviewModel?.dataLesener=this
        arguments!!.getString ("token")?.let { GroupviewModel.getOrder(it) }

        arguments!!.getString ("token")
        priceB.text=arguments!!.getInt ("price").toString()
        adultB.text=arguments!!.getInt ("adult").toString()
        childB.text=arguments!!.getInt ("child").toString()


        BookTour.setOnClickListener {
            if(!emailB.text.toString().isNullOrEmpty()
                &&!phoneB.text.toString().isNullOrEmpty()
                &&!nameB.text.toString().isNullOrEmpty()
                &&!hotelB.text.toString().isNullOrEmpty()
                &&!nationalB.text.toString().isNullOrEmpty()
                &&!infantB.text.toString().isNullOrEmpty()){
                email=emailB.text.toString()
                phone=phoneB.text.toString()
                name=nameB.text.toString()
                hotel=hotelB.text.toString()
                national=nationalB.text.toString()
                infant=infantB.text.toString().toInt()


                var aa= AdultsData(Email=email,Phone=phone,Nationality=national,Name=name,Hotel=hotel,
                    INFANT=infant.toString(),Room=hotelRoomB.text.toString(),address=hotelAddressB.text.toString())
                var bb=gg(AdultsData=aa,data=myOrder!!)
                GroupviewModel.SubmetBookTours(LoginMember.cacheObj.token,bb)
                println(bb.AdultsData)
                println(bb.data)
            }else{
                context?.toast("Please fill all the field")
            }

        }
    }

}
data class AdultsData(
    var Email:String,
    var Phone:String,
    var Nationality:String,
    var Name:String,
    var Hotel:String,
    var Room:String,
    var address:String,
    var INFANT:String
)
data class gg(
    var AdultsData:AdultsData,
    var data:TourOrder
)