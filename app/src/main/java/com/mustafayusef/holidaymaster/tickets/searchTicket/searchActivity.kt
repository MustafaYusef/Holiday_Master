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
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.OptionsAdapter
import com.mustafayusef.holidaymaster.login.LoginMember
import com.mustafayusef.holidaymaster.tickets.showHoliday
import kotlinx.android.synthetic.main.activity_check_rooms.*
import kotlinx.android.synthetic.main.activity_options.view.*
import kotlinx.android.synthetic.main.activity_search_hotels.*
import kotlinx.android.synthetic.main.bottom_sheet_airport.view.*


class searchActivity : Fragment(){


    lateinit var myDialog: Dialog

    lateinit var option: Spinner
    lateinit var result: TextView
    lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener
    var flage = true
    var adult = 1
    var child = 0
    var infant = 0
    var type = 1
    var departure = ""
    var Return = ""
    var fromSelect = ""
    var toSelect = ""
    var direct=0
    val name: String = "mustafa yusef"
    var email: String = ""
    var phone: String = ""
    var money: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_search, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AdultPicker.minValue = 1
        AdultPicker.maxValue = 7
        AdultPicker.wrapSelectorWheel = true
        childPicker.minValue = 0
        childPicker.maxValue = 5
        childPicker.wrapSelectorWheel = true
        InfantPicker.minValue = 0
        InfantPicker.maxValue = 5
        InfantPicker.wrapSelectorWheel = true

        oneWay.isActivated = true
        flage = true

        contRet.visibility = View.INVISIBLE
        val options = arrayOf("First","Business","Economy ")
        TypePicker.minValue=0
        TypePicker.maxValue=options.size-1
        TypePicker.displayedValues=options

        TypePicker.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
        type =newVal+1
        }
        val suggest: Array<AutoCom>
        var json: String = ""
        val objectArrayString: String = context!!.resources.openRawResource(R.raw.airports)
            .bufferedReader().use { it.readText() }

        val gson = Gson()
        suggest = gson.fromJson(objectArrayString, Array<AutoCom>::class.java)
        // println("suggestion\n"+suggest)

        var names = mutableListOf("")
        var short = mutableListOf("")

      FromBtn?.setOnClickListener {
          showDailog(names,short,0)
      }
        toBtn?.setOnClickListener {
            showDailog(names,short,1)
        }

        for (i in suggest) {
            names.add(i.city+","+i.country+","+i.name)
            short.add(i.iata)
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
        }
    }
     fun closeKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }



    fun showDailog(names:MutableList<String>,short:MutableList<String>,flag:Int){
        val view = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.bottom_sheet_airport , null)
        val display =activity!!.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y


        view.minimumHeight=600
        val mBottomSheetDialog = Dialog(
            context!!,
            com.mustafayusef.holidaymaster.R.style.MaterialDialogSheet
        )
        mBottomSheetDialog.setContentView(view)
        mBottomSheetDialog.setCancelable(true)
        mBottomSheetDialog.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        mBottomSheetDialog.show()
        var adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_2, names)

       view?. fromCity?.setAdapter(adapter)


        view?.fromCity?.onFocusChangeListener = View.OnFocusChangeListener{
                view, b ->
            if(b){

                    view?.fromCity?.showDropDown()
                // Display the suggestion dropdown on focus
            }
        }
        view?.fromCity?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

          if(flag==0){
              fromSelect = short[names.indexOf(parent.getItemAtPosition(position).toString())]
              FromBtn.setText(names[names.indexOf(parent.getItemAtPosition(position).toString())])
              mBottomSheetDialog?.dismiss()
          }else{
              toSelect= short[names.indexOf(parent.getItemAtPosition(position).toString())]
              toBtn.setText(names[names.indexOf(parent.getItemAtPosition(position).toString())])
              mBottomSheetDialog?.dismiss()
          }

        }

    }

}





