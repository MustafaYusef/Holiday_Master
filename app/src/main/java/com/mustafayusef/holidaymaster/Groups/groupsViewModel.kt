package com.mustafayusef.holidaymaster.Groups

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.Groups.BookGroup.GroupBook
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class groupsViewModel(val userRepo:userRepostary): ViewModel() {

    var dataLesener: lesener?=null
    fun GetGroups(){
        dataLesener?.OnStart()


            corurtins.main {
                try {
                    val onewayResponse=userRepo.GetGroups()
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
    fun BookGroup(book: GroupBook) {
        dataLesener?.OnStart()


        corurtins.main {
            try {
                val onewayResponse=userRepo.BookGroup(book)
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

    fun getOrderGroup(book: String) {
        dataLesener?.OnStart()
        corurtins.main {
            try {
                val onewayResponse=userRepo.getOrder(book)
                onewayResponse?.let {
                    dataLesener?.onSucsessGetOrderGroup(it!!)
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