package br.com.rrdev.pontotel.extension

import br.com.rrdev.pontotel.api.routes.PontoTelApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun Retrofit.setup(route: String = PontoTelApi.BASE_URL): PontoTelApi {
    val retrofit = Retrofit.Builder().baseUrl(route).addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit.create(PontoTelApi::class.java)
}