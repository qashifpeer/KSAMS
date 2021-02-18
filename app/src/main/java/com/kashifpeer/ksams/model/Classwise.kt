package com.kashifpeer.ksams.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "class_table")
data class Classwise(
        @PrimaryKey(autoGenerate = true)
        val CID: Int,
        val className: String

): Parcelable
