package com.mustafayusef.holidaymaster

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Models.profileAuth
import com.mustafayusef.holidaymaster.login.LoginMember
import kotlinx.android.synthetic.main.activity_profile.*
import okhttp3.*
import java.io.IOException

class Lottie : Fragment() {
//    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
//        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_lottie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar> (R.id.ToolBar)
//        view?.findNavController()?.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.lottie2) {
//
//                toolbar?.visibility = View.GONE
//            } else {
//
//                toolbar?.visibility = View.VISIBLE
//            }
//
//        }

        //Holiday_list.layoutManager= LinearLayoutManager(this)
       Handler().postDelayed({

                Handler().postDelayed({
        if(LoginMember.cacheObj.token!=""){
            view.findNavController()?.navigate(R.id.dashboard2)
        }else{
            view.findNavController()?.navigate(R.id.mainActivity)
        // activity!!.finish()
        }
                },100)
            },4000)
//        if ( verifyAvailableNetwork(this@Lottie)) {
//
//            Handler().postDelayed({
//
//                Handler().postDelayed({
//                    val request= Request.Builder().url("https://favorite-holiday.herokuapp.com/api/user/checklogin/")
//                        .addHeader("token", LoginMember.cacheObj.token)
//                        .build()
//
//                    val client= OkHttpClient()
//
//                        client.newCall(request).enqueue(object : Callback {
//
//                            override fun onResponse(call: Call, response: Response) {
//                                val body=response.body()?.string()
//                                if(body!!.length>40){
//                                    val gson= GsonBuilder().create()
//                                    val AuthInfo: profileAuth = gson.fromJson(body, profileAuth::class.java)
//                                      LoginMember.cacheObj.nameP=AuthInfo.sesson.name
//                                     LoginMember.cacheObj.emailP=  AuthInfo.sesson.email
//                                     LoginMember.cacheObj.phoneP=  AuthInfo.sesson.phone
//                                    LoginMember.cacheObj.moneyP=  AuthInfo.sesson.money
//                                        val intent = Intent(this@Lottie,dashboard::class.java)
//                                        startActivity(intent)
//                                    finish()
//                                    }else{
//                                    view?.findNavController()?.navigate(R.id.mainActivity)
//
//
//                                    }
//                                }
//
//
//                                //println(AuthInfo?.sesson)
//
//                            override fun onFailure(call: Call, e: IOException) {
//                                runOnUiThread {
//                                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
//                                    val intent = Intent(this@Lottie,dashboard::class.java)
//                                    startActivity(intent)
//                                    finish()
//                                }
//                            }
//
//
//                        })
//
//
//
//
//
//                },100)
//            },4000)
//        }else{
//            Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this@Lottie,MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }




    }
}
