package com.mustafayusef.holidaymaster.login

import com.mustafayusef.holidaymaster.Auth.auth
import com.mustafayusef.holidaymaster.Models.Buggage
import com.mustafayusef.holidaymaster.Models.group
import com.mustafayusef.holidaymaster.Models.profileAuth

interface lesener {

        fun OnStart()
        fun onFailer(message:String)
        fun onSucsess(Response: auth)
      fun  onSucsessProfile(response:profileAuth)
}