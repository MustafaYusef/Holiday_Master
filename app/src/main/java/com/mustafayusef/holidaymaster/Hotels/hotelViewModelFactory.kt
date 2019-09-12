package com.mustafayusef.holidaymaster.Hotels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary

class hotelViewModelFactory(
    val repostary: userRepostary
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HotelsViewModel(repostary) as T
    }

}