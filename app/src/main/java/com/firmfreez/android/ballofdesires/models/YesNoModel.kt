package com.firmfreez.android.ballofdesires.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class YesNoModel(
        @SerializedName("answer")
        val answer: String,

        @SerializedName("forced")
        val value: Boolean,

        @SerializedName("image")
        val imageUrl: String
): Parcelable