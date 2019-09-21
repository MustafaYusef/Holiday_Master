package com.mustafayusef.holidaymaster.networks

import com.mustafayusef.holidaymaster.Auth.auth
import com.mustafayusef.holidaymaster.Groups.BookGroup.GroupBook
import com.mustafayusef.holidaymaster.Models.*
import com.mustafayusef.holidaymaster.Tours.bookTours.TourBook
import com.mustafayusef.holidaymaster.Tours.bookTours.gg
import com.mustafayusef.holidaymaster.Tours.tok
import com.mustafayusef.sharay.data.networks.repostorys.info
import okhttp3.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.Headers
import java.util.*
import java.util.concurrent.TimeUnit


interface myApis {

    @FormUrlEncoded

    @POST("AirLines/oneway")
    suspend fun getOneWay(
        @Field("Infant") infant:Int, @Field("Child") child:Int,
        @Field("Adult") adult:Int, @Field("DepartureDate") departureDate:String,
        @Field("from") from:String, @Field("to") to:String,
    @Field("direct") direct:Int, @Field("cabin") cabin:Int
    ):Response<oneWay>

    @FormUrlEncoded

    @POST("AirLines/twoway")
    suspend fun getTowWay(
        @Field("Infant") infant:Int, @Field("Child") child:Int,
        @Field("Adult") adult:Int, @Field("DepartureDate") departureDate:String,
        @Field("returnDate") ReturnDate:String,
        @Field("from") from:String, @Field("to") to:String,
        @Field("direct") direct:Int, @Field("cabin") cabin:Int
    ):Response<TowWay>

    @FormUrlEncoded
    @POST("AirLines/Baggage")
    suspend fun getBuggage(
        @Field("SessionId") session:String,@Field("_id") Id:String,
        @Field("type") type:String
    ):Response<Buggage>

    @FormUrlEncoded
    @POST("AirLines/fareRules")
    suspend fun getFare(
        @Field("SessionId") session:String,@Field("_id") Id:String,
        @Field("type") type:String
    ):Response<FareRules>


    @GET("user/checklogin/")
    suspend fun getProfile(
        @Header("token") token:String
    ):Response<profileAuth>

    @GET("Group/all")
    suspend fun GetGroups(
    ):Response<List<group>>

    @FormUrlEncoded
    @POST("holet/all/v2")
    suspend fun GetHotels(
        @Field("in") checkIn:String,
        @Field("out")checkOut:String,
        @Field("adt") AdultNo:String,
        @Field("ch") ChildNo:String,
        @Field("city")CityHot:String,
        @Field("chAge1")chAge1:String,
        @Field("chAge2")chAge2:String,
        @Field("chAge3")chAge3:String,
        @Field("chAge4")chAge4:String,
        @Field("chAge5")chAge5:String

    ):Response<List<hotel>>

    @GET("Tours/all")
    suspend fun getTours(
    ):Response<List<Tours>>

    @POST("user/login")
    suspend fun GetLogin(
        @Body body:logBody
    ):Response<auth>

    @GET("visa")
    suspend fun GetVisa(
    ):Response<List<country>>

    @FormUrlEncoded
    @POST("visa")
    suspend fun GetSearchVisa(
        @Field("Nationality") Nationality:String
    ):Response<List<country>>

    @Headers("Content-Type: application/json")
    @POST("incomplete/V3add")
    suspend fun BookTour(
        @Body body: TourBook
    ):Response<tok>

    @Headers("Content-Type: application/json")
    @POST("incomplete/V3add")
    suspend fun BookGroup(
        @Body body: GroupBook
    ):Response<tok>


    @GET("incomplete")
    suspend fun getOrder(
        @Header("ORDER") token:String
    ):Response<TourOrder>

    @Headers("Content-Type: application/json")
    @POST("incomplete/ToursOrderV3")
    suspend fun BookTourFinal(
        @Header("token") token:String ,@Body aa: gg
    ):Response<msg>

    @Multipart
    @POST("incomplete/addVisaV3")
    suspend fun BookVisa(
        @Header("token") token:String,
        @Part("FirstName") FirstName:RequestBody,
    @Part("LastName")   LastName:RequestBody,
    @Part("PassportNo")  PassportNo:RequestBody,
    @Part("Nationality")  Nationality:RequestBody,
    @Part("DateofBirth")  DateofBirth:RequestBody,
    @Part("PassportIssueDate") PassportIssueDate:RequestBody,
    @Part("PassportExpiryDate") PassportExpiryDate:RequestBody,

    @Part("_id_Visa")  _id:RequestBody,
    @Part("name_Visa") name:RequestBody,
    @Part("country_Visa") country:RequestBody,
    @Part("price_Visa")  price:RequestBody,
    @Part("Description_Visa")  Description:RequestBody,
    @Part("offers_Visa")  offers:RequestBody ,
    @Part("APPROVED_Visa")  APPROVED:RequestBody ,
    @Part("Nationality_Visa") Nationality_Visa:RequestBody ,
    @Part("uptime_Visa") uptime:RequestBody,
    @Part("__v_Visa")   __v:RequestBody,
        @Part image1: MultipartBody.Part?,
        @Part image2: MultipartBody.Part?,
        @Part image3: MultipartBody.Part?,
        @Part image4: MultipartBody.Part?,
        @Part image5: MultipartBody.Part?

    ):Response<msg>
    @Multipart
    @POST("incomplete/n3al")
    suspend fun BookFinalGroup(
        @Header("token") token:String,
        ):Response<msg>


    companion object{

        operator fun invoke(
            networkIntercepter:networkIntercepter
        ):myApis{
            val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
                )
                .build()

            val client = OkHttpClient.Builder() .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS)
                .addInterceptor(networkIntercepter)
                .connectionSpecs(Collections.singletonList(spec))
//            client.tim(10, TimeUnit.SECONDS)
//            client.setReadTimeout(30, TimeUnit.SECONDS)
//            client.interceptors().add(object : Interceptor() {
//                @Throws(IOException::class)
//               override fun intercept(chain: Interceptor.Chain): Response<*> {
//                    return onOnIntercept(chain)
//                }
//            })

            return Retrofit.Builder()
                .client(client.build())
                .baseUrl("https://favorite-holiday.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())

                .build().create(myApis::class.java)
        }
    }
}

data class logBody(
    var email:String,
    var password:String
)

data class msg(
    var msg:String
)