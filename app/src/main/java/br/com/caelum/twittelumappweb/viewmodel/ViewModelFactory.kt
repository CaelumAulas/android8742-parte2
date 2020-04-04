package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.api.InicializadorDoRetrofit
import br.com.caelum.twittelumappweb.api.TweetApi
import br.com.caelum.twittelumappweb.api.UsuarioApi
import br.com.caelum.twittelumappweb.app.TwittelumApp
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioLocal
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.mapper.TweetMapper
import java.lang.Exception

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val application by lazy { TwittelumApp.getInstance() }
    private val retrofit by lazy { InicializadorDoRetrofit.getRetrofit() }
    private val usuarioApi by lazy { UsuarioApi(retrofit) }
    private val tweetApi by lazy { TweetApi(retrofit) }
    private val usuarioLocal by lazy { UsuarioLocal(application) }
    private val mapper by lazy { TweetMapper(usuarioLocal) }
    private val tweetRepository by lazy { TweetRepository(tweetApi, mapper) }
    private val usuarioRepository by lazy { UsuarioRepository(usuarioApi, usuarioLocal) }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        TweetViewModel::class.java -> TweetViewModel(tweetRepository, usuarioRepository) as T
        UsuarioViewModel::class.java -> UsuarioViewModel(usuarioRepository) as T
        else -> throw  Exception()
    }

}
