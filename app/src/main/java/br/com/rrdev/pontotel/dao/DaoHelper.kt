package br.com.rrdev.pontotel.dao

import android.content.Context
import android.media.MediaPlayer
import br.com.rrdev.pontotel.listener.DaoHelperListerner
import br.com.rrdev.pontotel.model.User

class DaoHelper(private val listener: DaoHelperListerner){


    fun obtain(context: Context){
        Thread{
            val all=AppDatabase.getInstance(context).userDao().getAll()
            listener.getAll(all ?: ArrayList())
        }.start()
    }

    fun save(context: Context,list: List<User>){
        Thread{
            AppDatabase.getInstance(context).userDao().run {
                deleteAll()
                insert(list)
            }
        }.start()
    }
}
