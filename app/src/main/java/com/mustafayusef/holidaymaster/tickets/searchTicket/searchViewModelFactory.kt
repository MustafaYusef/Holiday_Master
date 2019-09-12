package com.mustafayusef.holidaymaster.tickets.searchTicket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary

class searchViewModelFactory(
    val repostary: userRepostary
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return searchViewModel(repostary) as T
    }

}