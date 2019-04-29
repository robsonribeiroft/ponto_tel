package br.com.rrdev.pontotel.dao

import android.content.Context
import br.com.rrdev.pontotel.listener.UserDBListener
import br.com.rrdev.pontotel.model.User

class DaoHelper(private val context: Context){

    private val userDao: UserDao = AppDatabase.getInstance(context).userDao()

    fun obtain(){
        Thread{
            val listener = context as UserDBListener
            val all= userDao.getAll()
            listener.getAll(all ?: ArrayList())
        }.start()
    }

    fun save(list: List<User>){
        Thread{
            userDao.run {
                deleteAll()
                insert(list)
            }
        }.start()
    }
}
