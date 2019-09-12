package com.mustafayusef.holidaymaster.tickets.FareRules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary

class FareViewModelFactory(
    val repostary: userRepostary
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FareViewModel(repostary) as T
    }

}