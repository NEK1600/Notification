package com.example.myapplication.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.db.model.ModelNotify

@Dao
interface DaoNotify {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notifyModel: ModelNotify)

    @Delete()
    suspend fun delete(notifyModel: ModelNotify)

    @Query("SELECT * FROM table_notify")
    fun getAllNotify(): LiveData<List<ModelNotify>>

   /* @Query("SELECT * FROM table_notify WHERE id = :id")
    fun getNotifyById(id: Long?): LiveData<List<ModelNotify>>*/



}