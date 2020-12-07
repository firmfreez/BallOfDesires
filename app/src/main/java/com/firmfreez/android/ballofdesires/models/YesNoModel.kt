package com.firmfreez.android.ballofdesires.models

import com.google.gson.annotations.SerializedName

data class YesNoModel(
        @SerializedName("answer")
        val answer: String,

        @SerializedName("forced")
        val value: Boolean,

        @SerializedName("image")
        val imageUrl: String
)