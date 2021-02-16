package com.kashifpeer.ksams.repository

import androidx.lifecycle.LiveData
import com.kashifpeer.ksams.data.StudentDao
import com.kashifpeer.ksams.model.Student
import com.kashifpeer.ksams.model.User

class StudentRepository(private val studentDao: StudentDao) {

    val readAllData: LiveData<List<Student>> = studentDao.readAllData()

    suspend fun addStudent(student: Student){
        studentDao.addStudent(student)
    }

    suspend fun updateStudent(student: Student){
        studentDao.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student){
        studentDao.deleteStudent(student)
    }

    suspend fun deleteAllStudents(){
        studentDao.deleteAllStudents()
    }



}