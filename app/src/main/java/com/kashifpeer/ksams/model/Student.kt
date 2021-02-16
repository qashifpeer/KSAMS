package com.kashifpeer.ksams.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "std_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val rollNum: Int,
    val adNum: Int,
    val stdName: String,
    val fatherName: String,
    val motherName: String,
    val residence: String,
    val stdClass: String,
    val stdUid : String,
    val stdPhone :String,
    val adDate: String,
    val birthDate: String,
    val stdGender: String


): Parcelable