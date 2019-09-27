package com.mustafayusef.holidaymaster.Groups.BookGroup

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
import com.mustafayusef.holidaymaster.Groups.groupViewModelFactory
import com.mustafayusef.holidaymaster.Groups.groupsViewModel
import com.mustafayusef.holidaymaster.Groups.lesener
import com.mustafayusef.holidaymaster.Models.Data
import com.mustafayusef.holidaymaster.Models.TourOrder

import com.mustafayusef.holidaymaster.Models.group
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.Tours.bookTours.InfoOb
import com.mustafayusef.holidaymaster.Tours.tok
import com.mustafayusef.holidaymaster.networks.msg
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_select_group.*
import kotlinx.android.synthetic.main.bottom_sheet_date_tour.view.*
import kotlinx.android.synthetic.main.progress.*

class select_group : Fragment(), lesener {
    override fun onSucsessFinalBookGroup(Response: msg) {

    }

    override fun onSucsessGetOrderGroup(Response: TourOrder) {

    }

    override fun OnStart() {
        bookLoading?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        bookLoading?.visibility=View.GONE
    }

    override fun onSucsess(Response:List<group>) {
        bookLoading?.visibility=View.GONE
    }

    override fun onSucsessBook(Response:tok) {
        //context?.toast(Response.orderToken)
        var bundel=Bundle()
        bundel.putString("token",Response.orderToken)
        bundel.putInt("persons",persons)

        bundel.putInt("adult",adNoGroup.text.toString().toInt())
        bundel.putInt("child",chNoGroup.text.toString().toInt())
        bundel.putInt("infant",infNoGroup.text.toString().toInt())


        view?.findNavController()?.navigate(R.id.groupBook,bundel)
        bookLoading?.visibility=View.GONE
    }
//var adult:Int=0
//    var child:Int=0
//    var infant:Int=0


    var tour: group? = null
    var date=""
    var index=0
    var seat:Double=0.0
    var GroupviewModel: groupsViewModel?=null
    var persons=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_select_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tour= arguments!!.getSerializable("Group") as group
        //priceButton.visibility=View.INVISIBLE
        wrapGroup.visibility= View.INVISIBLE

        seat=(tour!!.Data?.get(index)!!.ToursSets)!!.toDouble()

        priceAdultGroup.text= tour!!.price!!.toString()+" $"
        priceChildGroup.text= tour!!.priceCh!!.toString()+" $"

        subAdGroup.setOnClickListener {
            var adnum=adNoGroup.text.toString().toInt()

            if(adNoGroup.text.toString().toInt()>1){
                adnum--
                seat++
                seatsNoGroup.text="Available Seats:"+seat
                adNoGroup.text=adnum.toString()
                priceButtonGroup.text= ((priceButtonGroup.text.toString().subSequence(0,priceButtonGroup.text.toString().length-2).toString().toInt() -tour!!.price!!.toInt()).toString()+" $")

            }
        }
        plusAdGroup.setOnClickListener {
            var adnum=adNoGroup.text.toString().toInt()


            if(adNoGroup.text.toString().toInt()<10){
                if(seat>0) {
                    adnum++
                    seat--
                    seatsNoGroup.text = "Available Seats:" + seat
                    adNoGroup.text = adnum.toString()
                    priceButtonGroup.text = ((priceButtonGroup.text.toString().subSequence(
                        0,
                        priceButtonGroup.text.toString().length - 2
                    ).toString().toInt() + tour!!.price!!.toInt()).toString() + " $")
                }
            }


        }


        plusChGroup.setOnClickListener {
            var adnum=chNoGroup.text.toString().toInt()


            if(chNoGroup.text.toString().toInt()<10){
                if(seat>0) {
                adnum++
                seat--
                seatsNoGroup.text="Available Seats:"+seat
                chNoGroup.text=adnum.toString()
                priceButtonGroup.text= ((priceButtonGroup.text.toString().subSequence(0,priceButtonGroup.text.toString().length-2).toString().toInt() +tour!!.priceCh!!.toInt()).toString()+" $")

            }}


        }


        subchGroup.setOnClickListener {
            var adnum=chNoGroup.text.toString().toInt()

            if(chNoGroup.text.toString().toInt()>0){
                adnum--
                seat++
                seatsNoGroup.text="Available Seats:"+seat
                chNoGroup.text=adnum.toString()
                priceButtonGroup.text= ((priceButtonGroup.text.toString().subSequence(0,priceButtonGroup.text.toString().length-2).toString().toInt() -tour!!.priceCh!!.toInt()).toString()+" $")

            }
        }

        if(tour!!.opt?.get(0)?.food!!){
            foodLogo.setImageResource(R.drawable.breakfast_6)
        }
        if(tour!!.opt?.get(0)?.Flight!!){
            FlightLogo.setImageResource(R.drawable.airplane_2)
        }
        if(tour!!.opt?.get(0)?.Hotel!!){
            HotelLogo.setImageResource(R.drawable.hotel_2)
        }
        if(tour!!.opt?.get(0)?.Transport!!){
            TransportLogo.setImageResource(R.drawable.bus_3)
        }
        if(tour!!.opt?.get(0)?.TourismProgram!!){
            TourismProgramLogo.setImageResource(R.drawable.brochure)
        }
        if(tour!!.opt?.get(0)?.Visa!!){
            VisaLogo.setImageResource(R.drawable.passport_2)
        }


        plusInfGroup.setOnClickListener {
            var adnum=infNoGroup.text.toString().toInt()


            if(infNoGroup.text.toString().toInt()<10){
                if(seat>0) {
                    adnum++
//                    seat-=0.5
//                    seatsNoGroup.text="Available Seats:"+seat
                    infNoGroup.text=adnum.toString()
                   // priceButtonGroup.text= ((priceButtonGroup.text.toString().subSequence(0,priceButtonGroup.text.toString().length-2).toString().toInt() +tour!!.priceCh!!.toInt()).toString()+" $")

                }}


        }


        subInfGroup.setOnClickListener {
            var adnum=infNoGroup.text.toString().toInt()

            if(infNoGroup.text.toString().toInt()>0){
                adnum--
//                seat+=0.5
//                seatsNoGroup.text="Available Seats:"+seat
                infNoGroup.text=adnum.toString()
               // priceButtonGroup.text= ((priceButtonGroup.text.toString().subSequence(0,priceButtonGroup.text.toString().length-2).toString().toInt() -tour!!.priceCh!!.toInt()).toString()+" $")

            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= groupViewModelFactory(repostary)
        GroupviewModel = ViewModelProviders.of(this,factory).get(groupsViewModel::class.java)
        GroupviewModel?.dataLesener=this
       // GroupviewModel?.GetGroups()
        dateGroupSelect.setOnClickListener {
            selectDateGroup()
        }
        priceButtonGroup.setOnClickListener {
            persons=adNoGroup.text.toString().toInt()+chNoGroup.text.toString().toInt()+infNoGroup.text.toString().toInt()
           var pric= (priceButtonGroup.text.toString().subSequence(0,priceButtonGroup.text.toString().length - 2
            ).toString().toInt())
            var info = InfoOb(ChildrenNumber= chNoGroup.text.toString().toInt(),
                ADULTSNumber= adNoGroup.text.toString().toInt(),
                ToursDate= date,
                ToursSets= seat.toString())
            var dat=tour!!.Data
            println("date "+dat)
            dat?.get(index)?.ToursSets =(dat?.get(index)?.ToursSets)!! - (

                    adNoGroup.text.toString().toInt()+chNoGroup.text.toString().toInt()   )
            println("date change "+dat)
            var Book=
                dat?.let { it1 ->
                    GroupBook(
                        type = "Group",
                        price = pric,
                        info = info,
                        dates = it1,
                        item = tour!!,
                        Adults = adNoGroup.text.toString().toInt(),
                        Child = chNoGroup.text.toString().toInt(),
                        Infant = infNoGroup.text.toString().toInt()
                    )
                }
            println("json"+Book)
            Book?.let { it1 -> GroupviewModel?.BookGroup(it1) }

        }
    }

    fun selectDateGroup(){


        val view = layoutInflater.inflate(R.layout.bottom_sheet_date_tour , null)

        val display =  activity!!.windowManager .defaultDisplay
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
            dateGroup.text=date
            wrapGroup.visibility= View.VISIBLE
            seatsNoGroup.text="Available Seats:"+((tour!!.Data?.get(index)!!.ToursSets)!!.toInt())
            adNoGroup.text="1"
            seat=tour!!.Data?.get(index)!!.ToursSets!!.toDouble()
            priceButtonGroup.text=tour!!.price.toString()+" $"
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

data class GroupBook(
    var type:String,
    var price: Int,
    var info: InfoOb,
    var dates: List<Data>,
    var item: group,
    var Adults:Int,
    var Child:Int,
    var Infant:Int
)

