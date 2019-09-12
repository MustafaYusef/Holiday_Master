package com.mustafayusef.holidaymaster.Visa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary

class VisaViewModelFactory(
    val repostary: userRepostary
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VisaViewModel(repostary) as T
    }

}