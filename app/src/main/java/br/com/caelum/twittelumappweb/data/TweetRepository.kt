package br.com.caelum.twittelumappweb.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.api.TweetApi
import br.com.caelum.twittelumappweb.mapper.TweetMapper
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.modelo.TweetDTO

class TweetRepository(
        private val api: TweetApi,
        private val mapper: TweetMapper
) {

    private val listaLiveData = MutableLiveData<List<TweetDTO>>()
    private val erroLiveData = MutableLiveData<Throwable>()

    fun getLista() = listaLiveData as LiveData<List<TweetDTO>>
    fun getErro() = erroLiveData as LiveData<Throwable>

    fun salva(tweet: Tweet) {
        val tweetDto = mapper.map(tweet)
        api.salva(tweetDto)
    }


    fun busca() = api.buscaLista(sucesso(), error())

    private fun error(): (Throwable) -> Unit = { erroLiveData.postValue(it) }

    private fun sucesso(): (List<TweetDTO>) -> Unit = { listaLiveData.postValue(it) }

    fun filtra(newText: String?): List<TweetDTO> {
        if (newText.isNullOrBlank()) {
            return emptyList()
        }
        val todosOsTweets = listaLiveData.value
        return todosOsTweets?.filter { tweet -> tweet.mensagem.contains(newText, ignoreCase = true) }
                ?: emptyList()
    }

}
