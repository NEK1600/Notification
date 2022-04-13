package com.example.myapplication.db.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.db.dao.DaoNotify
import com.example.myapplication.db.model.ModelNotify

class RepositoryNotify(private val daoNotify: DaoNotify) {

    val repositoryAllNotify :LiveData<List<ModelNotify>> = daoNotify.getAllNotify()

    suspend fun insertNotify(notifyModel: ModelNotify){
        daoNotify.insert(notifyModel)
    }
    suspend fun deleteNotify(notifyModel: ModelNotify) {
        daoNotify.delete(notifyModel)

    }
}