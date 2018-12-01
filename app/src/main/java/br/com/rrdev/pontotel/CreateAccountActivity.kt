package br.com.rrdev.pontotel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import br.com.rrdev.pontotel.extension.passwordText
import br.com.rrdev.pontotel.extension.plainText
import br.com.rrdev.pontotel.extension.string
import com.google.firebase.auth.FirebaseAuth


class CreateAccountActivity : AppCompatActivity() {

    private lateinit var btnNewAccount: Button
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var checkbox: CheckBox

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        editEmail = findViewById(R.id.edit_create_account_email)
        editPassword = findViewById(R.id.edit_create_account_password)
        btnNewAccount = findViewById(R.id.btn_create_account)
        checkbox = findViewById(R.id.checkBox_create_account)


        mAuth = FirebaseAuth.getInstance()



        btnNewAccount.setOnClickListener {
            when{
                editEmail.string().isEmpty() || editPassword.string().isEmpty()->{
                    Toast.makeText(applicationContext, getString(R.string.email_invalido), Toast.LENGTH_LONG).show()
                }
                editPassword.string().length < PASSWORD_MINIMUM_SIZE->{
                    Toast.makeText(applicationContext, getString(R.string.senha_requisito), Toast.LENGTH_LONG).show()
                }
                else->{
                    mAuth.createUserWithEmailAndPassword(editEmail.string(), editPassword.string())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val user = mAuth.currentUser
                                this@CreateAccountActivity.finish()
                                Toast.makeText(
                                    applicationContext, getString(R.string.conta_criada),
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.d(TAG, "createUserWithEmail:failure", task.exception)
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
