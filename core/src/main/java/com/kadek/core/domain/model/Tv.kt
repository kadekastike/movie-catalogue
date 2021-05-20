package com.kadek.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tv (
    val id: Int,
    val title: String,
    val firstAirDate: String,
    val description: String,
    val poster: String,
    val favorited: Boolean
    ) : Parcelable