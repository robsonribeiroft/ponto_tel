package br.com.rrdev.pontotel.api.routes

import br.com.rrdev.pontotel.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface PontoTelApi {

    @GET("data.json")
    fun getUsers(): Call<Data>
}