package com.mustafayusef.holidaymaster.tickets.Buggage

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.tickets.leseners.buggageLesener
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class buggageViewModel(val repostary: userRepostary) :ViewModel() {
  var dataLesener: buggageLesener?=null

    fun getBuggage(session:String,Id:String,type:String){
        dataLesener?.OnStart()
            corurtins.main {
                try {
                    val BuugageResponse= repostary.getdataBuggage(session,Id,type)
                    BuugageResponse?.let {
                        dataLesener?.onSucsessBuggage(it!!)
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