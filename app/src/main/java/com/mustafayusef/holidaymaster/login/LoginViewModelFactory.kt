package com.mustafayusef.holidaymaster.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary

class LoginViewModelFactory(
    val repostary: userRepostary
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repostary) as T
    }

}