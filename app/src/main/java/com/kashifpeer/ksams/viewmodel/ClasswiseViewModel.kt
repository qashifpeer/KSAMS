package com.kashifpeer.ksams.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kashifpeer.ksams.data.Userdatabase
import com.kashifpeer.ksams.model.Classwise
import com.kashifpeer.ksams.repository.ClasswiseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ClasswiseViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Classwise>>
    private val repository: ClasswiseRepository

    init {
        val classsDao = Userdatabase.getDatabase(
            application
        ).classsDao()
        repository = ClasswiseRepository(classsDao)
        readAllData = repository.readAllData
    }
    fun addClasss(classwise: Classwise){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addClasss(classwise)
        }
    }
}