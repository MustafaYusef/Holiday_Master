package com.mustafayusef.holidaymaster.tickets.BookOneWay

import androidx.lifecycle.ViewModel
import com.mustafayusef.holidaymaster.Models.ticketDetails.Baggage
import com.mustafayusef.holidaymaster.Models.ticketDetails.Data
import com.mustafayusef.holidaymaster.networks.msg
import com.mustafayusef.holidaymaster.utils.ApiExaptions
import com.mustafayusef.holidaymaster.utils.corurtins
import com.mustafayusef.holidaymaster.utils.noInternetExeption
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException

class ticketDetailsViewModel(val userRepo:userRepostary): ViewModel() {

    var dataLesener: lesenerTicketOne? = null
    fun GetDetails(
        token: String, type: String,
        Adult: String,
        Child: String,
        Infant: String,
        _id: String,
        SessionId: String
    ) {
        dataLesener?.OnStart()


        corurtins.main {
            try {
                val onewayResponse = userRepo.GetTicketD(
                    token, type,
                    Adult,
                    Child,
                    Infant,
                    _id,
                    SessionId
                )
                onewayResponse?.let {
                    dataLesener?.onSucsess(it)
                }

            } catch (e: ApiExaptions) {
                e.message?.let { dataLesener?.onFailer(it) }

            } catch (e: noInternetExeption) {
                e.message?.let { dataLesener?.onFailer(it) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { dataLesener?.onFailer(it) }
            } catch (e: SocketException) {
                e.message?.let { dataLesener?.onFailer(it) }
            } catch (e: ProtocolException) {
                e.message?.let { dataLesener?.onFailer(it) }
            }

        }

    }

     fun bookFinalTicket(
        token: String, InfAsso: List<Int>,
        adtTitle: List<String>,adtFirstName: List<String>, adtLastName: List<String>, adtDateOfBirth: List<String>,
        adtPassportIssueDate: List<String>,
        adtAge: List<Int>,
        adtPassportNo: List<String>,
        AdtIssuingCountry: List<String>,
        adtExpiryDate: List<String>,

        chldTitle: List<String>,
        chldFirstName: List<String>,
        chldLastName: List<String>,
        chldDateOfBirth: List<String>,
        chldPassportIssueDate: List<String>,
        chldAge: List<Int>,
        chldPassportNo: List<String>,
        chldIssuingCountry: List<String>,
        chldExpiryDate: List<String>,

        inftTitle: List<String>, inftFirstName: List<String>, inftLastName: List<String>,
        inftDateOfBirth: List<String>, inftPassportIssueDate: List<String>, inftAge: List<Int>,
        inftPassportNo: List<String>, inftIssuingCountry: List<String>,
        inftExpiryDate: List<String>, AdulNumbert: Int,
        chldNumbert: Int,
        inftNumber: Int,
        email: String, phone: String, SessionId: String, _id: String, price: String,
        type: String, data1:List<Data> , Baggage1:List<Baggage>
    ) {
        dataLesener?.OnStart()


        corurtins.main {
            try {
                val onewayResponse = userRepo.bookFinalTicket(
                    token, InfAsso,
                    adtTitle,adtFirstName, adtLastName, adtDateOfBirth,
                    adtPassportIssueDate,
                    adtAge,
                    adtPassportNo,
                    AdtIssuingCountry,
                    adtExpiryDate,

                    chldTitle,
                    chldFirstName,
                    chldLastName,
                    chldDateOfBirth,
                    chldPassportIssueDate,
                    chldAge,
                    chldPassportNo,
                    chldIssuingCountry,
                    chldExpiryDate,

                    inftTitle, inftFirstName, inftLastName,
                    inftDateOfBirth, inftPassportIssueDate, inftAge,
                    inftPassportNo, inftIssuingCountry,
                    inftExpiryDate, AdulNumbert,
                    chldNumbert,
                    inftNumber,
                    email, phone, SessionId, _id, price,

                    type, data1, Baggage1
                )
                onewayResponse?.let {
                    dataLesener?.onSucsessBook(it)
                }

            } catch (e: ApiExaptions) {
                e.message?.let { dataLesener?.onFailer(it) }

            } catch (e: noInternetExeption) {
                e.message?.let { dataLesener?.onFailer(it) }
            } catch (e: SocketTimeoutException) {
                e.message?.let { dataLesener?.onFailer(it) }
            } catch (e: SocketException) {
                e.message?.let { dataLesener?.onFailer(it) }
            } catch (e: ProtocolException) {
                e.message?.let { dataLesener?.onFailer(it) }
            }

        }

    }

}