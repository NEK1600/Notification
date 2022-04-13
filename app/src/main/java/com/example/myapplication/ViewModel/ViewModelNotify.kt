package com.example.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.DbNotify
import com.example.myapplication.db.model.ModelNotify
import com.example.myapplication.db.repository.RepositoryNotify
import kotlinx.coroutines.launch

class ViewModelNotify(application: Application): AndroidViewModel(application) {
    val context = application
    private val repository:RepositoryNotify
    val allNotifyList: LiveData<List<ModelNotify>>

    /*fun initDatabase(){
        val daoNotify = DbNotify.getDatabase(context,viewModelScope).notifyDao()
        val repository = RepositoryNotify(daoNotify)
        val allNotifyList = repository.allNotify
    }*/

    init{
        val wordsDao = DbNotify.getDatabase(application,viewModelScope).notifyDao()
        repository = RepositoryNotify(wordsDao)
        allNotifyList = repository.repositoryAllNotify
    }

    fun getAllNotify():LiveData<List<ModelNotify>>{
        return  repository.repositoryAllNotify
    }

    fun insert(notify:ModelNotify) = viewModelScope.launch {
        repository.insertNotify(notify)
    }

}