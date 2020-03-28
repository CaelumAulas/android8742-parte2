package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import java.lang.Exception

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val tweetRepository by lazy { TweetRepository() }
    private val usuarioRepository by lazy { UsuarioRepository() }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        TweetViewModel::class.java -> TweetViewModel(tweetRepository) as T
        UsuarioViewModel::class.java -> UsuarioViewModel(usuarioRepository) as T
        else -> throw  Exception()
    }

}
