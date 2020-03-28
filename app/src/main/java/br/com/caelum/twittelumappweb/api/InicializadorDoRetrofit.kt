package br.com.caelum.twittelumappweb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InicializadorDoRetrofit {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://twittelum-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRetrofit(): Retrofit = retrofit
}
