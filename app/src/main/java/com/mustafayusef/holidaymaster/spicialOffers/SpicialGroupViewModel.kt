package com.mustafayusef.holidaymaster.spicialOffers

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class SpicialGroupViewModel(val userRepo: userRepostary) : ViewModel() {
    var dataLesener: lesenerOffers?=null
    fun GetTours(){
        dataLesener?.OnStart()


        corurtins.main {
            try {
                val onewayResponse=userRepo.GetToursOffer()
                onewayResponse?.let {
                    dataLesener?.onSucsessTour(it)
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

    fun GetGroups(){
        dataLesener?.OnStart()


        corurtins.main {
            try {
                val onewayResponse=userRepo.GetGroupsOffer()
                onewayResponse?.let {
                    dataLesener?.onSucsessGroup(it)
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
