package com.mustafayusef.holidaymaster.Tours

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.Models.TourOrder
import com.mustafayusef.holidaymaster.Tours.bookTours.TourBook
import com.mustafayusef.holidaymaster.Tours.bookTours.gg
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class ToursViewModel(val userRepo:userRepostary): ViewModel() {

    var dataLesener: lesener?=null
    fun GetTours(){
        dataLesener?.OnStart()


            corurtins.main {
                try {
                    val onewayResponse=userRepo.GetTours()
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

    fun BookTours(book: TourBook) {
        dataLesener?.OnStart()


        corurtins.main {
            try {
                val onewayResponse=userRepo.BookTours(book)
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

    fun getOrder(book: String) {
        dataLesener?.OnStart()
        corurtins.main {
            try {
                val onewayResponse=userRepo.getOrder(book)
                onewayResponse?.let {
                    dataLesener?.onSucsessGetOrder(it!!)
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

    fun SubmetBookTours(token: String,item: gg) {
        dataLesener?.OnStart()
        corurtins.main {
            try {
                val onewayResponse=userRepo.BookTourFinal(token,item)
                onewayResponse?.let {
                    dataLesener?.onSucsessBookTour(it!!)
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