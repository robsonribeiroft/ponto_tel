package br.com.rrdev.pontotel

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import br.com.rrdev.pontotel.dialog.LoadDialog
import br.com.rrdev.pontotel.extension.passwordText
import br.com.rrdev.pontotel.extension.plainText
import br.com.rrdev.pontotel.extension.string
import br.com.rrdev.pontotel.util.Auth
import br.com.rrdev.pontotel.util.PreferencesHelper
import com.google.firebase.auth.FirebaseAuth


class SignInActivity : AppCompatActivity() {

    private lateinit var btnSignIn: Button
    private lateinit var btnNewAccount: Button
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var checkbox: CheckBox

    private lateinit var mAuth: FirebaseAuth

    private val loader = LoadDialog()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        editEmail = findViewById(R.id.edit_sign_in_email)
        editPassword = findViewById(R.id.edit_sign_in_password)
        btnSignIn = findViewById(R.id.btn_sign_in)
        btnNewAccount = findViewById(R.id.btn_new_account)
        checkbox = findViewById(R.id.checkBox_sign_in)

        mAuth = FirebaseAuth.getInstance()

        btnNewAccount.setOnClickListener {
            startActivity(Intent(this@SignInActivity, CreateAccountActivity::class.java))
        }

        btnSignIn.setOnClickListener {
            when{
                Auth.validateEmail(editEmail.string()).not()->{
                    Toast.makeText(applicationContext, getString(R.string.email_invalido), Toast.LENGTH_LONG).show()
                }

                editPassword.string().length < PASSWORD_MINIMUM_SIZE->{
                    Toast.makeText(applicationContext, getString(R.string.senha_requisito), Toast.LENGTH_LONG).show()
                }

                else->{
                    loader.show(supportFragmentManager, null)

                    mAuth.signInWithEmailAndPassword(editEmail.string(), editPassword.string())
                        .addOnCompleteListener(this) { task ->
                            loader.dismiss()
                            if (task.isSuccessful) {

                                val user = mAuth.currentUser
                                user?.email
                                Log.d(TAG, "USer:\n${user.toString()}")
                                user?.let { it ->
                                    PreferencesHelper.save(applicationContext, it.uid)
                                }
                                startActivity(Intent(applicationContext, ListActivity::class.java))

                            } else {
                                Log.d(TAG, "signInWithEmail:failure:\n${task.exception}")
                                Toast.makeText(
                                    applicationContext, getString(R.string.email_utilizado),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }

                }
            }

        }

        checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                editPassword.plainText()
            else
                editPassword.passwordText()
        }
    }

    companion object {
        const val TAG = "PONTO_TEL"
        private const val PASSWORD_MINIMUM_SIZE = 6
    }
}
