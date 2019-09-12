package com.mustafayusef.holidaymaster.login.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.chibatching.kotpref.bulk
import com.mustafayusef.holidaymaster.Auth.auth
import com.mustafayusef.holidaymaster.MainActivity
import com.mustafayusef.holidaymaster.Models.profileAuth
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.login.LoginMember
import com.mustafayusef.holidaymaster.login.LoginViewModel
import com.mustafayusef.holidaymaster.login.LoginViewModelFactory
import com.mustafayusef.holidaymaster.login.lesener
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : Fragment() ,lesener{
    override fun onSucsessProfile(response: profileAuth) {
     LoginMember.cacheObj.nameP=response.sesson.name
  LoginMember.cacheObj.emailP=response.sesson.email
  LoginMember.cacheObj.phoneP=response.sesson.phone
  LoginMember.cacheObj.moneyP=response.sesson.money


        username.text= LoginMember.cacheObj.nameP
        Email.text= LoginMember.cacheObj.emailP
        Phone.text= LoginMember.cacheObj.phoneP
        Money.text= LoginMember.cacheObj.moneyP.toString()
    }

    override fun OnStart() {

    }

    override fun onFailer(message: String) {
    }

    override fun onSucsess(Response: auth) {
    }

    var logViewModel:LoginViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LogOUT?.setOnClickListener {
            LoginMember.cacheObj.bulk {
                token=""
                phoneP=""
                nameP=""
                emailP=""
                moneyP=0
            }
            view.findNavController().navigate(R.id.mainActivity)
        }
//        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
//        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.profile) {
//
//                toolbar?.visibility = View.GONE
//            } else {
//
//                toolbar?.visibility = View.VISIBLE
//            }
//
//        }
      // Kotpref.init(this)
//        var name=intent.getStringExtra("name")
//       var email= intent.getStringExtra("email")
//        var phone=intent.getStringExtra("phone")
//       var money= intent.getStringExtra("money")



     //  println(name+"     "+email)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= LoginViewModelFactory(repostary)
        logViewModel = ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)
        logViewModel?.dataLesener=this
        logViewModel?.Profile(LoginMember.cacheObj.token)
    }
//    fun backsearch(view:View){
//        val intent= Intent(this@Profile,dashboard::class.java)
//        startActivity(intent)
//    }

//    fun  showrofile(){
//        if (verifyAvailableNetwork(this@Profile)) {
//            val request = Request.Builder().url("https://favorite-holiday.herokuapp.com/api/user/checklogin/")
//                .addHeader("token", LoginMember.cacheObj.token)
//                .build()
//            val client = OkHttpClient()
//            client.newCall(request).enqueue(object : Callback {
//
//                override fun onResponse(call: Call, response: Response) {
//                    val body = response.body()?.string()
//                    if(body!!.length>20){
//
//                    }
//                    //  println(body)
//                    val gson = GsonBuilder().create()
//                    val AuthInfo: profileAuth = gson.fromJson(body, profileAuth::class.java)
//                    // println(AuthInfo.sesson)
//                    runOnUiThread {
//                        if(AuthInfo.sesson!=null){
//
//                            username.text=AuthInfo.sesson.name
//                            Email.text=AuthInfo.sesson.email
//                            Phone.text=AuthInfo.sesson.phone
//                            Money.text=AuthInfo.sesson.money.toString()
//
//
//
//                        }else{
//                            runOnUiThread {
//                                Toast.makeText(applicationContext,"there is no account for you", Toast.LENGTH_SHORT).show()
//
//                            }
//
//                            val intent = Intent(this@Profile, LoginMember::class.java)
//
//
//
//                            startActivity(intent)
//                        }
//
//
//                    }
//                }
//
//                override fun onFailure(call: Call, e: IOException) {
//                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
//                }
//
//
//            })
//
//
//        } else {
//            Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()
//            // finish()
//        }
////        val intent= Intent(this@dashboard,Profile::class.java)
////        startActivity(intent)
//    }
//    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
//        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
//    }
}
