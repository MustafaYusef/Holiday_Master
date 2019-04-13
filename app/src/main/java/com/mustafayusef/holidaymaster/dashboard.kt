package com.mustafayusef.holidaymaster

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Hotels.SearchHotels
import com.mustafayusef.holidaymaster.Models.profileAuth
import com.mustafayusef.holidaymaster.tickets.searchActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import okhttp3.*
import java.io.IOException

class dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)

        flight.startAnimation(AnimationUtils.loadAnimation(this@dashboard,R.anim.top_bottum))
        hotel.startAnimation(AnimationUtils.loadAnimation(this@dashboard,R.anim.right_to_left))

        visa.startAnimation(AnimationUtils.loadAnimation(this@dashboard,R.anim.left_to_right))
        prof.startAnimation(AnimationUtils.loadAnimation(this@dashboard,R.anim.right_to_left))
    }
    fun  searchHotel(view: View){
        hotel.startAnimation(AnimationUtils.loadAnimation(this@dashboard,R.anim.fade_in))

        val intent= Intent(this@dashboard,SearchHotels::class.java)
        startActivity(intent)
    }
    fun  searchVisa(view: View){
        visa.startAnimation(AnimationUtils.loadAnimation(this@dashboard,R.anim.fade_in))

        val intent= Intent(this@dashboard,com.mustafayusef.holidaymaster.Visa.searchVisa::class.java)
        startActivity(intent)
    }
    fun  searchFlight(view: View){
        flight.startAnimation(AnimationUtils.loadAnimation(this@dashboard,R.anim.fade_in))
          val intent= Intent(this@dashboard, searchActivity::class.java)
        startActivity(intent)
    }
    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
    fun  showOrofile(view: View){
        prof.startAnimation(AnimationUtils.loadAnimation(this@dashboard,R.anim.fade_in))
        if (verifyAvailableNetwork(this@dashboard)) {
            val request = Request.Builder().url("https://favorite-holiday.herokuapp.com/api/user/checklogin/")
                .addHeader("token", LoginMember.cacheObj.token)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()
                   if(body!!.length>20){

                   }
                    //  println(body)
                    val gson = GsonBuilder().create()
                    val AuthInfo: profileAuth = gson.fromJson(body, profileAuth::class.java)
                    // println(AuthInfo.sesson)
                    runOnUiThread {
                        if(AuthInfo.sesson!=null){
                            val intent = Intent(this@dashboard, Profile::class.java)

                            intent.putExtra("name", AuthInfo.sesson.name)
                            intent.putExtra("email", AuthInfo.sesson.email)
                            intent.putExtra("money", AuthInfo.sesson.money.toString())
                            intent.putExtra("phone", AuthInfo.sesson.phone)

                            startActivity(intent)
                        }else{
                            runOnUiThread {
                                Toast.makeText(applicationContext,"there is no account for you", Toast.LENGTH_SHORT).show()

                            }

                            val intent = Intent(this@dashboard, LoginMember::class.java)



                            startActivity(intent)
                        }


                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }


            })


        } else {
            Toast.makeText(applicationContext, "There is no Internet connection", Toast.LENGTH_SHORT).show()

        }
//        val intent= Intent(this@dashboard,Profile::class.java)
//        startActivity(intent)
    }
}
