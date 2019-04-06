package com.mustafayusef.holidaymaster

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_search.*
//import netscape.javascript.JSObject.getWindow
//import com.sun.deploy.ui.CacheUpdateProgressDialog.dismiss
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import java.io.IOException
import java.util.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Hotels.SearchHotels
import com.mustafayusef.holidaymaster.Models.AutoCom
import com.mustafayusef.holidaymaster.Models.profileAuth
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_search_hotels.*
import kotlinx.android.synthetic.main.bottom_sheet_emp_cov.*
import kotlinx.android.synthetic.main.bottom_sheet_emp_cov.view.*
import okhttp3.*
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.view.Display




class searchActivity : AppCompatActivity() {
    lateinit var myDialog: Dialog
    val context: Context = this
    lateinit var option: Spinner
    lateinit var result: TextView
    lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener
    var flage = false
    var adult = 0
    var child = 0
    var infant = 0
    var type = "Economy"
    var departure = ""
    var Return = ""
    var fromSelect = ""
    var toSelect = ""
    val name: String = "mustafa yusef"
    var email: String = ""
    var phone: String = ""
    var money: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)







        AdultPicker.minValue = 0
        AdultPicker.maxValue = 10
        AdultPicker.wrapSelectorWheel = true
        childPicker.minValue = 0
        childPicker.maxValue = 10
        childPicker.wrapSelectorWheel = true
        InfantPicker.minValue = 0
        InfantPicker.maxValue = 10
        InfantPicker.wrapSelectorWheel = true




        button17.isActivated = true
        flage = true
        retL.visibility = View.INVISIBLE



        val options = arrayOf("Economy","First")
        TypePicker.minValue=0
        TypePicker.maxValue=options.size-1
        TypePicker.displayedValues=options

        TypePicker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
           type =options[newVal]
        }




//        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
//        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // result.text = "Please Select an Option"
//                type = "Economy"
//
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                type = options.get(position)
//
//            }
//        }

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
            names.add(i.city+","+i.country)
            short.add(i.iata)

        }

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)

        fromCity.setAdapter(adapter)
        fromCity.onFocusChangeListener = View.OnFocusChangeListener{
                view, b ->
            if(b){
                Handler().postDelayed(Runnable {  fromCity.showDropDown() }, 100)
                // Display the suggestion dropdown on focus

            }
        }

        fromCity.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            fromSelect = short[names.indexOf(parent.getItemAtPosition(position).toString())]
            // Display the clicked item using toast
//            Toast.makeText(applicationContext,"Selected : $selectedItem",Toast.LENGTH_SHORT).show()
        }
        to.setAdapter(adapter)
        to.onFocusChangeListener = View.OnFocusChangeListener{
                view, b ->
            if(b){
                Handler().postDelayed(Runnable {  to.showDropDown() }, 100)
                // Display the suggestion dropdown on focus

            }
        }
        toSelect = to.text.toString()
        to.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            toSelect = short[names.indexOf(parent.getItemAtPosition(position).toString())]

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
    }


//    fun Context.hideKeyboard(view: View) {
//        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//    }


    @SuppressLint("SetTextI18n")
    fun funDate(view: View) {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(
            this,
            android.R.style.Theme_Material_Light_Dialog,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->

                dep.setText("$year-${month+1}-$dayOfMonth")
                departure = "$year-${month+1}-$dayOfMonth"
            },
            year,
            month,
            day
        )

        //show datepicker
        dpd.show()
    }


    @SuppressLint("SetTextI18n")
    fun dateRet(view: View) {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH).toInt()
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR).toInt()

        val dpd = DatePickerDialog(
            this,
            android.R.style.Theme_Material_Light_Dialog,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->

                retun.setText("$year-${month+1}-$dayOfMonth")
                Return = "$year-${month+1}-$dayOfMonth"
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

    fun showHolidys(view: View) {
        if(flage){
            if(adult!=0&&departure!="" && fromSelect!="" &&toSelect!=""){
                if (verifyAvailableNetwork(this@searchActivity)) {
                    val intent = Intent(this@searchActivity, showHoliday::class.java)

                    intent.putExtra("flage", flage)
                    intent.putExtra("adult", adult)
                    intent.putExtra("child", child)
                    intent.putExtra("infant", infant)
                    intent.putExtra("departure", departure)
                    intent.putExtra("Return", Return)
                    intent.putExtra("Type", type)
                    intent.putExtra("fromSelect", fromSelect)
                    intent.putExtra("toSelect", toSelect)

                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(applicationContext, "should fill all requierd field", Toast.LENGTH_SHORT).show()

            }
        }else{
            if(adult!=0&&departure!="" &&Return!="" && fromSelect!="" &&toSelect!=""){
                if (verifyAvailableNetwork(this@searchActivity)) {
                    val intent = Intent(this@searchActivity, showHoliday::class.java)

                    intent.putExtra("flage", flage)
                    intent.putExtra("adult", adult)
                    intent.putExtra("child", child)
                    intent.putExtra("infant", infant)
                    intent.putExtra("departure", departure)
                    intent.putExtra("Return", Return)
                    intent.putExtra("Type", type)
                    intent.putExtra("fromSelect", fromSelect)
                    intent.putExtra("toSelect", toSelect)

                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(applicationContext, "should fill all requierd field", Toast.LENGTH_SHORT).show()

            }
        }





    }
    fun passData(view:View){

    }
//

fun goToHotel(view: View){
    val intent=Intent(this@searchActivity,SearchHotels::class.java)
    startActivity(intent)
}

    fun Oneway(view: View) {




        button17.isActivated = true
        button10.isActivated = false
        flage = true
        retL.visibility = View.INVISIBLE

    }

    fun TowWay(view: View) {
        button10.isActivated = true
        button17.isActivated = false

        flage = false
        retL.visibility = View.VISIBLE

    }

    fun backSearch(view: View) {
        val intent = Intent(this@searchActivity, MainActivity::class.java)


        startActivity(intent)
    }

    fun profile(view: View) {

        //Holiday_list.layoutManager= LinearLayoutManager(this)

        if (verifyAvailableNetwork(this@searchActivity)) {
            val request = Request.Builder().url("https://favorite-holiday.herokuapp.com/api/user/checklogin/")
                .addHeader("token", LoginMember.cacheObj.token)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()

                  //  println(body)
                    val gson = GsonBuilder().create()
                    val AuthInfo: profileAuth = gson.fromJson(body, profileAuth::class.java)
                   // println(AuthInfo.sesson)
                    runOnUiThread {
                        if(AuthInfo.sesson!=null){
                            val intent = Intent(this@searchActivity, Profile::class.java)

                            intent.putExtra("name", AuthInfo.sesson.name)
                            intent.putExtra("email", AuthInfo.sesson.email)
                            intent.putExtra("money", AuthInfo.sesson.phone)
                            intent.putExtra("phone", AuthInfo.sesson.money.toString())

                            startActivity(intent)
                        }else{
                            val intent = Intent(this@searchActivity, LoginMember::class.java)



                            startActivity(intent)
                        }


                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }


            })


        } else {
            Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()

        }

    }
}





