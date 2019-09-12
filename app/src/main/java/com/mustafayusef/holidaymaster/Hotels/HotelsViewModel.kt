package com.mustafayusef.holidaymaster.Hotels

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.tickets.leseners.getDataLesener
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class HotelsViewModel(val userRepo:userRepostary): ViewModel() {

    var dataLesener: lesener?=null
    fun GetHotels(checkIn:String,
                  checkOut:String,
                  AdultNo:String,
                  ChildNo:String,
                  CityHot:String,
                  chAge1:String,
                  chAge2:String,
                  chAge3:String,
                  chAge4:String,
                  chAge5:String){
        dataLesener?.OnStart()


            corurtins.main {
                try {
                    val onewayResponse=userRepo.GetHotels(checkIn,checkOut,AdultNo,ChildNo,CityHot,chAge1,chAge2,chAge3,chAge4,chAge5)
                    onewayResponse?.let {
                        dataLesener?.onSucsess(it!!)
                    }

                }catch (e: ApiExaptions){
                    e.message?.let { dataLesener?.onFailer(it) }

                }catch (e: noInternetExeption){
                    e.message?.let { dataLesener?.onFailer(it) }
                }catch (e: SocketTimeoutException){
                    e.message?.let { dataLesener?.onFailer(it) }}
                catch (e: SocketException){
                    e.message?.let { dataLesener?.onFailer(it) }
                }catch (e: ProtocolException){
                    e.message?.let { dataLesener?.onFailer(it) }
                }


            }

        }



}