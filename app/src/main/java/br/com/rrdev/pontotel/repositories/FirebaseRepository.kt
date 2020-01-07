package br.com.rrdev.pontotel.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository{


    private val firebase = FirebaseAuth.getInstance()
    val logInSuccessful = MutableLiveData<Pair<Boolean, String?>>()
    val createAccountSuccessFul = MutableLiveData<Pair<Boolean, String?>>()

    companion object{
        private const val TAG = "FIREBASE_RESULT_TAG"
    }

    fun logIn(email: String, password: String){
        firebase.signInWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful){
                logInSuccessful.value = Pair(task.isSuccessful, firebase.currentUser?.uid)
            } else {
                Log.d(TAG, "createAccount error\n${task.exception}")
            }
            logInSuccessful.value = Pair(task.isSuccessful, task.exception?.message)
        }
    }

    fun logOut() = firebase.signOut()

    fun createAccount(email: String, password: String){
        firebase.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                createAccountSuccessFul.value = Pair(task.isSuccessful, firebase.currentUser?.uid)
            } else {
                Log.d(TAG, "createAccount error\n${task.exception}")
            }
            createAccountSuccessFul.value = Pair(task.isSuccessful, task.exception?.message)
        }

    }

}