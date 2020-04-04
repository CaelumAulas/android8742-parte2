package br.com.caelum.twittelumappweb.api

import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.modelo.TweetDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
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


    private interface TweetService {

        @POST("/tweet")
        fun salva(@Body tweet: TweetDTO): Call<Tweet>
    }
}
