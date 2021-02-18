package com.kashifpeer.ksams.repository

import androidx.lifecycle.LiveData
import com.kashifpeer.ksams.data.ClasswiseDao
import com.kashifpeer.ksams.model.Classwise

class ClasswiseRepository(private val classwiseDao: ClasswiseDao) {

    val readAllData: LiveData<List<Classwise>> = classwiseDao.readAllData()


    suspend fun addClasss(classwise: Classwise){
        classwiseDao.addClasss(classwise)
    }

    suspend fun updateClassst(classwise: Classwise){
        classwiseDao.updateClasss(classwise)
    }

    suspend fun deleteClasss(classwise: Classwise){
        classwiseDao.deleteClasss(classwise)
    }
}
