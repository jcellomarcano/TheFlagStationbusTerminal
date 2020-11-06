package com.example.thefalgbusstop.data.services

import com.example.thefalgbusstop.data.network.ApiConstants.ENDPOINT_ALL
import com.example.thefalgbusstop.data.network.ChoferResponseServer
import com.example.thefalgbusstop.data.network.ApiConstants.ENDPOINT_CHOFER
import com.example.thefalgbusstop.data.network.ChoferServer
import io.reactivex.Single
import retrofit2.http.*


interface ChoferService {

    @GET(ENDPOINT_CHOFER + ENDPOINT_ALL)
    fun getAllChofers(
            @Header("Authorization") API_KEY: String,
    ): Single<List<ChoferServer>>

    @GET(ENDPOINT_CHOFER)
    fun getChofer(
            @Header("Authorization") API_KEY: String,
            @Path("id") id: Int
    ): Single<ChoferServer>

    @POST(ENDPOINT_CHOFER)
    @FormUrlEncoded
    fun insertChofer(
            @Header("Authorization") API_KEY: String,
            @Field("nombre") nombre: String?,
            @Field("apellido") apellido: String?,
            @Field("rut") rut: String): Single<ChoferServer>

    @PUT(ENDPOINT_CHOFER)
    fun updateChofer(
            @Header("Authorization") API_KEY: String,
            @Path("id") id: Int,
            @Field("nombre") nombre: String?,
            @Field("apellido") apellido: String?,
            @Field("rut") rut: String
    ): Single<ChoferServer>

    @DELETE(ENDPOINT_CHOFER)
    fun deleteChofer(
            @Header("Authorization") API_KEY: String,
            @Path("id") id: Int,
            @Field("nombre") nombre: String?,
            @Field("apellido") apellido: String?,
            @Field("rut") rut: String
    ): Single<ChoferServer>

}

