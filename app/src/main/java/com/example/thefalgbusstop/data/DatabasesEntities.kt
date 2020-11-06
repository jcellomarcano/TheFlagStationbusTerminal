package com.example.thefalgbusstop.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chofer")
data class ChoferEntity(
    @PrimaryKey @ColumnInfo(name = "chofer_id") var id: Int,
    @ColumnInfo(name = "chofer_name") var name: String,
    @ColumnInfo(name = "chofer_lastname") var lastname: String,
    @ColumnInfo(name = "chofer_rut") var rut: String,
)
