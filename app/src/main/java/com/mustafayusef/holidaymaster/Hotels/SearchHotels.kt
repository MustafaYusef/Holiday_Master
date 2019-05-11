package com.mustafayusef.holidaymaster.Hotels

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_search_hotels.*
import java.util.*

import android.os.Handler
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Models.namesContry
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.dashboard
import kotlinx.android.synthetic.main.activity_search.*
import okhttp3.*
import java.io.IOException
import kotlin.to


@Suppress("NAME_SHADOWING")
class SearchHotels : AppCompatActivity() {
     var chAge= mutableListOf<Int>(0,0,0,0,0)
    val context: Context = this
    var checkIn: String = ""
    var checkOut: String = ""
    var AdultNo: Int = 0
    var ChildNo: Int = 0
    var CityHot:String=""

    var chAge1=0
    var chAge2=0
    var chAge3=0
    var chAge4=0
    var chAge5=0

    var country= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_hotels)
//        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        //  ratingBar.rating= 2F
        PickersCh.visibility=View.INVISIBLE

        val options = arrayOf(1, 2, 3,4,5,6,7)

        // val AdultHot = findViewById(R.id.spinner) as Spinner
        AdultHot.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, options)
        AdultHot.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // result.text = "Please Select an Option"
                AdultNo = options.get(0)

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                AdultNo = options.get(position)

            }
        }
        ch1Picker.minValue = 2
        ch1Picker.maxValue = 12
        ch1Picker.wrapSelectorWheel = true

        ch2Picker.minValue = 2
        ch2Picker.maxValue = 12
        ch2Picker.wrapSelectorWheel = true

        ch3Picker.minValue = 2
        ch3Picker.maxValue = 12
        ch3Picker.wrapSelectorWheel = true

        ch4Picker.minValue = 2
        ch4Picker.maxValue = 12
        ch4Picker.wrapSelectorWheel = true

        ch5Picker.minValue = 2
        ch5Picker.maxValue = 12
        ch5Picker.wrapSelectorWheel = true






        val options1 = arrayOf(0,1, 2, 3,4,5)
        ChildHot.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, options1)
        ChildHot.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // result.text = "Please Select an Option"
                ChildNo = 0

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ChildNo = options1.get(position)
                checkPicker()
            }

        }

        ch1Picker.setOnValueChangedListener { picker, oldVal, newVal ->
            chAge1 = newVal

        }
        ch2Picker.setOnValueChangedListener { picker, oldVal, newVal ->
            chAge2 = newVal


        }
        ch3Picker.setOnValueChangedListener { picker, oldVal, newVal ->
            chAge3 = newVal

        }
        ch4Picker.setOnValueChangedListener { picker, oldVal, newVal ->
            chAge4 = newVal

        }
        ch5Picker.setOnValueChangedListener { picker, oldVal, newVal ->
            chAge5 = newVal
        }


          //var suggest:List<namesContry>?=null
        CityHotel.isActivated=false
        RunCountry()

//        val suggest: Array<AutoCom>
//        var json: String = ""
//        val objectArrayString: String = context.resources.openRawResource(R.raw.airports)
//            .bufferedReader().use { it.readText() }
//
//        val gson = Gson()
//        suggest = gson.fromJson(objectArrayString, Array<AutoCom>::class.java)
//        // println("suggestion\n"+suggest)
//
//        var showNames = mutableListOf("")
//        var short = mutableListOf("")
//        var names=mutableListOf("")
//
//
//        for (i in suggest) {
//            showNames.add(i.city+","+i.country)
//            short.add(i.iata)
//            names.add(i.city)
//
//        }

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, country)

        CityHotel.setAdapter(adapter)

        CityHotel.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            CityHot= country!![position]
            closeKeyboard()
            // Display the clicked item using toast
//            Toast.makeText(applicationContext,"Selected : $selectedItem",Toast.LENGTH_SHORT).show()
        }


        CityHotel.onFocusChangeListener = View.OnFocusChangeListener{
                view, b ->
            if(b){
                Handler().postDelayed(Runnable {
                    //CityHotel.showDropDown()
                    closeKeyboard()
                }, 100)
                // Display the suggestion dropdown on focus

            }
        }



    }
    fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
 fun checkPicker(){
     if(ChildNo>0){
         PickersCh.visibility=View.VISIBLE
         when(ChildNo){
             1->{
//                 chAge1=2
                 ch1Picker.visibility=View.VISIBLE
                 ch1T.visibility=View.VISIBLE

                 ch2Picker.visibility=View.INVISIBLE
                 ch2T.visibility=View.INVISIBLE

                 ch3Picker.visibility=View.INVISIBLE
                 ch3T.visibility=View.INVISIBLE

                 ch4Picker.visibility=View.INVISIBLE
                 ch4T.visibility=View.INVISIBLE

                 ch5Picker.visibility=View.INVISIBLE
                 ch5T.visibility=View.INVISIBLE
             }
             2->{
//                 chAge2=2
//                 chAge1=2
                 ch1Picker.visibility=View.VISIBLE
                 ch1T.visibility=View.VISIBLE

                 ch2Picker.visibility=View.VISIBLE
                 ch2T.visibility=View.VISIBLE

                 ch3Picker.visibility=View.INVISIBLE
                 ch3T.visibility=View.INVISIBLE

                 ch4Picker.visibility=View.INVISIBLE
                 ch4T.visibility=View.INVISIBLE

                 ch5Picker.visibility=View.INVISIBLE
                 ch5T.visibility=View.INVISIBLE
             }
             3->{
//                 chAge2=2
//                 chAge1=2
//                 chAge3=2
                 ch1Picker.visibility=View.VISIBLE
                 ch1T.visibility=View.VISIBLE

                 ch2Picker.visibility=View.VISIBLE
                 ch2T.visibility=View.VISIBLE

                 ch3Picker.visibility=View.VISIBLE
                 ch3T.visibility=View.VISIBLE

                 ch4Picker.visibility=View.INVISIBLE
                 ch4T.visibility=View.INVISIBLE

                 ch5Picker.visibility=View.INVISIBLE
                 ch5T.visibility=View.INVISIBLE
             }
             4->{
//                 chAge2=2
//                 chAge1=2
//                 chAge3=2
//                 chAge4=2
                 ch1Picker.visibility=View.VISIBLE
                 ch1T.visibility=View.VISIBLE

                 ch2Picker.visibility=View.VISIBLE
                 ch2T.visibility=View.VISIBLE

                 ch3Picker.visibility=View.VISIBLE
                 ch3T.visibility=View.VISIBLE

                 ch4Picker.visibility=View.VISIBLE
                 ch4T.visibility=View.VISIBLE

                 ch5Picker.visibility=View.INVISIBLE
                 ch5T.visibility=View.INVISIBLE
             }
             5->{
//                 chAge2=2
//                 chAge1=2
//                 chAge3=2
//                 chAge4=2
//                 chAge5=2
                 ch1Picker.visibility=View.VISIBLE
                 ch1T.visibility=View.VISIBLE

                 ch2Picker.visibility=View.VISIBLE
                 ch2T.visibility=View.VISIBLE

                 ch3Picker.visibility=View.VISIBLE
                 ch3T.visibility=View.VISIBLE

                 ch4Picker.visibility=View.VISIBLE
                 ch4T.visibility=View.VISIBLE

                 ch5Picker.visibility=View.VISIBLE
                 ch5T.visibility=View.VISIBLE
             }
         }

     }else{
         PickersCh.visibility=View.INVISIBLE
     }
 }
        @SuppressLint("SetTextI18n")
        fun chIn(view: View) {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH).toInt()
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR).toInt()
var month1=""
            var day1=""
            var dpd = DatePickerDialog(
                this,

                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                    if(month.toString().length<2){
                        month1=("0"+(month+1))
                    }else{
                        month1=(month+1).toString()
                    }

                    if(dayOfMonth.toString().length<2){
                        day1=("0"+(dayOfMonth))
                    }else{
                        day1=(dayOfMonth).toString()
                    }
                    checkin.setText("$month1/$day1/$year")

                    checkIn =checkin.text.toString()
                },
                year,
                month,
                day
            )

            //show datepicker
            dpd.show()
        }


    @SuppressLint("SetTextI18n")
        fun chout(view: View) {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH).toInt()
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR).toInt()
        var month1=""
        var day1=""
            val dpd = DatePickerDialog(
                this,

                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                    if(month.toString().length<2){
                        month1=("0"+(month+1))
                    }else{
                        month1=(month+1).toString()
                    }

                    if(dayOfMonth.toString().length<2){
                        day1=("0"+(dayOfMonth))
                    }else{
                        day1=(dayOfMonth).toString()
                    }


                    checkout.setText("$month1/$day1/$year")
                    checkOut = checkout.text.toString()
                },
                year,
                month,
                day
            )

            //show datepicker
            dpd.show()
        }

    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
    fun goToHotels(view:View){
        chAge[0]=chAge1
        chAge[1]=chAge2
        chAge[2]=chAge3
        chAge[3]=chAge4
        chAge[4]=chAge5

            if(CityHot!=""){
                if(checkIn!="" && checkOut!="" ){
                if (verifyAvailableNetwork(this@SearchHotels)) {
                    val intent=Intent(this@SearchHotels,ShowHotels::class.java)
                    intent.putExtra("checkIn",checkIn)
                    intent.putExtra("checkOut",checkOut)
                    intent.putExtra("CityHotel",CityHot)
                    intent.putExtra("Adult",AdultNo)
                    intent.putExtra("Child",ChildNo)
                    println(chAge)
                    intent.putExtra("chAge1",chAge[0])
                    intent.putExtra("chAge2",chAge[1])
                    intent.putExtra("chAge3",chAge[2])
                    intent.putExtra("chAge4",chAge[3])
                    intent.putExtra("chAge5",chAge[4])


                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()

                }
            }else{
                    Toast.makeText(applicationContext, "should fill checkin and checkout date", Toast.LENGTH_SHORT).show()

                }

        }else{
                CityHotel.startAnimation(AnimationUtils.loadAnimation(this@SearchHotels,R.anim.shake))
                CityHotel.text.clear()
                CityHotel.hint="Select from List"
                CityHotel.setHintTextColor(-0x01ffff)
                CityHotel.highlightColor=-0x01ffff
                Toast.makeText(applicationContext, "You should select from list", Toast.LENGTH_SHORT).show()
        }

    }
    fun backMain(view: View){
        val intent=Intent(this@SearchHotels,dashboard::class.java)
        startActivity(intent)
    }

    fun RunCountry(){
        //val url="https://favorite-holiday.herokuapp.com/api/orders/twoway?from=$from&to=$to&Ddata=$departure&adt=$adult&type=$type&chd=$child&Rdata=$Return"



        val client= OkHttpClient()




        val request = Request.Builder()
            .url("https://favorite-holiday.herokuapp.com/api/holet/cities")

            .build()


            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response){
                    val body=response.body()?.string()
                    println(body)

                        val gson= GsonBuilder().create()

                    var Country:List<namesContry> = gson.fromJson(body, Array<namesContry>::class.java).toList()
                    for(i in 0 until Country!!.size){
                        country.add( Country!![i].name!!)
                    }






                }

                override fun onFailure(call: Call, e: IOException) {

                    //Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }

            })

      CityHotel.isActivated=true
    }



}
