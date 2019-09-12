package com.mustafayusef.holidaymaster.tickets.searchTicket

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.tickets.leseners.getDataLesener
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class searchViewModel(val userRepo:userRepostary): ViewModel() {
//    var flage:Boolean=true
//    var adult = 1
//    var child = 0
//    var infant = 0
//    var type = 1
//    var departure = ""
//    var Return = ""
//    var fromSelect = ""
//    var toSelect = ""
//    var direct:Int=1
    var dataLesener: getDataLesener?=null
    fun getdata(infant:Int,child:Int,
                adult:Int,departureDate:String,
                returnDate:String,
                from:String, to:String,
                direct:Int,cabin:Int,flag:Boolean){
        dataLesener?.OnStart()
        if(flag){

            corurtins.main {
                try {
                    val onewayResponse=userRepo.getdataOneWay(infant,child,
                        adult,departureDate,
                        from, to,
                        direct,cabin)
                    onewayResponse.result?.let {
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
        else{
            corurtins.main {
                try {
                    val TowwayResponse=userRepo.getdataTowWay(infant,child,
                        adult,departureDate,
                        returnDate,
                        from, to,
                        direct,cabin)
                    TowwayResponse.result?.let {
                        dataLesener?.onSucsessTow(it!!)
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

}