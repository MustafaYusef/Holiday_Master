package com.mustafayusef.holidaymaster

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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
import com.mustafayusef.holidaymaster.Adapters.TowWayAdapter
import com.mustafayusef.holidaymaster.Models.AutoCom
import com.mustafayusef.holidaymaster.Models.profileAuth
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_show_holiday.*
import okhttp3.*


class searchActivity : AppCompatActivity() {
lateinit var myDialog:Dialog
    val context: Context = this
    lateinit var option : Spinner
    lateinit var result : TextView
    lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener
    var flage=false
    var adult=0
    var child=0
    var infant=0
    var type=""
    var departure=""
    var Return=""
    var fromSelect=""
    var toSelect=""
    val name:String="mustafa yusef"
    var email:String = ""
    var phone:String=""
    var money:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        AdultPicker.minValue=0
        AdultPicker.maxValue=10
        AdultPicker.wrapSelectorWheel=true
        childPicker.minValue=0
        childPicker.maxValue=10
        childPicker.wrapSelectorWheel=true
        InfantPicker.minValue=0
        InfantPicker.maxValue=10
        InfantPicker.wrapSelectorWheel=true




        button17.isActivated=true
        flage=true
        retun.visibility=View.INVISIBLE
        option = findViewById(R.id.spinner) as Spinner

        val options = arrayOf("Economy", "First","Class")

        option.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
               // result.text = "Please Select an Option"
                type="Economy"

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                type=options.get(position)

            }
        }

        val suggest:Array<AutoCom>
        var json:String=""
        val objectArrayString: String = context.resources.openRawResource(R.raw.airports)
            .bufferedReader().use { it.readText() }

        val gson = Gson()
        suggest=gson.fromJson(objectArrayString,Array<AutoCom>::class.java)
         // println("suggestion\n"+suggest)

        var names=mutableListOf("")
        var short=mutableListOf("")


        for(i in suggest){
            names.add(i.city)
            short.add(i.iata)

        }

        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,names)

        fromCity .setAdapter(adapter)
        fromCity.setOnFocusChangeListener({ view, b -> if(b) fromCity.showDropDown()})

        fromCity.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            fromSelect =short[names.indexOf(parent.getItemAtPosition(position).toString())]
            // Display the clicked item using toast
//            Toast.makeText(applicationContext,"Selected : $selectedItem",Toast.LENGTH_SHORT).show()
        }

        to.setAdapter(adapter)
        to.setOnFocusChangeListener({view, b -> if(b) to.showDropDown() })
        toSelect=to.text.toString()
        to.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            toSelect =short[names.indexOf(parent.getItemAtPosition(position).toString())]

        }



        AdultPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            adult =newVal
        }
        childPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            child =newVal
        }
        InfantPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            infant =newVal
        }
        }







    @SuppressLint("SetTextI18n")
    fun funDate (view:View){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(this, android.R.style.Theme_Material_Light_Dialog, DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->

            dep.setText("$year-$monthOfYear-$dayOfMonth")
            departure="$year-$monthOfYear-$dayOfMonth"
        }, year, month, day)

        //show datepicker
        dpd.show()
    }


    @SuppressLint("SetTextI18n")
    fun dateRet (view:View){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(this, android.R.style.Theme_Material_Light_Dialog, DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->

            retun.setText("$year-$monthOfYear-$dayOfMonth")
            Return="$year-$monthOfYear-$dayOfMonth"
        }, year, month, day)

        //show datepicker
        dpd.show()
    }






    fun showHolidys(view: View){
        val intent =Intent(this@searchActivity,showHoliday::class.java)

           intent.putExtra("flage",flage)
        intent.putExtra("adult",adult)
        intent.putExtra("child",child)
        intent.putExtra("infant",infant)
        intent.putExtra("departure",departure)
        intent.putExtra("Return",Return)
        intent.putExtra("Type",type)
        intent.putExtra("fromSelect",fromSelect)
        intent.putExtra("toSelect",toSelect)

        startActivity(intent)

    }


    fun Oneway(view: View){
        button17.isActivated=true
        button10.isActivated=false
        flage=true
        retun.visibility=View.INVISIBLE

    }
    fun TowWay(view: View){
        button10.isActivated=true
        button17.isActivated=false

        flage=false
        retun.visibility=View.VISIBLE

    }
    fun backSearch(view:View){
        val intent =Intent(this@searchActivity,MainActivity::class.java)


        startActivity(intent)
    }
    fun profile(view:View){

        //Holiday_list.layoutManager= LinearLayoutManager(this)
        val request= Request.Builder().url("https://favorite-holiday.herokuapp.com/api/user/checklogin/")
            .addHeader("token", LoginMember.cacheObj.token)
            .build()
       // println(cacheObj.token)
//        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjODE1NmY4NjA3M2U3NTU1MDhlZDNjMiIsImlhdCI6MTU1MjU2ODc5MywiZXhwIjoxNTU0OTg3OTkzfQ.9Z62kOCV6i6CQaYqM9yk3zHyh2j1bt713Rk7kAicigk "
        val client= OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body=response.body()?.string()

                println(body)
                val gson= GsonBuilder().create()
                val  AuthInfo: profileAuth = gson.fromJson(body, profileAuth::class.java)
                //name=AuthInfo.sesson.name

          println(AuthInfo.sesson)
                runOnUiThread {
                    val intent =Intent(this@searchActivity,Profile::class.java)

                    intent.putExtra("name",AuthInfo.sesson.name)
                    intent.putExtra("email",AuthInfo.sesson.email)
                    intent.putExtra("money",AuthInfo.sesson.phone)
                    intent.putExtra("phone",AuthInfo.sesson.money.toString())



                    startActivity(intent)


                }

            }
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }


        })


    }







}






