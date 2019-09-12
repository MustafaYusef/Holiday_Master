package com.mustafayusef.holidaymaster.Tours.bookTours

import android.app.Dialog
import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.Models.Data
import com.mustafayusef.holidaymaster.Models.TourOrder
import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.Tours.ToursViewModel
import com.mustafayusef.holidaymaster.Tours.lesener
import com.mustafayusef.holidaymaster.Tours.tok
import com.mustafayusef.holidaymaster.Tours.toursViewModelFactory
import com.mustafayusef.holidaymaster.networks.msg
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_select__tour.*
import kotlinx.android.synthetic.main.bottom_sheet_date_tour.view.*

class Select_Tour : Fragment(),lesener {
    override fun onSucsessGetOrder(Response: TourOrder) {

    }

    override fun onSucsessBookTour(message: msg) {
    }


    override fun onSucsessBook(Response: tok) {
      //  context?.toast(Response.orderToken)
        var bundel=Bundle()
        bundel.putString("token",Response.orderToken)
        bundel.putInt("price",pric)
        bundel.putInt("child",chNo?.text.toString().toInt())
        bundel.putInt("adult",adNo?.text.toString().toInt())
        view?.findNavController()?.navigate(R.id.bookForm,bundel)

    }

    override fun OnStart() {

    }

    override fun onFailer(message: String) {
        context?.toast(message)
    }

    override fun onSucsess(Response: List<Tours>) {

    }
    var pric=0
    var tour: Tours? = null
    var date=""
    var index=0
    var seat:Double= 0.0
    var GroupviewModel:ToursViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_select__tour, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= toursViewModelFactory(repostary)
        GroupviewModel = ViewModelProviders.of(this,factory).get(ToursViewModel::class.java)
        GroupviewModel?.dataLesener=this

        priceButton.setOnClickListener {
            println("date "+tour!!.Data)
//               var t=tour
//            t!!.Data?.get(index)!!.ToursSets=seat.toLong()

            pric= (priceButton.text.toString().subSequence(0,priceButton.text.toString().length - 2
            ).toString().toInt())
            var info = InfoOb(ChildrenNumber= chNo.text.toString().toInt(),
                ADULTSNumber= adNo.text.toString().toInt(),
                ToursDate= date,
            ToursSets= seat.toString())
            var dat=tour!!.Data
            dat?.get(index)?.ToursSets =seat.toDouble()
            println("date change "+dat)
            var Book=
                dat?.let { it1 ->
                    TourBook(
                        type = "Tours",
                        price = pric,
                        info =info,
                        dates = it1,
                        item = tour!!,
                        Adults =adNo.text.toString().toInt(),
                        Child =chNo.text.toString().toInt(),
                        Infant =0)
                }
            Book?.let { it1 -> GroupviewModel?.BookTours(it1) }
//            let parameters: Parameters = ["type":"Tours",
//            "price": price,
//            "info": infoFinal!,
//            "dates":datesFinal!,
//            "item": j,
//            "Adults": num1,
//            "Child": num2,
//            "Infant": 0
//            ]


        }



         tour= arguments!!.getSerializable ("Tour") as Tours
        //priceButton.visibility=View.INVISIBLE
        wrap.visibility=View.INVISIBLE

        seat=(tour!!.Data?.get(index)!!.ToursSets)!!.toDouble()

        priceAdult.text= tour!!.price!!.toString()+" $"
        priceChild.text= tour!!.priceCh!!.toString()+" $"



        subAd.setOnClickListener {
            var adnum=adNo.text.toString().toInt()

            if(adNo.text.toString().toInt()>1){
                adnum--
                seat++
                seatsNo.text="Available Seats:"+seat
                adNo.text=adnum.toString()
                priceButton.text= ((priceButton.text.toString().subSequence(0,priceButton.text.toString().length-2).toString().toInt() -tour!!.price!!.toInt()).toString()+" $")

            }


        }
        plusAd.setOnClickListener {
            var adnum=adNo.text.toString().toInt()


            if(adNo.text.toString().toInt()<10){
                if(seat>0){
                    adnum++
                    seat--
                    seatsNo.text="Available Seats:"+seat
                    adNo.text=adnum.toString()
                    priceButton.text= ((priceButton.text.toString().subSequence(0,priceButton.text.toString().length-2).toString().toInt() +tour!!.price!!.toInt()).toString()+" $")

                }

            }


        }


        plusCh.setOnClickListener {
            var adnum=chNo.text.toString().toInt()


            if(chNo.text.toString().toInt()<10){
                if(seat>0) {
                    adnum++
                    seat -= 0.5
                    seatsNo.text = "Available Seats:" + seat
                    chNo.text = adnum.toString()
                    priceButton.text = ((priceButton.text.toString().subSequence(
                        0,
                        priceButton.text.toString().length - 2
                    ).toString().toInt() + tour!!.priceCh!!.toInt()).toString() + " $")
                }
        }


    }


        subch.setOnClickListener {
            var adnum=chNo.text.toString().toInt()

            if(chNo.text.toString().toInt()>0){
                adnum--
                seat+=0.5
                seatsNo.text="Available Seats:"+seat
                chNo.text=adnum.toString()
                priceButton.text= ((priceButton.text.toString().subSequence(0,priceButton.text.toString().length-2).toString().toInt() -tour!!.priceCh!!.toInt()).toString()+" $")

            }


        }
        DatesTours.setOnClickListener {
            selectDate()
        }

    }

    fun selectDate(){


        val view = layoutInflater.inflate(R.layout.bottom_sheet_date_tour , null)

        val display =activity!!. windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y


        view.minimumHeight=600
        val mBottomSheetDialog = Dialog(
            context!!,
            R.style.MaterialDialogSheet
        )
        mBottomSheetDialog.setContentView(view)
        mBottomSheetDialog.setCancelable(true)
        mBottomSheetDialog.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        mBottomSheetDialog.window!!.setGravity(Gravity.BOTTOM)
//        view.OptionList.layoutManager= LinearLayoutManager(this)
//        view.OptionList.adapter = OptionsAdapter(this@Select_Tour, hotel)
        view.datesPickers.minValue = 0
        view.datesPickers.maxValue = tour!!.Data!!.size-1
        view.datesPickers.wrapSelectorWheel = true
        val datesArray= mutableListOf<String>()
        for(i in 0 until  tour!!.Data!!.size){
            datesArray.add(tour!!.Data!![i].ToursDate.toString())
        }
        view.datesPickers.displayedValues = datesArray.toTypedArray()
        date= datesArray[0]
        view.datesPickers.setOnValueChangedListener { picker, oldVal, newVal ->
            //
            //Display the newly selected number to text view
            date = datesArray[newVal]
            // println(country +"   cooodkl,dl")
            index=newVal
        }


        mBottomSheetDialog.show()

        view.ApplayT.setOnClickListener {
            dateTour.text=date
            wrap.visibility=View.VISIBLE
            seatsNo.text="Available Seats:"+((tour!!.Data?.get(index)!!.ToursSets)!!.toInt())
            seat=tour!!.Data?.get(index)!!.ToursSets!!.toDouble()
            adNo.text="1"
            priceButton.text=tour!!.price.toString()+" $"
            mBottomSheetDialog.dismiss()
        }


//        spin1.setAdapter(ArrayAdapter<String>(this@RepActivity, android.R.layout.simple_dropdown_item_1line, items))
//        spin2.setAdapter(ArrayAdapter<String>(this@RepActivity, android.R.layout.simple_dropdown_item_1line, items))
//
//        catList.setAdapter(categoryListAdapter)


        view.CancelT.setOnClickListener {
            mBottomSheetDialog.dismiss()
        }

    }



}
data class InfoOb(
    var ChildrenNumber: Int,
    var ADULTSNumber: Int,
    var ToursDate: String,
   var ToursSets:String

)
data class TourBook(
    var type:String,
    var price: Int,
    var info: InfoOb,
    var dates: List<Data>,
    var item: Tours,
    var Adults:Int,
    var Child:Int,
    var Infant:Int
)

//            let parameters: Parameters = ["type":"Tours",
//            "price": price,
//            "info": infoFinal!,
//            "dates":datesFinal!,
//            "item": j,
//            "Adults": num1,
//            "Child": num2,
//            "Infant": 0
//            ]
