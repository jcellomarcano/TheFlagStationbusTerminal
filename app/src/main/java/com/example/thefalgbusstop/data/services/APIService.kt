package com.example.thefalgbusstop.data.services

import com.example.thefalgbusstop.data.network.*
import com.example.thefalgbusstop.data.network.ApiConstants.ENDPOINT_ALL
import com.example.thefalgbusstop.data.network.ApiConstants.ENDPOINT_BUS
import com.example.thefalgbusstop.data.network.ApiConstants.ENDPOINT_CHOFER
import com.example.thefalgbusstop.data.network.ApiConstants.ENDPOINT_PASSENGER
import com.example.thefalgbusstop.data.network.ApiConstants.ENDPOINT_SIT
import com.example.thefalgbusstop.data.network.ApiConstants.ENDPOINT_TIME
import com.example.thefalgbusstop.domain.entities.ChoferPost
import com.example.thefalgbusstop.domain.entities.responsePojo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*


interface ChoferService {

    @GET(ENDPOINT_CHOFER + ENDPOINT_ALL)
    fun getAllChofers(
        @Header("Authorization") API_KEY: String,
    ): Single<List<ChoferServer>>

    @GET(ENDPOINT_CHOFER)
    fun getChoferRepo(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
    ): Single<ChoferServer>

//    @FormUrlEncoded
    @POST(ENDPOINT_CHOFER)
    @Headers( "Content-Type: application/json;charset=UTF-8")
    fun createChofer(
    @Header("Authorization") API_KEY: String,
    @Body chofer: ChoferPost,
    ): Single<responsePojoServer>

    @PUT("$ENDPOINT_CHOFER/{id}")
    fun updateChofer(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<ChoferServer>

    @DELETE("$ENDPOINT_CHOFER/{id}")
    fun deleteChofer(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
    ): Single<String>

    @DELETE("$ENDPOINT_CHOFER/{id}")
    fun deleteChoferAlt(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
    ): Call<responsePojo>

}

interface BusService {

    @GET(ENDPOINT_BUS + ENDPOINT_ALL)
    fun getAllBus(
        @Header("Authorization") API_KEY: String,
    ): Single<List<BusServer>>

    @GET(ENDPOINT_BUS)
    fun getBusRepo(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
    ): Single<BusServer>

    @POST(ENDPOINT_BUS)
    @FormUrlEncoded
    fun insertBus(
        @Header("Authorization") API_KEY: String,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<BusServer>

    @PUT(ENDPOINT_BUS)
    fun updateBus(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<BusServer>

    @DELETE(ENDPOINT_BUS)
    fun deleteBus(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<BusServer>

}

interface PassengerService {

    @GET(ENDPOINT_PASSENGER + ENDPOINT_ALL)
    fun getAllPassenger(
        @Header("Authorization") API_KEY: String,
    ): Single<List<PassengerServer>>

    @GET(ENDPOINT_PASSENGER)
    fun getPassengerRepo(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
    ): Single<PassengerServer>

    @POST(ENDPOINT_PASSENGER)
    @FormUrlEncoded
    fun insertPassenger(
        @Header("Authorization") API_KEY: String,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<PassengerServer>

    @PUT(ENDPOINT_PASSENGER)
    fun updatePassenger(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<PassengerServer>

    @DELETE(ENDPOINT_PASSENGER)
    fun deletePassenger(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<PassengerServer>

}

interface HorarioService {

    @GET(ENDPOINT_TIME + ENDPOINT_ALL)
    fun getAllHorario(
        @Header("Authorization") API_KEY: String,
    ): Single<List<HorarioServer>>

    @GET(ENDPOINT_TIME)
    fun getHorarioRepo(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
    ): Single<HorarioServer>

    @POST(ENDPOINT_TIME)
    @FormUrlEncoded
    fun insertHorario(
        @Header("Authorization") API_KEY: String,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<HorarioServer>

    @PUT(ENDPOINT_TIME)
    fun updateHorario(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<HorarioServer>

    @DELETE(ENDPOINT_TIME)
    fun deleteHorario(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<HorarioServer>

}

interface RouteService {

    @GET(ENDPOINT_TIME + ENDPOINT_ALL)
    fun getAllRoutes(
        @Header("Authorization") API_KEY: String,
    ): Single<List<RouteServer>>

    @GET(ENDPOINT_TIME)
    fun getRouteRepo(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
    ): Single<RouteServer>

    @POST(ENDPOINT_TIME)
    @FormUrlEncoded
    fun insertRoute(
        @Header("Authorization") API_KEY: String,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<RouteServer>

    @PUT(ENDPOINT_TIME)
    fun updateRoute(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<RouteService>

    @DELETE(ENDPOINT_TIME)
    fun deleteRoute(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<RouteService>

}

interface SitService {

    @GET(ENDPOINT_SIT + ENDPOINT_ALL)
    fun getAllSit(
        @Header("Authorization") API_KEY: String,
    ): Single<List<SitServer>>

    @GET(ENDPOINT_SIT)
    fun getSitRepo(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
    ): Single<SitServer>

    @POST(ENDPOINT_SIT)
    @FormUrlEncoded
    fun insertSit(
        @Header("Authorization") API_KEY: String,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<SitServer>

    @PUT(ENDPOINT_SIT)
    fun updateSit(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<SitServer>

    @DELETE(ENDPOINT_SIT)
    fun deleteSit(
        @Header("Authorization") API_KEY: String,
        @Path("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido") apellido: String,
        @Field("rut") rut: String,
    ): Single<SitServer>

}

