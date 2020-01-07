package br.com.rrdev.pontotel.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.rrdev.pontotel.model.User

@Dao
interface UserDao {

    @Query("SELECT * from user")
    fun getAll(): List<User>

    @Insert(onConflict = REPLACE)
    fun insert(listItems: List<User>)

    @Query("DELETE from user")
    fun deleteAll()
}