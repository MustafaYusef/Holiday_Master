package com.mustafayusef.sharay.data.networks.repostorys

import com.mustafayusef.holidaymaster.Auth.auth
import com.mustafayusef.holidaymaster.Groups.BookGroup.GroupBook
import com.mustafayusef.holidaymaster.Models.*
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

    suspend fun GetHotels( checkIn:String,
                          checkOut:String,
                           AdultNo:String,
                          ChildNo:String,
                          CityHot:String,
                          chAge1:String,
                          chAge2:String,
                          chAge3:String,
                         chAge4:String,
                          chAge5:String): List<hotel> {
        return SafeRequest {
            api.GetHotels(checkIn,checkOut,AdultNo,ChildNo,CityHot,chAge1,chAge2,chAge3,chAge4,chAge5)
        }
    }
    suspend fun BookTours(book: TourBook):tok{
        return SafeRequest {
            api.BookTour(book)
        }
    }
    suspend fun BookGroup(book: GroupBook):tok{
        return SafeRequest {
            api.BookGroup(book)
        }
    }

    suspend fun getOrder(token: String):TourOrder{
        return SafeRequest {
            api.getOrder(token)
        }
    }

    suspend fun BookTourFinal(token: String,gg: gg): msg {
        return SafeRequest {
            api.BookTourFinal(token,gg)
        }
    }
   suspend fun Profile(token:String):profileAuth{
       return SafeRequest {
           api.getProfile(token)
       }
   }
    suspend fun BookVisa(token:String, FirstName:String,
                          LastName:String,
                          PassportNo:String,
                          Nationality:String,
                          DateofBirth:String,
                          PassportIssueDate:String,
                          PassportExpiryDate:String,visa:country
                         ,imge0: MultipartBody.Part?
                         , imge1: MultipartBody.Part?
                         , imge2: MultipartBody.Part?
                         , imge3: MultipartBody.Part?, imge4: MultipartBody.Part?):msg{
        val filter=HashMap<Int,MultipartBody.Part>()


            imge0?.let { filter.put(0, it) }
            imge1?.let { filter.put(1, it) }
            imge2?.let { filter.put(2, it) }
            imge3?.let { filter.put(3, it) }
            imge4?.let { filter.put(4, it) }



        var inf=info(FirstName=RequestBody.create(MediaType.parse("text/plain"),FirstName)
            ,LastName=RequestBody.create(MediaType.parse("text/plain"),LastName),
            PassportNo=RequestBody.create(MediaType.parse("text/plain"),PassportNo),
            Nationality=RequestBody.create(MediaType.parse("text/plain"),Nationality)
            , DateofBirth=RequestBody.create(MediaType.parse("text/plain"),DateofBirth),
            PassportIssueDate=RequestBody.create(MediaType.parse("text/plain"),PassportIssueDate),
            PassportExpiryDate=RequestBody.create(MediaType.parse("text/plain"),PassportExpiryDate))


//        var info1=RequestBody.create(MediaType.parse("multipart/form-data"),inf)
        return SafeRequest {
            api.BookVisa(token,inf.FirstName,inf.LastName,inf.PassportNo,inf.Nationality,
                inf.DateofBirth,inf.PassportIssueDate,inf.PassportExpiryDate,
                RequestBody.create(MediaType.parse("text/plain"),visa._id),
                RequestBody.create(MediaType.parse("text/plain"), visa.name),
                RequestBody.create(MediaType.parse("text/plain"), visa.country),
                RequestBody.create(MediaType.parse("text/plain"), visa.price.toString()),
                RequestBody.create(MediaType.parse("text/plain"), visa.Description),
                RequestBody.create(MediaType.parse("text/plain"), visa.offers.toString()) ,
                RequestBody.create(MediaType.parse("text/plain"), visa.APPROVED) ,
                RequestBody.create(MediaType.parse("text/plain"), visa.Nationality) ,
                RequestBody.create(MediaType.parse("text/plain"), visa.uptime),
                RequestBody.create(MediaType.parse("text/plain"), visa.__v.toString())
                ,filter[0],filter[1],filter[2],filter[3],filter[4])
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