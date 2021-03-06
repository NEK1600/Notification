package com.example.myapplication.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_notify")
class ModelNotify(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,

    @ColumnInfo
    var title: String="" ,

    @ColumnInfo
    var timeMills: Long= 12345678910,

    @ColumnInfo
    var timePerson: String=""

)