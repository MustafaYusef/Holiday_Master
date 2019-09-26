package com.mustafayusef.holidaymaster.tickets.BookTowWay

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.mustafayusef.holidaymaster.Models.bookResTecket.BookTicketResponse
import com.mustafayusef.holidaymaster.Models.ticketDetails.ticketDetails

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.login.LoginMember
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.tickets.BookOneWay.lesenerTicketOne
import com.mustafayusef.holidaymaster.tickets.BookOneWay.ticketDetailsViewModel
import com.mustafayusef.holidaymaster.tickets.BookOneWay.ticketDetailsViewModelFactory
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.fragment_book_final_ticket.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.ticket_popup_picker.view.*
import java.util.*


class BookFinalTicketTow : Fragment(), lesenerTicketOne {
    override fun onSucsessBook(Response: BookTicketResponse) {
       var bundle=Bundle()
        bundle.putString("wallet",Response.data.Wallet.toString())
        bundle.putString("price",Response.data.price .toString())
        bundle.putString("pn",Response.data.AirPNR .toString())
        bookLoading?.visibility=View.GONE
        view?.findNavController()?.navigate(R.id.successTicket,bundle)
    }

    override fun OnStart() {
        bookLoading?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        bookLoading?.visibility=View.GONE
    }

    override fun onSucsess(Response: ticketDetails) {
    }

    var arrayTitle = arrayOf("Miss", "Mr", "Mrs", "Ms")
    var ArrAdult = mutableListOf<String>()
    var WithArray = mutableListOf<Int>()
    var title = ""



    var adtTitleAll = mutableListOf<String>()
    var adtFirstNameAll = mutableListOf<String>()
    var adtLastNameAll = mutableListOf<String>()
    var adtDateOfBirthAll = mutableListOf<String>()
    var adtPassportIssueDateAll = mutableListOf<String>()
    var adtAgeAll = mutableListOf<Int>()
    var adtPassportNoAll = mutableListOf<String>()
    var AdtIssuingCountryAll = mutableListOf<String>()
    var adtExpiryDateAll = mutableListOf<String>()



    var adtTitle = mutableListOf<String>()
    var adtFirstName = mutableListOf<String>()
    var adtLastName = mutableListOf<String>()
    var adtDateOfBirth = mutableListOf<String>()
    var adtPassportIssueDate = mutableListOf<String>()
    var adtAge = mutableListOf<Int>()
    var adtPassportNo = mutableListOf<String>()
    var AdtIssuingCountry = mutableListOf<String>()
    var adtExpiryDate = mutableListOf<String>()

    var chldTitle = mutableListOf<String>()
    var chldFirstName = mutableListOf<String>()
    var chldLastName = mutableListOf<String>()
    var chldDateOfBirth = mutableListOf<String>()
    var chldPassportIssueDate = mutableListOf<String>()
    var chldAge = mutableListOf<Int>()
    var chldPassportNo = mutableListOf<String>()
    var chldIssuingCountry = mutableListOf<String>()
    var chldExpiryDate = mutableListOf<String>()

    var inftTitle = mutableListOf<String>()
    var inftFirstName = mutableListOf<String>()
    var inftLastName = mutableListOf<String>()
    var inftDateOfBirth = mutableListOf<String>()
    var inftPassportIssueDate = mutableListOf<String>()
    var inftAge = mutableListOf<Int>()
    var inftPassportNo = mutableListOf<String>()
    var inftIssuingCountry = mutableListOf<String>()
    var inftExpiryDate = mutableListOf<String>()

    var DateBirth = ""
    var passIssueDate = ""
    var passExpire = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_final_ticket, container, false)
    }
    var countAdult: Int=0
    var countChild: Int=0
    var countInfant: Int=0
    var session=""
    lateinit var viewmodel: ticketDetailsViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var ticket: com.mustafayusef.holidaymaster.Models.ticketDetails.Result =
            arguments?.getSerializable("tecket") as com.mustafayusef.holidaymaster.Models.ticketDetails.Result

        println("session id :"+ticket)

        session=arguments?.getString("session")!!
        var phone = arguments?.getString("phone")
        var email = arguments?.getString("email")
        val networkIntercepter = networkIntercepter(context!!)
        val api = myApis(networkIntercepter)
        val repostary = userRepostary(api)
        val factory = ticketDetailsViewModelFactory(repostary)
        viewmodel = ViewModelProviders.of(this, factory).get(ticketDetailsViewModel::class.java)
        viewmodel?.dataLesener = this

        setArrayAdult(ticket.searchParams.Adult.toInt())

        var countPerson: Int = ticket.searchParams.Adult.toInt() +
                ticket.searchParams.Child.toInt() + ticket.searchParams.Infant.toInt()
        countAdult = ticket.searchParams.Adult.toInt()
        countChild = ticket.searchParams.Child.toInt()
        countInfant= ticket.searchParams.Infant.toInt()
        selectTitle?.setOnClickListener {
            showDilog(arrayTitle)
        }
        travilWithbtn?.setOnClickListener {
            if(ArrAdult.count()!=0){
                showDilogWith()
            }

        }



        DateBirthT.setOnClickListener {
            getDate(0)
        }
        passIssueDateV.setOnClickListener {
            getDate(1)
        }
        passExpireT.setOnClickListener {
            getDate(2)
        }
        var index = 1
        var type="Adult"
        passNum?.text = "$type/Passenger $index"
        NextTicketFinal?.setOnClickListener {
            if(countPerson-1 ==countChild+countInfant){
                type="Child"
                passNum?.text = "$type/Passenger ${index + 1}"
            }
            else if(countPerson-1 ==countInfant)  {
                travilWithbtn.visibility = View.VISIBLE
                type="Infant"
                passNum?.text = "$type/Passenger ${index + 1}"
            }
            if (countPerson > 1) {

                if (!firstNameT.text.isNullOrEmpty() && !lastNameT.text.isNullOrEmpty() &&
                    !nationalityT.text.isNullOrEmpty() && !passPortNumT.text.isNullOrEmpty()
                    && !DateBirth.isNullOrEmpty()
                    && !passIssueDate.isNullOrEmpty() && !passExpire.isNullOrEmpty() && title != ""
                ) {

                    frag_oneT?.startAnimation(
                        AnimationUtils.loadAnimation(
                            context,
                            R.anim.fade_in
                        )
                    )
                    selectTitle?.text = "Select title"

                    index++
                    adtTitleAll.add(title)
                    adtLastNameAll.add(lastNameT.text.toString())
                    adtDateOfBirthAll.add(DateBirth)
                    adtPassportIssueDateAll.add(passIssueDate)

                    adtPassportNoAll.add(passPortNumT.text.toString())
                    AdtIssuingCountryAll.add(nationalityT.text.toString())
                    adtExpiryDateAll.add(passExpire)
                    adtFirstNameAll.add(firstNameT.text.toString())


                    bookTicketCon?.smoothScrollTo(0, 0)
                    firstNameT?.setText("")
                    lastNameT?.setText("")
                    passPortNumT?.setText("")
                    nationalityT?.setText("")
                    DateBirthT?.text = "Date of Birth"
                    passIssueDateV?.text = "Password issue Date"
                    passExpireT?.text = "Password Expiry Date"

                    travilWithbtn?.text = "Travelling with"


                    countPerson--



                } else {
                    context?.toast("Fill all required field ")
                }
            } else   if (!firstNameT.text.isNullOrEmpty() && !lastNameT.text.isNullOrEmpty() &&
                !nationalityT.text.isNullOrEmpty() && !passPortNumT.text.isNullOrEmpty()
                && !DateBirth.isNullOrEmpty()
                && !passIssueDate.isNullOrEmpty() && !passExpire.isNullOrEmpty() && title != ""
            ) {
                adtTitleAll.add(title)
                adtLastNameAll.add(lastNameT.text.toString())
                adtDateOfBirthAll.add(DateBirth)
                adtPassportIssueDateAll.add(passIssueDate)

                adtPassportNoAll.add(passPortNumT.text.toString())
                AdtIssuingCountryAll.add(nationalityT.text.toString())
                adtExpiryDateAll.add(passExpire)
                adtFirstNameAll.add(firstNameT.text.toString())
                countPerson--
                //context?.toast("do something")
                println("arrays adt " + adtPassportNoAll)
                println("arrays child " + adtPassportNoAll)
                println("arrays infant " + adtPassportNoAll)
                println("arrays associated " + WithArray)
                setAge()
                getArrays(ticket.searchParams.Adult.toInt(),ticket.searchParams.Child.toInt(),ticket.searchParams.Infant.toInt())

                viewmodel?.bookFinalTicket(
                    LoginMember.cacheObj.token, WithArray,
                    adtTitle, adtFirstName, adtLastName, adtDateOfBirth,
                    adtPassportIssueDate,
                    adtAge,
                    adtPassportNo,
                    AdtIssuingCountry,
                    adtExpiryDate,
                    chldTitle,
                    chldFirstName,
                    chldLastName,
                    chldDateOfBirth,
                    chldPassportIssueDate,
                    chldAge,
                    chldPassportNo,
                    chldIssuingCountry,
                    chldExpiryDate,

                    inftTitle, inftFirstName, inftLastName,
                    inftDateOfBirth, inftPassportIssueDate, inftAge,
                    inftPassportNo, inftIssuingCountry,
                    inftExpiryDate, ticket.searchParams.Adult.toInt(),
                    ticket.searchParams.Child.toInt(),
                    ticket.searchParams.Infant.toInt(),
                    email!!, phone!!, session, ticket.data[0]._id,
                    ticket.data[0].price.toString(),

                    ticket.searchParams.type, ticket.data, ticket.Baggage
                )
            }else{
                context?.toast("Fill all required field ")
            }

        }
    }
    fun setAge(){
            val calendar = Calendar.getInstance()
       for(i in 0 until adtDateOfBirthAll.count()) {
           adtAgeAll.add((calendar.get(Calendar.YEAR) + 1)-adtDateOfBirthAll[i].subSequence(0,4).toString().toInt() )
       }
    }
    fun getArrays(adult:Int,child:Int,Infant:Int){
         adtTitle=adtTitleAll.subList(0,adult)
         adtFirstName =adtFirstNameAll.subList(0,adult)
         adtLastName =adtLastNameAll.subList(0,adult)
         adtDateOfBirth =adtDateOfBirthAll.subList(0,adult)
         adtPassportIssueDate =adtPassportIssueDateAll.subList(0,adult)
         adtAge =adtAgeAll.subList(0,adult)
         adtPassportNo =adtPassportNoAll.subList(0,adult)
         AdtIssuingCountry =AdtIssuingCountryAll.subList(0,adult)
         adtExpiryDate =adtExpiryDateAll.subList(0,adult)





        if(countChild!=0){
             chldTitle = adtTitleAll.subList(adult,adult+child)
             chldFirstName = adtFirstNameAll.subList(adult,adult+child)
             chldLastName =adtLastNameAll.subList(adult,adult+child)
             chldDateOfBirth = adtDateOfBirthAll.subList(adult,adult+child)
             chldPassportIssueDate =adtPassportIssueDateAll.subList(adult,adult+child)
             chldAge  =adtAgeAll.subList(adult,adult+child)
             chldPassportNo = adtPassportNoAll.subList(adult,adult+child)
             chldIssuingCountry =AdtIssuingCountryAll.subList(adult,adult+child)
             chldExpiryDate = adtExpiryDateAll.subList(adult,adult+child)
        }
        if(countInfant!=0){
             inftTitle = adtTitleAll.subList(adult+child,adult+child+Infant)
             inftFirstName = adtFirstNameAll.subList(adult+child,adult+child+Infant)
             inftLastName = adtLastNameAll.subList(adult+child,adult+child+Infant)
             inftDateOfBirth = adtDateOfBirthAll.subList(adult+child,adult+child+Infant)
             inftPassportIssueDate = adtPassportIssueDateAll.subList(adult+child,adult+child+Infant)
             inftAge =adtAgeAll.subList(adult+child,adult+child+Infant)
             inftPassportNo = adtPassportNoAll.subList(adult+child,adult+child+Infant)
             inftIssuingCountry = AdtIssuingCountryAll.subList(adult+child,adult+child+Infant)
             inftExpiryDate = adtExpiryDateAll.subList(adult+child,adult+child+Infant)
        }

    }




        fun setArrayAdult(num: Int) {
            for (i in 0 until num) {
                ArrAdult.add("Adult ${i + 1}")
                WithArray.add(0)
            }
        }


        fun showDilogWith() {

            val dview: View = layoutInflater.inflate(
                R.layout.ticket_popup_picker, null
            )

            dview.withPicker.minValue = 0
            dview.withPicker.maxValue = ArrAdult.size - 1
            dview.withPicker.wrapSelectorWheel = true
            dview.withPicker.displayedValues = ArrAdult.toTypedArray()
            val builder = AlertDialog.Builder(context!!).setView(dview)
            val malert = builder.show()
            var index = 0
            malert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dview.withPicker.setOnValueChangedListener { picker, oldVal, newVal ->

                //Display the newly selected number to text view
                index = newVal

                // println(country +"   cooodkl,dl")
            }
            dview?.Select?.setOnClickListener {
                WithArray[index]=index+1
                travilWithbtn?.text=ArrAdult[index]
                ArrAdult?.removeAt(index)
                malert?.dismiss()

            }
            dview?.cancelPick?.setOnClickListener {
                malert?.dismiss()
            }

        }

        fun getDate(flage: Int) {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val dpd = DatePickerDialog(
                context!!,

                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->

                    var date = "$year-${month + 1}-$dayOfMonth"
                    if (flage == 0) {

                        DateBirthT.text = date
                         DateBirth = date
                    } else if (flage == 1) {
                        passIssueDateV?.text = date
                        passIssueDate = date
                    } else {
                        passExpireT.text = date
                        passExpire = date
                    }
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        fun showDilog(array: Array<String>) {
            val dview: View = layoutInflater.inflate(
                R.layout.ticket_popup_picker, null
            )
            dview.withPicker.minValue = 0
            dview.withPicker.maxValue = array.size - 1
            dview.withPicker.wrapSelectorWheel = true
            dview.withPicker.displayedValues = array
            val builder = AlertDialog.Builder(context!!).setView(dview)
            val malert = builder.show()
            var index = 0
            malert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dview.withPicker.setOnValueChangedListener { picker, oldVal, newVal ->

                //Display the newly selected number to text view
                index = newVal
                // println(country +"   cooodkl,dl")
            }
            dview?.Select?.setOnClickListener {
                title = array[index]
                selectTitle?.text = array[index]


                malert?.dismiss()
            }
            dview?.cancelPick?.setOnClickListener {
                malert?.dismiss()
            }

        }

    }


