package br.com.rrdev.pontotel.createAccount

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import br.com.rrdev.pontotel.R
import br.com.rrdev.pontotel.extension.passwordText
import br.com.rrdev.pontotel.extension.plainText
import br.com.rrdev.pontotel.extension.string


class CreateAccountActivity : AppCompatActivity(), CreateAccountContract.View {

    private lateinit var btnNewAccount: Button
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var checkbox: CheckBox

    private lateinit var presenter: CreateAccountPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        editEmail = findViewById(R.id.edit_create_account_email)
        editPassword = findViewById(R.id.edit_create_account_password)
        btnNewAccount = findViewById(R.id.btn_create_account)
        checkbox = findViewById(R.id.checkBox_create_account)


        presenter = CreateAccountPresenter()
        presenter.attach(this)


        btnNewAccount.setOnClickListener {
            presenter.createNewAccount(editEmail.string(), editPassword.string())
        }

        checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                editPassword.plainText()
            else
                editPassword.passwordText()
        }
    }

    override fun onSuccess() {
        this.finish()
    }

    override fun onFailure(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val PASSWORD_MINIMUM_SIZE = 6
    }
}
