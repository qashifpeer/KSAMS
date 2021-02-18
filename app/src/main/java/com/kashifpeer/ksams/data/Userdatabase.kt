package com.kashifpeer.ksams.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kashifpeer.ksams.model.Classwise
import com.kashifpeer.ksams.model.Student
import com.kashifpeer.ksams.model.User
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [User::class, Student::class, Classwise::class],version = 1,exportSchema = false)
abstract class Userdatabase:RoomDatabase() {

    abstract fun userDao():UserDao
    abstract fun studentDao():StudentDao
    abstract fun classsDao():ClasswiseDao
    //abstract fun attendanceDao():AttendanceDao
    companion object{
        @Volatile
        private var INSTANCE:Userdatabase?=null

        @InternalCoroutinesApi
        fun getDatabase(context: Context):Userdatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    Userdatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE=instance
                return instance

            }


        }
    }

}