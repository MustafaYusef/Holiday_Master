package com.mustafayusef.holidaymaster.login

import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.KotprefModel
import com.mustafayusef.holidaymaster.Auth.auth
import com.mustafayusef.holidaymaster.Models.profileAuth
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary


import kotlinx.android.synthetic.main.activity_login_member.*
import kotlinx.android.synthetic.main.progress.*


class LoginMember : Fragment() ,lesener{
    override fun onSucsessProfile(response: profileAuth) {

    }

    override fun OnStart() {
        logBtn.isClickable=false
        bookLoading?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        context?.toast(message)
        logBtn.isClickable=true
        bookLoading?.visibility=View.GONE
    }

    override fun onSucsess(AuthInfo: auth) {
        cacheObj.token = AuthInfo.token
        bookLoading?.visibility=View.GONE
      context?.toast("You are login Successfully")
        view?.findNavController()?.navigate(R.id.dashboard2)

    }

    object cacheObj : KotprefModel() {
        var token by stringPref("")
        var ListCost= mutableListOf<Long>(0)
       var phoneP by stringPref("")
        var nameP by stringPref("")
        var emailP by stringPref("")
        var moneyP by longPref(0)

    }
    var logViewModel:LoginViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_login_member, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= LoginViewModelFactory(repostary)
        logViewModel = ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)
        logViewModel?.dataLesener=this

        logBtn.setOnClickListener {
            logViewModel!!.GetLogin(UserName.text.toString(),Password.text.toString())
        }

    }


//    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
//        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
//    }

//    fun Login(view:View){
//        logBtn.isClickable=false
//        if(UserName.text.toString()!=""&&Password.text.toString()!=""){
//            if ( verifyAvailableNetwork(this@LoginMember)) {
//                val client=OkHttpClient()
//                val json=JSONObject()
//                json.put("email",UserName.text)
//                json.put("password",Password.text)
//                val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString())
//                val request = Request.Builder()
//                    .url("https://favorite-holiday.herokuapp.com/api/user/login/")
//                    .post(body)
//                    .build()
//                try {
//                    client.newCall(request).enqueue(object :Callback {
//
//                        override fun onResponse(call: Call, response: Response) {
//                            val body = response.body()?.string()
//                            //println(body)
//                            if(body!!.length>20){
//                                val gson = GsonBuilder().create()
//
//                                val  AuthInfo: auth = gson.fromJson(body,auth ::class.java)
//                                println(AuthInfo.token)
//                                cacheObj.token = AuthInfo.token
////                                LoginMember.cacheObj.nameP=AuthInfo.sesson.name
////                                LoginMember.cacheObj.emailP=  AuthInfo.sesson.email
////                                LoginMember.cacheObj.phoneP=  AuthInfo.sesson.phone
////                                LoginMember.cacheObj.moneyP=  AuthInfo.sesson.money
//                                if(AuthInfo.token!=""){
//                                    val intent = Intent(this@LoginMember, Lottie::class.java)
//                                    startActivity(intent)
//                                    finish()
//                                }
//                            }else{
//                                runOnUiThread {
//                                    Toast.makeText(applicationContext,"there is no account for you", Toast.LENGTH_SHORT).show()
//                                    logBtn.isClickable=true
//                                }
//
//                            }
//
////               runOnUiThread(
////
////
////               )
//
//                        }
//
//                        override fun onFailure(call: Call, e: IOException) {
//
//                            Toast.makeText(applicationContext,"You have no account", Toast.LENGTH_SHORT).show()
//                            logBtn.isClickable=true
//                        }
//
//                    })
//                }catch (e:Exception){
//                    Toast.makeText(applicationContext,"You have no account", Toast.LENGTH_SHORT).show()
//                }
//
//
//
//
//
//
//            }else{
//                Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()
//                logBtn.isClickable=true
//            }
//        }else{
//            Toast.makeText(applicationContext, "You should fill all field", Toast.LENGTH_SHORT).show()
//            logBtn.isClickable=true
//        }
//        logBtn.isClickable=true
//    }
}


