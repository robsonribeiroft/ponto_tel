package br.com.rrdev.pontotel.api.configuration

import br.com.rrdev.pontotel.api.routes.PontoTelApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSetup {

    companion object {

        fun getInstance(): PontoTelApi = Retrofit.Builder().baseUrl(PontoTelApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(PontoTelApi::class.java)

    }
}