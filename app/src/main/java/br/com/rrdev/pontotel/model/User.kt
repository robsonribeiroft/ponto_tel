package br.com.rrdev.pontotel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    var roomId: Long=0,
    @ColumnInfo
    val id: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val pwd: String)