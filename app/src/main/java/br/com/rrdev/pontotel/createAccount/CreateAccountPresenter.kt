package br.com.rrdev.pontotel.createAccount

import android.app.Activity
import android.content.Context
import android.util.Log
import br.com.rrdev.pontotel.R
import br.com.rrdev.pontotel.createAccount.CreateAccountActivity.Companion.PASSWORD_MINIMUM_SIZE
import com.google.firebase.auth.FirebaseAuth

class CreateAccountPresenter : CreateAccountContract.Presenter<CreateAccountContract.View> {

    private lateinit var view: CreateAccountContract.View
    private lateinit var context: Context

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun attach(view: CreateAccountContract.View) {
        this.view = view
        context = view as Context
    }

    override fun createNewAccount(email: String, password: String) {
        when{
            email.isEmpty() || password.isEmpty()-> view.onFailure(context.getString(R.string.email_invalido))
            password.length < PASSWORD_MINIMUM_SIZE -> view.onFailure(context.getString(R.string.senha_requisito))
            else-> createAccount(email, password)
        }
    }

    private fun createAccount(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context as Activity) { task ->
                    if (task.isSuccessful) {
                        view.onSuccess()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("CHECK", task.exception?.message)
                        view.onFailure(context.getString(R.string.email_utilizado))
                    }
                }
    }


}