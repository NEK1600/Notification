package com.example.myapplication.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_notify")
class ModelNotify(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,

    @ColumnInfo
    var title: String=""
)