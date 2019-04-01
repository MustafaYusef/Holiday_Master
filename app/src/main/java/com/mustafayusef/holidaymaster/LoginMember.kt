package com.mustafayusef.holidaymaster

import android.os.Bundle
import android.view.View
import android.R.string
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.KotprefModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.mustafayusef.holidaymaster.Auth.auth
import com.mustafayusef.holidaymaster.Models.profileAuth


import kotlinx.android.synthetic.main.activity_login_member.*
import kotlinx.android.synthetic.main.activity_show_holiday.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception


class LoginMember : AppCompatActivity() {
    object cacheObj : KotprefModel() {
        var token by stringPref("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_member)
        Kotpref.init(this)
    }
    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun Login(view:View){
        if ( verifyAvailableNetwork(this@LoginMember)) {
        val client=OkHttpClient()
        val json=JSONObject()
        json.put("email",UserName.text)
        json.put("password",Password.text)
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString())
        val request = Request.Builder()
            .url("https://favorite-holiday.herokuapp.com/api/user/login/")
            .post(body)
            .build()
      try {
          client.newCall(request).enqueue(object :Callback {

              override fun onResponse(call: Call, response: Response) {
                  val body = response.body()?.string()
                  //println(body)
                  val gson = GsonBuilder().create()

                  val  AuthInfo: auth = gson.fromJson(body,auth ::class.java)
                  println(AuthInfo.token)
                  cacheObj.token = AuthInfo.token

                  if(AuthInfo.token!=""){
                      val intent = Intent(this@LoginMember,searchActivity::class.java)
                      startActivity(intent)
                  }
//               runOnUiThread(
//
//
//               )

              }

              override fun onFailure(call: Call, e: IOException) {
                val intent = Intent(this@LoginMember,MainActivity::class.java)
                startActivity(intent)
                  Toast.makeText(applicationContext,"You have no account", Toast.LENGTH_SHORT).show()
              }

          })
      }catch (e:Exception){
          Toast.makeText(applicationContext,"You have no account", Toast.LENGTH_SHORT).show()
      }






        }else{
            Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()

        }
    }
}


