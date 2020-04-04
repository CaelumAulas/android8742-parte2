package br.com.caelum.twittelumappweb.modulos

import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodels = module {
    viewModel { UsuarioViewModel(repository = get()) }
    viewModel { TweetViewModel(repository = get(), usuarioRepository = get()) }
}
