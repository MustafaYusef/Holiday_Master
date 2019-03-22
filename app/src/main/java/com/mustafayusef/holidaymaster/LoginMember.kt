package com.mustafayusef.holidaymaster

import android.os.Bundle
import android.view.View
import android.R.string
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.KotprefModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.mustafayusef.holidaymaster.Auth.auth


import kotlinx.android.synthetic.main.activity_login_member.*
import kotlinx.android.synthetic.main.activity_show_holiday.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException





class LoginMember : AppCompatActivity() {
    object cacheObj : KotprefModel() {
        var token by stringPref("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_member)
        Kotpref.init(this)
    }

    fun Login(view:View){

        val client=OkHttpClient()
        val json=JSONObject()
        json.put("email",UserName.text)
        json.put("password",Password.text)
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString())
        val request = Request.Builder()
            .url("https://favorite-holiday.herokuapp.com/api/user/login/")
            .post(body)
            .build()

        client.newCall(request).enqueue(object :Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)
                val gson = GsonBuilder().create()

                  val  AuthInfo: auth = gson.fromJson(body,auth ::class.java)
                  println(AuthInfo.token)
                  cacheObj.token = AuthInfo.token
                println(cacheObj.token)

            }

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }

        })
        val intent = Intent(this@LoginMember,searchActivity::class.java)
        startActivity(intent)





    }
}


