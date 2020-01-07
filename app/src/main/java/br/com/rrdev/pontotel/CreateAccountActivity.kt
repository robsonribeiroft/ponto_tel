package br.com.rrdev.pontotel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.rrdev.pontotel.extension.passwordText
import br.com.rrdev.pontotel.extension.plainText
import br.com.rrdev.pontotel.extension.string
import br.com.rrdev.pontotel.util.PreferencesHelper
import br.com.rrdev.pontotel.viewmodels.CreateAccountActivityViewModel
import kotlinx.android.synthetic.main.activity_create_account.*


class CreateAccountActivity : AppCompatActivity() {


    private lateinit var createAccountActivityViewModel: CreateAccountActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        createAccountActivityViewModel = ViewModelProviders
            .of(this)
            .get(CreateAccountActivityViewModel::class.java)

        createAccountActivityViewModel
            .createAccountSuccessful
            .observe(this, Observer { result: Pair<Boolean, String?> ->
            if (result.first){
                if (result.second.isNullOrEmpty()){
                    showMessage("uid isNullOrEmpty")
                } else{
                    PreferencesHelper.save(result.second!!)
                }
                finish()
            } else{
                showMessage(getString(R.string.email_utilizado))
            }
        })

        btn_create_account.setOnClickListener {
            val myEmail = edit_create_account_email.string()
            val myPassword = edit_create_account_password.string()
            when{
                myEmail.isEmpty() || myPassword.isEmpty()->{
                    showMessage(getString(R.string.email_invalido))
                }
                myPassword.length < PASSWORD_MINIMUM_SIZE->{
                    showMessage(getString(R.string.senha_requisito))
                }
                else->{
                    createAccountActivityViewModel.createAccount(myEmail, myPassword)
//                    mAuth.createUserWithEmailAndPassword(myEmail, myPassword)
//                        .addOnCompleteListener(this) { task ->
//                            if (task.isSuccessful) {
//                                val user = mAuth.currentUser
//                                this@CreateAccountActivity.finish()
//                                Toast.makeText(
//                                    applicationContext, getString(R.string.conta_criada),
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Log.d(TAG, "createUserWithEmail:failure", task.exception)
//                                Toast.makeText(
//                                    applicationContext, getString(R.string.email_utilizado),
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        }
                }
            }

        }

        checkBox_create_account.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                edit_create_account_password.plainText()
            else
                edit_create_account_password.passwordText()
        }
    }

    private fun showMessage(text: String){
        Toast.makeText(
            applicationContext,
            text,
            Toast.LENGTH_LONG)
            .show()
    }
    companion object {
        const val TAG = "PONTO_TEL"
        private const val PASSWORD_MINIMUM_SIZE = 6
    }
}
