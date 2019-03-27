package com.mustafayusef.holidaymaster

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Models.profileAuth
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception

class Lottie : AppCompatActivity() {
    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        //Holiday_list.layoutManager= LinearLayoutManager(this)

        if ( verifyAvailableNetwork(this@Lottie)) {

            Handler().postDelayed({

                Handler().postDelayed({
                    val request= Request.Builder().url("https://favorite-holiday.herokuapp.com/api/user/checklogin/")
                        .addHeader("token", LoginMember.cacheObj.token)
                        .build()

                    val client= OkHttpClient()
                    try {
                        client.newCall(request).enqueue(object : Callback {

                            override fun onResponse(call: Call, response: Response) {
                                val body=response.body()?.string()

                                println(body)
                                val gson= GsonBuilder().create()
                                val AuthInfo: profileAuth = gson.fromJson(body, profileAuth::class.java)
                                if(AuthInfo.sesson!=null){
                                    val intent = Intent(this@Lottie,searchActivity::class.java)
                                    startActivity(intent)
                                }else{
                                    val intent = Intent(this@Lottie,MainActivity::class.java)
                                    startActivity(intent)
                                }
                                //println(AuthInfo?.sesson)
                            }
                            override fun onFailure(call: Call, e: IOException) {
                                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                            }


                        })
                    }catch (e:Exception){
                        Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()

                    }




                },100)
            },4000)
        }else{
            Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()

        }




    }
}
