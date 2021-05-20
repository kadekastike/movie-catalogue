package com.kadek.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "fav_tv")
@Parcelize
data class TvEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
) : Parcelable