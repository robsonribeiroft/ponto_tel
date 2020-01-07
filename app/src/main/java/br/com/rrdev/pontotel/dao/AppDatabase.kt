package br.com.rrdev.pontotel.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.rrdev.pontotel.PontotelApplication.Companion.application
import br.com.rrdev.pontotel.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE : AppDatabase? = null


        fun getInstance():AppDatabase{
            if (INSTANCE == null){
                synchronized(AppDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(application, AppDatabase::class.java, "mydata.db").build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}