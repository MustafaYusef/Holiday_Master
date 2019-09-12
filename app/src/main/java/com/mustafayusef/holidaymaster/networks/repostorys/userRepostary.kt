package com.mustafayusef.sharay.data.networks.repostorys

import com.mustafayusef.holidaymaster.Auth.auth
import com.mustafayusef.holidaymaster.Groups.BookGroup.GroupBook
import com.mustafayusef.holidaymaster.Models.*
import com.mustafayusef.holidaymaster.Tours.bookTours.TourBook
import com.mustafayusef.holidaymaster.Tours.bookTours.gg
import com.mustafayusef.holidaymaster.Tours.tok
import com.mustafayusef.holidaymaster.networks.*

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
}