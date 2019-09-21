package com.mustafayusef.holidaymaster.Visa

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.Models.country
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import okhttp3.MultipartBody
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class VisaViewModel(val userRepo:userRepostary): ViewModel() {

    var dataLesener: lesener?=null
    fun GetVisa(){
        dataLesener?.OnStart()


            corurtins.main {
                try {
                    val onewayResponse=userRepo.GetVisa()
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

    fun GetSearchVisa(country:String){
        dataLesener?.OnStart()
        corurtins.main {
            try {
                val onewayResponse=userRepo.GetSearchVisa(country)
                onewayResponse?.let {
                    dataLesener?.onSucsessSearch(it!!)
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

    fun BookVis(token:String, FirstName:String,
                LastName:String,
                PassportNo:String,
                Nationality:String,
                DateofBirth:String,
                PassportIssueDate:String,
                PassportExpiryDate:String, visa: country,
                imge0: MultipartBody.Part?
                , imge1: MultipartBody.Part?
                , imge2: MultipartBody.Part?
                , imge3: MultipartBody.Part?, imge4: MultipartBody.Part?){

        dataLesener?.OnStart()
        corurtins.main {
            try {
                val onewayResponse=userRepo.BookVisa(token, FirstName,
                    LastName,
                    PassportNo,
                    Nationality,
                    DateofBirth,
                    PassportIssueDate,
                    PassportExpiryDate,visa,imge0
                ,imge1
                ,imge2
                ,imge3,imge4)
                onewayResponse?.let {
                    dataLesener?.onSucsessBook(it!!)
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