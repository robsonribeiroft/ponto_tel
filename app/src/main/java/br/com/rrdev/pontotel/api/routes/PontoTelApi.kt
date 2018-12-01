package br.com.rrdev.pontotel.api.routes

import br.com.rrdev.pontotel.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface PontoTelApi {

    companion object {
        const val BASE_URL = "https://s3-sa-east-1.amazonaws.com/pontotel-docs/"
    }

    @GET("data.json")
    fun getUsers(): Call<Data>
}