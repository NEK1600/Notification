package com.example.myapplication.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.myapplication.db.model.ModelNotify

@Dao
interface DaoNotify {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notifyModel: ModelNotify)

    @Delete
    suspend fun delete(notifyModel: ModelNotify)

    @Query("SELECT * FROM table_notify")
    fun getAllNotify(): LiveData<List<ModelNotify>>

    @Query("DELETE FROM table_notify")
    fun getNotifyById()

    @Query("DELETE FROM table_notify WHERE Id = :id")
    fun getDeleteNotifyById(id: Int?)



}