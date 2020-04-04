package br.com.caelum.twittelumappweb.api

import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.modelo.TweetDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class TweetApi(retrofit: Retrofit) {

    private val service by lazy { retrofit.create(TweetService::class.java) }

    fun salva(tweet: TweetDTO) {
        service.salva(tweet).enqueue(object : Callback<Tweet> {
            override fun onFailure(call: Call<Tweet>, t: Throwable) {
            }

            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
            }
        })
    }


    fun buscaLista(
            delegateSucesso: (List<TweetDTO>) -> Unit,
            delegateErro: (Throwable) -> Unit
    ) {

        service.buscaTodos().enqueue(object : Callback<List<TweetDTO>> {
            override fun onFailure(call: Call<List<TweetDTO>>, t: Throwable) {
                delegateErro(t)
            }

            override fun onResponse(call: Call<List<TweetDTO>>, response: Response<List<TweetDTO>>) {
                if (response.isSuccessful) {
                    response.body()?.let(delegateSucesso)
                } else {
                    val mensagemErro = response.errorBody()?.string()
                    delegateErro(Throwable(mensagemErro))
                }
            }
        })
    }

    private interface TweetService {

        @POST("/tweet")
        fun salva(@Body tweet: TweetDTO): Call<Tweet>

        @GET("/tweet")
        fun buscaTodos(): Call<List<TweetDTO>>
    }
}
