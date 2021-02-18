package com.kashifpeer.ksams.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kashifpeer.ksams.model.Classwise

@Dao
interface ClasswiseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addClasss(classwise: Classwise)

    @Update
    suspend fun updateClasss(classwise: Classwise)

    @Delete
    suspend fun deleteClasss(classwise: Classwise)

    @Query("DELETE FROM class_table")
    suspend fun deleteAllClassses()

    @Query("SELECT * FROM class_table ORDER BY CID ASC")
    fun readAllData(): LiveData<List<Classwise>>
}