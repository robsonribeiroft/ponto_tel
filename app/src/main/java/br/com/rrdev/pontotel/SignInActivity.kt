package br.com.rrdev.pontotel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.rrdev.pontotel.dialog.LoadDialog
import br.com.rrdev.pontotel.extension.isValidEmail
import br.com.rrdev.pontotel.extension.passwordText
import br.com.rrdev.pontotel.extension.plainText
import br.com.rrdev.pontotel.extension.string
import br.com.rrdev.pontotel.util.PreferencesHelper
import br.com.rrdev.pontotel.viewmodels.SignInActivityViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    private lateinit var signInActivityViewModel: SignInActivityViewModel
    private val loader = LoadDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInActivityViewModel = ViewModelProviders
            .of(this)
            .get(SignInActivityViewModel::class.java)

        signInActivityViewModel.logInSuccess.observe(this, Observer { result: Pair<Boolean, String?> ->
            if (result.first){
                if (result.second.isNullOrEmpty()){
                    showMessage("uid isNullOrEmpty")
                } else{
                    PreferencesHelper.save(result.second!!)
                    startActivity(Intent(applicationContext, ListActivity::class.java))
                }
                finish()
            } else{
                showMessage(getString(R.string.email_utilizado))
            }
        })

        btn_new_account.setOnClickListener {
            startActivity(Intent(this@SignInActivity, CreateAccountActivity::class.java))
            finish()
        }

        btn_sign_in.setOnClickListener {
            val myEmail = edit_sign_in_email.string()
            val myPassword = edit_sign_in_password.string()
            when{
                myEmail.isValidEmail().not()-> showMessage(getString(R.string.email_invalido))

                myPassword.length < PASSWORD_MINIMUM_SIZE-> showMessage(getString(R.string.senha_requisito))

                else->{
                    signInActivityViewModel.login(myEmail, myPassword)
                }
            }

        }

        checkBox_sign_in.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                edit_sign_in_password.plainText()
            else
                edit_sign_in_password.passwordText()
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
