package br.com.rrdev.pontotel.viewmodels

import androidx.lifecycle.ViewModel
import br.com.rrdev.pontotel.repositories.FirebaseRepository

class CreateAccountActivityViewModel: ViewModel() {

    private val firebaseRepository = FirebaseRepository()

    val createAccountSuccessful = firebaseRepository.createAccountSuccessFul



    fun createAccount(email: String, password: String){
        firebaseRepository.createAccount(email, password)
    }
}