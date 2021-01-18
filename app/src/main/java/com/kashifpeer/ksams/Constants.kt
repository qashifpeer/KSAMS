package com.kashifpeer.ksams

object Constants {
    //db name
    const val DB_NAME="MY_RECORDS_DB"
    const val DB_VERSION=1
    //table name
    const val TABLE_NAME="MY_RECORDS_TABLE"
    //columns/fields of table
    const val C_ID="ID"
    const val C_NAME="NAME"
    const val C_PHONE="PHONE"
    const val C_EMAIL="EMAIL"
    const val C_IMAGE="IMAGE"
    const val C_ADDED_TIMESTAMP="ADDED_TIME_STAMP"
    const val C_UPDATED_TIMESTAMP="UPDATED_TIME_STAMP"

    //CREATE TABLE QUERRY
    const val CREATE_TABLE=("CREATE TABLE " + TABLE_NAME + "("+ C_ID + "PRIMARY KEY AUTOINCREMENT"
                    + C_PHONE + "TEXT,"
                    + C_EMAIL + "TEXT,"
                    + C_IMAGE + "TEXT,"
                    + C_ADDED_TIMESTAMP + "TEXT,"
                    + C_UPDATED_TIMESTAMP + "TEXT"+")"
            )

}