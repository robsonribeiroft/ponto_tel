package br.com.rrdev.pontotel.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import br.com.rrdev.pontotel.model.User

@Dao
interface UserDao {

    @Query("SELECT * from user")
    fun getAll(): List<User>?

    @Insert(onConflict = REPLACE)
    fun insert(listItems: List<User>)

    @Query("DELETE from user")
    fun deleteAll()
}