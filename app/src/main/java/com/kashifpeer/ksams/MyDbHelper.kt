package com.kashifpeer.ksams

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.AudioTimestamp
import android.media.Image
import android.provider.ContactsContract

//database helper class that contains all crude ops
class MyDbHelper(context: Context?):SQLiteOpenHelper(context,Constants.DB_NAME,null,Constants.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        //create table on that db
        db.execSQL(Constants.CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + Constants.CREATE_TABLE)
        onCreate(db)

    }

    //insert record to db
    fun insertRecord(
        name: String?,
        image: String?,
        phone: String?,
        email: String?,
        addedTime: String?,
        updatedTime: String?
    ) {
        //get writable database
        val db = this.writableDatabase
        val values = ContentValues()
        //id will be inserted automatically
        values.put(Constants.C_NAME, name)
        values.put(Constants.C_IMAGE, image)
        values.put(Constants.C_PHONE, phone)
        values.put(Constants.C_EMAIL, email)
        values.put(Constants.C_ADDED_TIMESTAMP, addedTime)
        values.put(Constants.C_UPDATED_TIMESTAMP, updatedTime)

        //insert row, it will record id of saved record
        val id = db.insert(Constants.TABLE_NAME, null, values)
        //close db connection
        db.close()
        //return ID of inserted record
            //return id
    }


}