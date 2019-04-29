package br.com.rrdev.pontotel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.rrdev.pontotel.List.ListActivity
import br.com.rrdev.pontotel.signIn.SignInActivity
import br.com.rrdev.pontotel.util.PreferencesHelper

class PresentationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation)
    }


    override fun onResume() {
        super.onResume()
        Handler().postDelayed({

            val id = PreferencesHelper.retrieve(applicationContext)

            startActivity(Intent(applicationContext, if (id.isNullOrEmpty()) SignInActivity::class.java else ListActivity::class.java))

            finish()
        }, 2000)


    }
}
