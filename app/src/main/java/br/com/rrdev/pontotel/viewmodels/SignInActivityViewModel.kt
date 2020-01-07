package br.com.rrdev.pontotel.viewmodels

import androidx.lifecycle.ViewModel
import br.com.rrdev.pontotel.repositories.FirebaseRepository

class SignInActivityViewModel: ViewModel() {

    private val firebaseRepository = FirebaseRepository()
    val logInSuccess = firebaseRepository.logInSuccessful

    fun login(email: String, password: String){
        firebaseRepository.logIn(email, password)

    }
}