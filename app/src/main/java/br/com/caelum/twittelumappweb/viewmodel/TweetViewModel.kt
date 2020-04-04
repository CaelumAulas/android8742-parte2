package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.modelo.Tweet

class TweetViewModel(
        private val repository: TweetRepository,
        private val usuarioRepository: UsuarioRepository
) : ViewModel() {


    fun busca() = repository.busca()
    fun salva(tweet: Tweet) = repository.salva(tweet)
    fun lista() = repository.getLista()
    fun filtra(newText: String?) = repository.filtra(newText)
    fun desloga() = usuarioRepository.desloga()


}
