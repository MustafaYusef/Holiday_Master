package com.mustafayusef.holidaymaster.login.profile.dashBoard

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.login.lesener
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class OrderTicketsViewModel(val userRepo: userRepostary) : ViewModel() {
    var dataLesener: Dashlesener?=null
    fun GetOrderTicket(token:String){
        dataLesener?.OnStart()
        corurtins.main {
            try {
                val onewayResponse=userRepo.GetTicketOrder(token)
                onewayResponse?.let {
                    dataLesener?.onSucsessTicket(it!!)
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

    fun GetOtherOrder(token:String){
        dataLesener?.OnStart()
        corurtins.main {
            try {
                val onewayResponse=userRepo.GetotherOrder(token)
                onewayResponse?.let {
                    dataLesener?.onSucsessOther(it!!)
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
