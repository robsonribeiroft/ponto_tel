package br.com.rrdev.pontotel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.rrdev.pontotel.util.PreferencesHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PresentationActivity : AppCompatActivity(), CoroutineScope {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation)
    }


    override fun onResume() {
        super.onResume()
        launch {
            delay(1500)
            startActivity(
                Intent(
                applicationContext,
                if (PreferencesHelper.userIsLogged()) ListActivity::class.java
                else SignInActivity::class.java
            ))
            finish()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Main
}
