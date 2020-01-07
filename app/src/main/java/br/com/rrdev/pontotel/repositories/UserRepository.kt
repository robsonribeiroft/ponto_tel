package br.com.rrdev.pontotel.repositories

import androidx.lifecycle.MutableLiveData
import br.com.rrdev.pontotel.PontotelApplication.Companion.api
import br.com.rrdev.pontotel.dao.UserDao
import br.com.rrdev.pontotel.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class UserRepository(private val userDao: UserDao): CoroutineScope {

    val allUsers = MutableLiveData<List<User>>()

    fun getAllUsersApi() = launch{
        try {
            val response = api.getUsers().execute()
            if (response.isSuccessful){
                val data = response.body()!!.data
                saveUsersLocal(data)
                withContext(Main){ allUsers.value = data }
            }else{
                val data =userDao.getAll()
                withContext(Main){ allUsers.value = data}
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getAllUsersDao() = launch {
        allUsers.value = userDao.getAll()
    }

    private fun saveUsersLocal(list: List<User>) = launch {
        userDao.insert(list)
    }



    override val coroutineContext: CoroutineContext
        get() = IO
}