package br.com.rrdev.pontotel.signIn

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import br.com.rrdev.pontotel.List.ListActivity
import br.com.rrdev.pontotel.R
import br.com.rrdev.pontotel.createAccount.CreateAccountActivity
import br.com.rrdev.pontotel.dialog.LoadDialog
import br.com.rrdev.pontotel.extension.passwordText
import br.com.rrdev.pontotel.extension.plainText
import br.com.rrdev.pontotel.extension.string


class SignInActivity : AppCompatActivity(), SignInContract.View {



    private lateinit var btnSignIn: Button
    private lateinit var btnNewAccount: Button
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var checkbox: CheckBox



    private val loader = LoadDialog()

    private lateinit var presenter: SignInPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        editEmail = findViewById(R.id.edit_sign_in_email)
        editPassword = findViewById(R.id.edit_sign_in_password)
        btnSignIn = findViewById(R.id.btn_sign_in)
        btnNewAccount = findViewById(R.id.btn_new_account)
        checkbox = findViewById(R.id.checkBox_sign_in)

        presenter = SignInPresenter()
        presenter.attach(this)

        btnNewAccount.setOnClickListener {
            startActivity(Intent(this@SignInActivity, CreateAccountActivity::class.java))
        }

        btnSignIn.setOnClickListener {
            presenter.signIn(editEmail.string(), editPassword.string())

        }

        checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                editPassword.plainText()
            else
                editPassword.passwordText()
        }
    }

    override fun onSuccess() {
        startActivity(Intent(applicationContext, ListActivity::class.java))
    }

    override fun onFailure(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }


    override fun showLoader() {
        loader.show(supportFragmentManager, null)
    }

    override fun hideLoader() {
        loader.dismiss()
    }
}
