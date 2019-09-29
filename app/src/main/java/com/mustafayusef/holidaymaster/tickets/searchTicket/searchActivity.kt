package com.mustafayusef.holidaymaster.tickets.searchTicket

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_search.*
//import netscape.javascript.JSObject.getWindow
//import com.sun.deploy.ui.CacheUpdateProgressDialog.dismiss
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import java.util.*
import com.google.gson.Gson
import com.mustafayusef.holidaymaster.*
import com.mustafayusef.holidaymaster.Models.AutoCom
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Adapters.OptionsAdapter
import com.mustafayusef.holidaymaster.login.LoginMember
import com.mustafayusef.holidaymaster.tickets.showHoliday
import com.mustafayusef.holidaymaster.utils.toast
import kotlinx.android.synthetic.main.activity_check_rooms.*
import kotlinx.android.synthetic.main.activity_options.view.*
import kotlinx.android.synthetic.main.activity_search_hotels.*
import kotlinx.android.synthetic.main.bottom_sheet_airport.view.*


class searchActivity : Fragment(),cityAdapter.OnNoteLisener,cityAdapter2.OnNoteLisener2{


    var mBottomSheetDialog:Dialog?=null

    lateinit var myDialog: Dialog

    lateinit var option: Spinner
    lateinit var result: TextView
    lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener
    var flage = true
    var adult = 1
    var child = 0
    var infant = 0
    var type = 3
    var departure = ""
    var Return = ""
    var fromSelect = ""
    var toSelect = ""
    var direct=0
    val name: String = "mustafa yusef"
    var email: String = ""
    var phone: String = ""
    var money: String = ""

    var flage1=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_search, container, false)
    }


    var city = mutableListOf<String>()
    var country = mutableListOf<String>()
    var nameAir = mutableListOf<String>()
    var short =mutableListOf<String>()

    var cityT =mutableListOf<String>()
    var countryT =mutableListOf<String>()
    var nameAirT = mutableListOf<String>()
    var shortT =mutableListOf<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AdultPicker.minValue = 1
        AdultPicker.maxValue = 10
        AdultPicker.wrapSelectorWheel = true
        childPicker.minValue = 0
        childPicker.maxValue = 10
        childPicker.wrapSelectorWheel = true
        InfantPicker.minValue = 0
        InfantPicker.maxValue = 10
        InfantPicker.wrapSelectorWheel = true

        oneWay.isActivated = true
        flage = true

        contRet.visibility = View.INVISIBLE
        val options = arrayOf("Economy","Business","First")
        TypePicker.minValue=0
        TypePicker.maxValue=options.size-1
        TypePicker.displayedValues=options

        TypePicker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            if(options[newVal]=="Economy"){
                type =3
            }else if(options[newVal]=="Business"){
                type =2
            }else{
                type =1
            }

        }
        val suggest: Array<AutoCom>
        var json: String = ""
        val objectArrayString: String = context!!.resources.openRawResource(R.raw.airports)
            .bufferedReader().use { it.readText() }

        val gson = Gson()
        suggest = gson.fromJson(objectArrayString, Array<AutoCom>::class.java)
        // println("suggestion\n"+suggest)


        for (i in suggest) {
            city.add(i.city)
            country.add(i.country)
            nameAir.add(i.name)
            short.add(i.iata)
        }

      FromBtn?.setOnClickListener {
          showDailog1()
      }
        toBtn?.setOnClickListener {
            showDailog2()
        }



        AdultPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            adult = newVal
        }
        childPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
           child = newVal
        }
        InfantPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            infant = newVal
        }


        contDep.setOnClickListener {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val dpd = DatePickerDialog(
                context!!,

                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->

                    depText.text= "$year-${month+1}-$dayOfMonth"
                    departure = "$year-${month+1}-$dayOfMonth"
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        contRet.setOnClickListener {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH).toInt()
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR).toInt()

            val dpd = DatePickerDialog(
                context!!,

                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->

                    retunText.text = "$year-${month+1}-$dayOfMonth"
                    Return = "$year-${month+1}-$dayOfMonth"
                },
                year,
                month,
                day
            )
            dpd.show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        oneWay.setOnClickListener {
            oneWay.isActivated = true
            towWay.isActivated = false
            flage = true
            contRet.visibility = View.INVISIBLE

        }

        towWay.setOnClickListener {
            towWay.isActivated = true
            oneWay.isActivated = false

            flage = false
            contRet.visibility = View.VISIBLE
        }


        showHoliday.setOnClickListener {
            if(adult>=infant){
                if(flage){
                    if(fromSelect!=""){
                        if(toSelect!=""){
                            if(departure!=""){

                                //  sss.getdata()
                                val intent =Bundle()
//
                                intent.putBoolean("flage", flage)
                                intent.putInt("adult", adult)
                                intent.putInt("child", child)
                                intent.putInt("infant", infant)
                                intent.putString("departure", departure)
                                intent.putString("Return", Return)
                                intent.putInt("Type", type)
                                intent.putString("fromSelect", fromSelect)
                                intent.putString("toSelect", toSelect)
                                intent.putInt("Direct", direct)
                                view?.findNavController()?.navigate(R.id.searchTecketToShow,intent)


                            }else{

                                Toast.makeText(context, "should set a departure date", Toast.LENGTH_SHORT).show()
                                contDep.startAnimation(AnimationUtils.loadAnimation(context,R.anim.shake))

                            }
                        }else{
                            toBtn.startAnimation(AnimationUtils.loadAnimation(context,R.anim.shake))
                            toBtn.setText("")
                            toBtn.hint="select from List"
                            toBtn.setHintTextColor(-0x01ffff)
                            toBtn.highlightColor=-0x01ffff
                            Toast.makeText(context, "You should select from list", Toast.LENGTH_SHORT).show()

                        }

                    }else{
                        FromBtn.startAnimation(AnimationUtils.loadAnimation(context,R.anim.shake))
                        FromBtn.setText("")
                        FromBtn.hint="select from List"
                        FromBtn.setHintTextColor(-0x01ffff)
                        FromBtn.highlightColor=-0x01ffff
                        Toast.makeText(context, "You should select from list", Toast.LENGTH_SHORT).show()

                    }


                }else{
                    if(fromSelect!=""){
                        if(toSelect!=""){
                            if(departure!=""){
                                if(Return!=""){

                                    val intent = Bundle()

                                    intent.putBoolean("flage", flage)
                                    intent.putInt("adult", adult)
                                    intent.putInt("child", child)
                                    intent.putInt("infant", infant)
                                    intent.putString("departure", departure)
                                    intent.putString("Return", Return)
                                    intent.putInt("Type", type)
                                    intent.putString("fromSelect", fromSelect)
                                    intent.putString("toSelect", toSelect)
                                    intent.putInt("Direct", direct)
                                    view?.findNavController()?.navigate(R.id.searchTecketToShow,intent)
                                }else{
                                    Toast.makeText(context, "should set a Return date", Toast.LENGTH_SHORT).show()
                                    contRet.startAnimation(AnimationUtils.loadAnimation(context,R.anim.shake))
                                }

                            }else{

                                Toast.makeText(context, "should set a departure date", Toast.LENGTH_SHORT).show()
                                contDep.startAnimation(AnimationUtils.loadAnimation(context,R.anim.shake))

                            }
                        }else{
                            toBtn.startAnimation(AnimationUtils.loadAnimation(context,R.anim.shake))
                            toBtn.setText("")
                            toBtn.hint="select from List"
                            toBtn.setHintTextColor(-0x01ffff)
                            toBtn.highlightColor=-0x01ffff
                            Toast.makeText(context, "You should select from list", Toast.LENGTH_SHORT).show()

                        }

                    }else{
                        FromBtn.startAnimation(AnimationUtils.loadAnimation(context,R.anim.shake))
                        FromBtn.setText("")
                        FromBtn.hint="select from List"
                        FromBtn.setHintTextColor(-0x01ffff)
                        FromBtn.highlightColor=-0x01ffff
                        Toast.makeText(context, "You should select from list", Toast.LENGTH_SHORT).show()

                    }
                }
            }else{
                context?.toast("the number of Infant Should Not be more than Adults")
            }

        }
    }
     fun closeKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }



    fun showDailog2(){
        flage1=true
        val view = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.bottom_sheet_airport , null)
        val display =activity!!.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y

        view?.airportList?.layoutManager=LinearLayoutManager(context)
        view?.airportList?.adapter=context?.let {
            cityAdapter2(it ,this@searchActivity, city,country,nameAir,short) }
        view.minimumHeight=600
        mBottomSheetDialog = Dialog(
            context!!,
            com.mustafayusef.holidaymaster.R.style.MaterialDialogSheet
        )
        mBottomSheetDialog!!.setContentView(view)
        mBottomSheetDialog!!.setCancelable(true)
        mBottomSheetDialog!!.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        mBottomSheetDialog!!.show()
//        mBottomSheetDialog!!.setOnShowListener {
//            flage1=true
//
//            view?.airportList?.layoutManager=LinearLayoutManager(context)
//            view?.airportList?.adapter=context?.let { cityAdapter(it ,this@searchActivity, city,country,nameAir,short) }
//
//        }



        view?.fromCity?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(!newText!!.isEmpty()){
                    flage1=false
                    filter(newText.toString())
                    view?.airportList?.layoutManager=LinearLayoutManager(context)

                    view?.airportList?.adapter=context?.let { cityAdapter2(it ,this@searchActivity, cityT
                        ,countryT,nameAirT,shortT) }
                }else{
                    flage1=true
                }

                return false
            }
        })

    }

    fun showDailog1(){
        flage1=true
        val view = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.bottom_sheet_airport , null)
        val display =activity!!.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y
        view?.airportList?.layoutManager=LinearLayoutManager(context)
        view?.airportList?.adapter=context?.let {
            cityAdapter(it ,this@searchActivity, city,country,nameAir,short) }
//        view?.airportList?.layoutManager=LinearLayoutManager(context)
//        view?.airportList?.adapter=context?.let {
//            cityAdapter(it ,this@searchActivity, city,country,nameAir,short) }
        view.minimumHeight=600
         mBottomSheetDialog = Dialog(
            context!!,
            com.mustafayusef.holidaymaster.R.style.MaterialDialogSheet
        )
        mBottomSheetDialog!!.setContentView(view)
        mBottomSheetDialog!!.setCancelable(true)
        mBottomSheetDialog!!.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        mBottomSheetDialog!!.show()

//        var adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, names)
//
//       view?. fromCity?.setAdapter(adapter)


        view?.fromCity?.onFocusChangeListener = View.OnFocusChangeListener{ view: View,
                                                        b: Boolean ->

                // Display the suggestion dropdown on focus

        }
        view?.fromCity?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // do something on text submit

                   // filter(query.toString())
//                view?.airportList?.adapter=context?.let { cityAdapter(it ,this@searchActivity, city,country,nameAir,short) }



                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText!!.isEmpty()){
                    flage1=false
                    filter(newText.toString())
                    view?.airportList?.layoutManager=LinearLayoutManager(context)

                    view?.airportList?.adapter=context?.let { cityAdapter(it ,this@searchActivity, cityT
                        ,countryT,nameAirT,shortT) }
                }else{
                    flage1=true
                }

                return false
            }
        })
//
//          if(flag==0){
//              fromSelect = short[names.indexOf(parent.getItemAtPosition(position).toString())]
//              FromBtn.setText(names[names.indexOf(parent.getItemAtPosition(position).toString())])
//              mBottomSheetDialog?.dismiss()
//          }else{
//              toSelect= short[names.indexOf(parent.getItemAtPosition(position).toString())]
//              toBtn.setText(names[names.indexOf(parent.getItemAtPosition(position).toString())])
//              mBottomSheetDialog?.dismiss()
//          }

    }

    fun filter(text:String){
        // context?.toast("start filter")
        countryT.clear()
        cityT.clear()
        nameAirT.clear()
        shortT.clear()

        for(i in 0 until country.size){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(country[i].toLowerCase().contains(text.toLowerCase().trim())||
                city[i].toLowerCase().contains(text.toLowerCase().trim())
                ||nameAir[i].toLowerCase().contains(text.toLowerCase().trim())
                ||short[i].toLowerCase().contains(text.toLowerCase().trim())){
                countryT.add(country[i])
                cityT.add(city[i])
                nameAirT.add(nameAir[i])
                shortT.add(short[i])

            }
        }
    }
    override fun onNoteClick(position: Int) {

        if(flage1){
            fromSelect = short[position]
                FromBtn.setText(city[position]+"("+fromSelect+")")
        }else{
            fromSelect = shortT[position]
                FromBtn.setText(cityT[position]+"("+fromSelect+")")
        }

        mBottomSheetDialog!!.dismiss()
    }
    override fun onNoteClick2(position: Int) {
        if(flage1){
            toSelect= short[position]

                toBtn.setText(city[position]+"("+toSelect+")")

        }else{
            toSelect= shortT[position]

                toBtn.setText(cityT[position]+"("+toSelect+")")
        }



        mBottomSheetDialog!!.dismiss()
    }
    }







