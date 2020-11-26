package com.example.thefalgbusstop.data.network

import android.os.Parcelable
import com.example.thefalgbusstop.data.network.ApiConstants.KEY_MESSAGE_RESPONSE
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class responsePojoServer(
    @SerializedName(KEY_MESSAGE_RESPONSE) val message: String,
): Parcelable
