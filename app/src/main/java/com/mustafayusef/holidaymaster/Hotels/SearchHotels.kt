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
import android.widget.Spinner
import android.widget.Toast
import com.google.gson.Gson
import com.mustafayusef.holidaymaster.Models.AutoCom

import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search_hotels.*
import java.util.*
import androidx.core.os.HandlerCompat.postDelayed

import android.os.Handler
import com.mustafayusef.holidaymaster.R


@Suppress("NAME_SHADOWING")
class SearchHotels : AppCompatActivity() {
    val context: Context = this
    var checkIn: String = ""
    var checkOut: String = ""
    var AdultNo: Int = 0
    var ChildNo: Int = 0
    var InfantNo: Int = 0
    var CityHot:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_hotels)
        //  ratingBar.rating= 2F


        val options = arrayOf(0,1, 2, 3,4,5,6,7,8,9,10)
        // val AdultHot = findViewById(R.id.spinner) as Spinner
        AdultHot.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, options)
        AdultHot.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // result.text = "Please Select an Option"
                AdultNo = 0

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                AdultNo = options.get(position)

            }
        }
        ChildHot.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, options)
        ChildHot.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // result.text = "Please Select an Option"
                ChildNo = 0

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ChildNo = options.get(position)

            }
        }
        InfantHot.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, options)
        InfantHot.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // result.text = "Please Select an Option"
                InfantNo = 0

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                InfantNo = options.get(position)

            }
        }


        val suggest: Array<AutoCom>
        var json: String = ""
        val objectArrayString: String = context.resources.openRawResource(R.raw.airports)
            .bufferedReader().use { it.readText() }

        val gson = Gson()
        suggest = gson.fromJson(objectArrayString, Array<AutoCom>::class.java)
        // println("suggestion\n"+suggest)

        var names = mutableListOf("")
        var short = mutableListOf("")


        for (i in suggest) {
            names.add(i.city)
            short.add(i.iata)

        }

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)

        CityHotel.setAdapter(adapter)
//        CityHotel.setOnFocusChangeListener({ view, b -> if (b) CityHotel.showDropDown() })




        CityHotel.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            CityHot= names [names.indexOf(parent.getItemAtPosition(position).toString())]

            // Display the clicked item using toast
//            Toast.makeText(applicationContext,"Selected : $selectedItem",Toast.LENGTH_SHORT).show()
        }


        CityHotel.onFocusChangeListener = View.OnFocusChangeListener{
                view, b ->
            if(b){
                Handler().postDelayed(Runnable {  CityHotel.showDropDown() }, 100)
                // Display the suggestion dropdown on focus

            }
        }
    }

        @SuppressLint("SetTextI18n")
        fun chIn(view: View) {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH).toInt()
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR).toInt()
var month1=""
            var dpd = DatePickerDialog(
                this,
                android.R.style.Theme_Material_Light_Dialog,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                    if(month.toString().length<2){
                        month1=("0"+(month+1))
                    }else{
                        month1=(month+1).toString()
                    }
                    checkin.setText("$month1 /$dayOfMonth/$year")

                    checkIn = "$month1/$dayOfMonth/$year"
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
            val dpd = DatePickerDialog(
                this,
                android.R.style.Theme_Material_Light_Dialog,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                    if(month.toString().length<2){
                        month1=("0"+(month+1))
                    }else{
                        month1=(month+1).toString()
                    }

                    checkout.setText("$month1/$dayOfMonth/$year")
                    checkOut = "$month1/$dayOfMonth/$year"
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
        if (verifyAvailableNetwork(this@SearchHotels)) {
        val intent=Intent(this@SearchHotels,ShowHotels::class.java)
        intent.putExtra("checkIn",checkIn)
        intent.putExtra("checkOut",checkOut)
        intent.putExtra("CityHotel",CityHot)
        intent.putExtra("Adult",AdultNo)
        intent.putExtra("Child",ChildNo)
        intent.putExtra("Infant",InfantNo)



        startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()

        }
    }



}
