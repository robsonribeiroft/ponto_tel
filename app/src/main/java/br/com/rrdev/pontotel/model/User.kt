package br.com.rrdev.pontotel.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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