package com.kashifpeer.ksams.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kashifpeer.ksams.model.Student
import com.kashifpeer.ksams.model.User
import java.nio.channels.SelectableChannel
import java.util.concurrent.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM std_table")
    suspend fun deleteAllStudents()

    @Query("SELECT * FROM std_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Student>>

    @Query("SELECT * FROM std_table WHERE stdName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Student>>


}