package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.db.dao.DaoNotify
import com.example.myapplication.db.model.ModelNotify
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ModelNotify::class], version = 2)
abstract class DbNotify : RoomDatabase() {

    abstract fun notifyDao():DaoNotify

    companion object{
        @Volatile
        private var INSTANCE: DbNotify? = null

        fun getDatabase(context: Context,scope: CoroutineScope): DbNotify {

            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,DbNotify::class.java,"db3").build()
                INSTANCE as DbNotify
            }else{
                INSTANCE as DbNotify
            }
        }

    }


}