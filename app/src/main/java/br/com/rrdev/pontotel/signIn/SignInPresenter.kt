package br.com.rrdev.pontotel.signIn

import android.app.Activity
import android.content.Context
import br.com.rrdev.pontotel.R
import br.com.rrdev.pontotel.createAccount.CreateAccountActivity
import br.com.rrdev.pontotel.util.PreferencesHelper
import com.google.firebase.auth.FirebaseAuth

class SignInPresenter : SignInContract.Presenter<SignInContract.View> {

    private lateinit var view: SignInContract.View
    private lateinit var context: Context

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun attach(view: SignInContract.View) {
        this.view = view
        context = view as Context
    }

    override fun signIn(email: String, password: String) {
        when{
            email.isEmpty() || password.isEmpty()-> view.onFailure(context.getString(R.string.email_invalido))
            password.length < CreateAccountActivity.PASSWORD_MINIMUM_SIZE -> view.onFailure(context.getString(R.string.senha_requisito))
            else-> signIn(email, password)
        }
    }


    private fun signInFirebase(email: String, password: String){
        view.showLoader()
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context as Activity) { task ->
                    view.hideLoader()
                    if (task.isSuccessful) {
                        mAuth.currentUser?.let { it ->
                            PreferencesHelper.save(context, it.uid)
                        }
                        view.onSuccess()
                    } else {
                        view.onFailure(context.getString(R.string.email_utilizado))
                    }

                }
    }

}