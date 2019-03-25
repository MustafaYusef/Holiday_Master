package com.mustafayusef.holidaymaster

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        //Holiday_list.layoutManager= LinearLayoutManager(this)


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



    }
}
