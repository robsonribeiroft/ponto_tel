package br.com.rrdev.pontotel

import android.app.Application
import android.content.Context
import br.com.rrdev.pontotel.api.routes.PontoTelApi
import br.com.rrdev.pontotel.dao.AppDatabase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PontotelApplication: Application() {

    companion object{
        lateinit var application: Context
        lateinit var database: AppDatabase
        lateinit var api: PontoTelApi
        lateinit var firebase: FirebaseAuth
    }


    private fun setupApi(){
        api = Retrofit.Builder().baseUrl("https://s3-sa-east-1.amazonaws.com/pontotel-docs/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PontoTelApi::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        application = applicationContext
        database = AppDatabase.getInstance()
        FirebaseApp.initializeApp(applicationContext)
        firebase = FirebaseAuth.getInstance()
        setupApi()
    }
}