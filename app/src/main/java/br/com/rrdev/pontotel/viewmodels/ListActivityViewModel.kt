package br.com.rrdev.pontotel.viewmodels

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import br.com.rrdev.pontotel.PontotelApplication.Companion.database
import br.com.rrdev.pontotel.dao.AppDatabase
import br.com.rrdev.pontotel.repositories.FirebaseRepository
import br.com.rrdev.pontotel.repositories.UserRepository
import br.com.rrdev.pontotel.util.PreferencesHelper
import com.google.firebase.auth.FirebaseAuth

class ListActivityViewModel: ViewModel() {

    private val userRepository = UserRepository(database.userDao())
    private val firebaseRepository = FirebaseRepository()
    val allUsers = userRepository.allUsers

    fun getAllUsers()  {
        userRepository.getAllUsersApi()
    }

    fun getAllUsersLocal(){
        userRepository.getAllUsersDao()
    }

    fun logOut(){
        PreferencesHelper.clear()
        firebaseRepository.logOut()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroyInstances(){
        AppDatabase.destroyInstance()
        Log.d("DCAB", "Lifecycle")
    }

}