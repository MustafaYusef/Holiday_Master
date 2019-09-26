package com.mustafayusef.holidaymaster.Groups

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.Groups.BookGroup.GroupBook
import com.mustafayusef.holidaymaster.Models.TourOrder
import com.mustafayusef.holidaymaster.networks.msg
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
     fun BookFinalGroup(token:String,
                               group: TourOrder,
                               adultFirstNameArr:List<RequestBody>,
                               adultLastNameArr:List<RequestBody>,
                               adultPassportNoArr:List<RequestBody>,
                               adultNationalityArr:List<RequestBody>,
                               adultBirthArr:List<RequestBody>,
                               adultIssueArr:List<RequestBody>,
                               adultExpiryArr:List<RequestBody>,
                               childFirstNameArr:List<RequestBody>,
                               childLastNameArr:List<RequestBody>,
                               childPassportNoArr:List<RequestBody>,
                               childNationalityArr:List<RequestBody>,
                               childBirthArr:List<RequestBody>,
                               childIssueArr:List<RequestBody>,
                               childExpiryArr:List<RequestBody>,

                               infantFirstNameArr:List<RequestBody>,
                               infantLastNameArr:List<RequestBody>,
                               infantPassportNoArr:List<RequestBody>,
                               infantNationalityArr:List<RequestBody>,
                               infantBirthArr:List<RequestBody>,
                               infantIssueArr:List<RequestBody>,
                               infantExpiryArr:List<RequestBody>,
                               images:List<MultipartBody.Part>) {
            corurtins.main {
                try {
                    val onewayResponse=userRepo.BookFinalGroup(token,
                        group,
                        adultFirstNameArr,
                        adultLastNameArr,
                        adultPassportNoArr,
                        adultNationalityArr,
                        adultBirthArr,
                        adultIssueArr,
                        adultExpiryArr,
                        childFirstNameArr,
                        childLastNameArr,
                        childPassportNoArr,
                        childNationalityArr,
                        childBirthArr,
                        childIssueArr,
                        childExpiryArr,
                        infantFirstNameArr,
                        infantLastNameArr,
                        infantPassportNoArr,
                        infantNationalityArr,
                        infantBirthArr,
                        infantIssueArr,
                        infantExpiryArr,
                        images)
                    onewayResponse?.let {
                        dataLesener?.onSucsessFinalBookGroup(it!!)
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
