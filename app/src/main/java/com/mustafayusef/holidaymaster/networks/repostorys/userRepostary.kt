package com.mustafayusef.sharay.data.networks.repostorys

import com.google.gson.Gson
import com.mustafayusef.holidaymaster.Auth.auth
import com.mustafayusef.holidaymaster.Groups.BookGroup.GroupBook
import com.mustafayusef.holidaymaster.Models.*
import com.mustafayusef.holidaymaster.Models.TicketOrder.TicketOrderRes
import com.mustafayusef.holidaymaster.Models.bookResTecket.BookTicketResponse
import com.mustafayusef.holidaymaster.Models.otherOrd.otherOrderRes
import com.mustafayusef.holidaymaster.Models.ticketDetails.Baggage
import com.mustafayusef.holidaymaster.Models.ticketDetails.Data
import com.mustafayusef.holidaymaster.Models.ticketDetails.ticketDetails
import com.mustafayusef.holidaymaster.Tours.bookTours.TourBook
import com.mustafayusef.holidaymaster.Tours.bookTours.gg
import com.mustafayusef.holidaymaster.Tours.tok
import com.mustafayusef.holidaymaster.networks.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

class userRepostary(val api:myApis):SafeApiRequest() {
    suspend fun getdataOneWay(
        infant: Int, child: Int,
        adult: Int, departureDate: String,
        from: String, to: String,
        direct: Int, cabin: Int
    ): oneWay {

        return SafeRequest {
            api.getOneWay(
                infant, child,
                adult, departureDate,
                from, to,
                direct, cabin
            )
        }
    }


    suspend fun getdataBuggage(session: String, Id: String, type: String): Buggage {
        return SafeRequest {
            api.getBuggage(session, Id, type)
        }
    }

    suspend fun getFare(session: String, Id: String, type: String): FareRules {
        return SafeRequest {
            api.getFare(session, Id, type)
        }
    }


    suspend fun getdataTowWay(
        infant: Int, child: Int,
        adult: Int, departureDate: String,
        returnDate: String,
        from: String, to: String,
        direct: Int, cabin: Int
    ): TowWay {
        return SafeRequest {
            api.getTowWay(
                infant, child,
                adult, departureDate,
                returnDate,
                from, to,
                direct, cabin
            )
        }
    }

    suspend fun GetGroups(): List<group> {
        return SafeRequest {
            api.GetGroups()
        }
    }

    suspend fun GetVisa(): List<country> {
        return SafeRequest {
            api.GetVisa()
        }
    }

    suspend fun GetSearchVisa(country: String): List<country> {
        return SafeRequest {
            api.GetSearchVisa(country)
        }
    }


    suspend fun GetLogin(email: String, password: String): auth {
        var b = logBody(email = email, password = password)
        return SafeRequest {
            api.GetLogin(b)
        }
    }

    suspend fun GetTours(): List<Tours> {
        return SafeRequest {
            api.getTours()
        }
    }

    suspend fun GetHotels(
        checkIn: String,
        checkOut: String,
        AdultNo: String,
        ChildNo: String,
        CityHot: String,
        chAge1: String,
        chAge2: String,
        chAge3: String,
        chAge4: String,
        chAge5: String
    ): List<hotel> {
        return SafeRequest {
            api.GetHotels(
                checkIn,
                checkOut,
                AdultNo,
                ChildNo,
                CityHot,
                chAge1,
                chAge2,
                chAge3,
                chAge4,
                chAge5
            )
        }
    }

    suspend fun BookTours(book: TourBook): tok {
        return SafeRequest {
            api.BookTour(book)
        }
    }

    suspend fun BookGroup(book: GroupBook): tok {
        return SafeRequest {
            api.BookGroup(book)
        }
    }

    suspend fun getOrder(token: String): TourOrder {
        return SafeRequest {
            api.getOrder(token)
        }
    }

    suspend fun BookTourFinal(token: String, gg: gg): msg {
        return SafeRequest {
            api.BookTourFinal(token, gg)
        }
    }

    suspend fun Profile(token: String): profileAuth {
        return SafeRequest {
            api.getProfile(token)
        }
    }

    suspend fun BookVisa(
        token: String, FirstName: String,
        LastName: String,
        PassportNo: String,
        Nationality: String,
        DateofBirth: String,
        PassportIssueDate: String,
        PassportExpiryDate: String, visa: country
        , imge0: MultipartBody.Part?
        , imge1: MultipartBody.Part?
        , imge2: MultipartBody.Part?
        , imge3: MultipartBody.Part?, imge4: MultipartBody.Part?
    ): msg {
        val filter = HashMap<Int, MultipartBody.Part>()


        imge0?.let { filter.put(0, it) }
        imge1?.let { filter.put(1, it) }
        imge2?.let { filter.put(2, it) }
        imge3?.let { filter.put(3, it) }
        imge4?.let { filter.put(4, it) }


        var inf = info(
            FirstName = RequestBody.create(MediaType.parse("text/plain"), FirstName)
            , LastName = RequestBody.create(MediaType.parse("text/plain"), LastName),
            PassportNo = RequestBody.create(MediaType.parse("text/plain"), PassportNo),
            Nationality = RequestBody.create(MediaType.parse("text/plain"), Nationality)
            , DateofBirth = RequestBody.create(MediaType.parse("text/plain"), DateofBirth),
            PassportIssueDate = RequestBody.create(
                MediaType.parse("text/plain"),
                PassportIssueDate
            ),
            PassportExpiryDate = RequestBody.create(
                MediaType.parse("text/plain"),
                PassportExpiryDate
            )
        )


//        var info1=RequestBody.create(MediaType.parse("multipart/form-data"),inf)
        return SafeRequest {
            api.BookVisa(
                token, inf.FirstName, inf.LastName, inf.PassportNo, inf.Nationality,
                inf.DateofBirth, inf.PassportIssueDate, inf.PassportExpiryDate,
                RequestBody.create(MediaType.parse("text/plain"), visa._id),
                RequestBody.create(MediaType.parse("text/plain"), visa.name),
                RequestBody.create(MediaType.parse("text/plain"), visa.country),
                RequestBody.create(MediaType.parse("text/plain"), visa.price.toString()),
                RequestBody.create(MediaType.parse("text/plain"), visa.Description),
                RequestBody.create(MediaType.parse("text/plain"), visa.offers.toString()),
                RequestBody.create(MediaType.parse("text/plain"), visa.APPROVED),
                RequestBody.create(MediaType.parse("text/plain"), visa.Nationality),
                RequestBody.create(MediaType.parse("text/plain"), visa.uptime),
                RequestBody.create(MediaType.parse("text/plain"), visa.__v.toString())
                , filter[0], filter[1], filter[2], filter[3], filter[4]
            )
        }
    }

    suspend fun BookFinalGroup(
        token: String,
        group: TourOrder,
        adultFirstNameArr: List<RequestBody>,
        adultLastNameArr: List<RequestBody>,
        adultPassportNoArr: List<RequestBody>,
        adultNationalityArr: List<RequestBody>,
        adultBirthArr: List<RequestBody>,
        adultIssueArr: List<RequestBody>,
        adultExpiryArr: List<RequestBody>,
        childFirstNameArr: List<RequestBody>,
        childLastNameArr: List<RequestBody>, childPassportNoArr: List<RequestBody>,
        childNationalityArr: List<RequestBody>,
        childBirthArr: List<RequestBody>,
        childIssueArr: List<RequestBody>,
        childExpiryArr: List<RequestBody>,

        infantFirstNameArr: List<RequestBody>,
        infantLastNameArr: List<RequestBody>,
        infantPassportNoArr: List<RequestBody>,
        infantNationalityArr: List<RequestBody>,
        infantBirthArr: List<RequestBody>,
        infantIssueArr: List<RequestBody>,
        infantExpiryArr: List<RequestBody>,
        images: List<MultipartBody.Part>
    ): msg {

        return SafeRequest {
            api.BookFinalGroup(
                token,
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
                images
            )
        }
    }

    suspend fun GetTicketD(token:String,
        type: String,
        Adult: String,
        Child: String,
        Infant: String,
        _id: String,
        SessionId: String
    ) : ticketDetails {
        return SafeRequest {
            api.getDitailsTicket(token,
                type,
                Adult,
                Child,
                Infant,
                _id,
                SessionId
            )
        }

    }

    suspend fun bookFinalTicket(token:String, InfAsso:List<Int>,
    adtTitle:List<String>,adtFirstName: List<String>, adtLastName:List<String>, adtDateOfBirth:List<String>,
     adtPassportIssueDate:List<String>,
     adtAge:List<Int>,
        adtPassportNo:List<String>,
       AdtIssuingCountry:List<String>,
        adtExpiryDate:List<String>,

      chldTitle:List<String>,
      chldFirstName:List<String>,
      chldLastName:List<String>,
       chldDateOfBirth:List<String>,
       chldPassportIssueDate:List<String>,
        chldAge:List<Int>,
        chldPassportNo:List<String>,
       chldIssuingCountry:List<String>,
     chldExpiryDate:List<String>,

   inftTitle:List<String>, inftFirstName:List<String>, inftLastName:List<String>,
     inftDateOfBirth:List<String>, inftPassportIssueDate:List<String>, inftAge:List<Int>,
       inftPassportNo:List<String>, inftIssuingCountry:List<String>,
       inftExpiryDate:List<String>, AdulNumbert:Int,
        chldNumbert:Int,
        inftNumber:Int,
    email:String, phone:String,SessionId:String, _id:String, price:String,

       type:String, data1:List<Data>, Baggage1:List<Baggage> ): BookTicketResponse {
        var gson = Gson()
        var data1 = gson.toJson(data1[0])
        var Baggage1=gson.toJson(Baggage1)
        return SafeRequest {
            api.bookFinalTicket(token, InfAsso,
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
            email, phone,SessionId, _id, price,

            type, data1,Baggage1
            )
        }
    }

    suspend fun GetGroupsOffer(): List<group> {
        return SafeRequest {
            api.GetSpicialGroups()
        }
    }
    suspend fun GetToursOffer(): List<Tours> {
        return SafeRequest {
            api.getSpicialTours()
        }
    }

    suspend fun GetTicketOrder(token:String): TicketOrderRes {
        return SafeRequest {
            api.GetTicketOrder(token )
        }
    }
    suspend fun GetotherOrder(token:String): otherOrderRes{
        return SafeRequest {
            api.GetotherOrder(token)
        }
    }
}
data class  info(
    var FirstName:RequestBody,
    var LastName:RequestBody,
    var PassportNo:RequestBody,
    var Nationality:RequestBody,
    var DateofBirth:RequestBody,
    var PassportIssueDate:RequestBody,
    var PassportExpiryDate:RequestBody
)