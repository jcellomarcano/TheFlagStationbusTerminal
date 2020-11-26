package com.example.thefalgbusstop.data.network

import com.example.thefalgbusstop.data.services.*
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


abstract class BaseRequest<T : Any>(
    var baseUrl: String
) {
    private val okHttpClient: OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        val specs = listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)
        OkHttpClient.Builder()
            .addInterceptor(this)
            .connectionSpecs(specs)
            .build()
    }

    inline fun <reified T : Any> getService(): T =
            buildRetrofit().run {
                create(T::class.java)
            }

    fun buildRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

class ChoferRequest(baseUrl: String): BaseRequest<ChoferService>(baseUrl)
class BusRequest(baseUrl: String): BaseRequest<BusService>(baseUrl)
class PassengerRequest(baseUrl: String): BaseRequest<PassengerService>(baseUrl)
class RouteRequest(baseUrl: String): BaseRequest<RouteService>(baseUrl)
class HorarioRequest(baseUrl: String): BaseRequest<HorarioService>(baseUrl)
class SitRequest(baseUrl: String): BaseRequest<SitService>(baseUrl)