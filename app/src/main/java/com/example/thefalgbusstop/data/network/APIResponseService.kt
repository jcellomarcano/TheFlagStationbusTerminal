package com.example.thefalgbusstop.data.network

import android.os.Parcelable
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_CHOFER_ID
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_END
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_FECHA
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_HORA
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_ID
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_ID_BUS
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_ID_PASAJERO
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_ID_ROUTE
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_LASTNAME
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_MARCA
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_NAME
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_NUM_ASIENTO
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_PATENTE
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_RESULTS
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_RUT
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_START
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_TERMINAL
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChoferServer(
        @SerializedName(KEY_ID) val id: Int,
        @SerializedName(KEY_NAME) val name: String,
        @SerializedName(KEY_LASTNAME) val lastname: String,
        @SerializedName(KEY_RUT) val rut: String,
): Parcelable

@Parcelize
data class BusServer(
        @SerializedName(KEY_ID) val id: Int,
        @SerializedName(KEY_PATENTE) val patente: String,
        @SerializedName(KEY_MARCA) val marca: String,
        @SerializedName(KEY_CHOFER_ID) val choferId: Int,

): Parcelable

@Parcelize
data class HorarioServer(
        @SerializedName(KEY_ID) val id: Int,
        @SerializedName(KEY_FECHA) val fecha: String,
        @SerializedName(KEY_HORA) val hora: String,
        @SerializedName(KEY_ID_ROUTE) val idTrayecto: Int,
        @SerializedName(KEY_ID_BUS) val idBus: Int,
): Parcelable

@Parcelize
data class RouteServer(
        @SerializedName(KEY_ID) val id: Int,
        @SerializedName(KEY_START) val ida: String,
        @SerializedName(KEY_END) val vuelta: String,
        @SerializedName(KEY_TERMINAL) val terminal: String,

): Parcelable

@Parcelize
data class SitServer(
        @SerializedName(KEY_ID) val id: Int,
        @SerializedName(KEY_NUM_ASIENTO) val numAsiento: Int,
        @SerializedName(KEY_ID_BUS) val idBus: Int,
        @SerializedName(KEY_ID_PASAJERO) val idPasenger: Int,
): Parcelable

@Parcelize
data class PassengerServer(
        @SerializedName(KEY_ID) val id: Int,
        @SerializedName(KEY_NAME) val name: String,
        @SerializedName(KEY_LASTNAME) val lastname: String,
        @SerializedName(KEY_RUT) val rut: String,
): Parcelable

