package com.kadek.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    val id : Int,
    val name: String,
    val releaseDate: String,
    val description: String,
    val poster: String,
    val favorited: Boolean
    ) : Parcelable