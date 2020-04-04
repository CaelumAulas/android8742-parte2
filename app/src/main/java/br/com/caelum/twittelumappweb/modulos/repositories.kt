package br.com.caelum.twittelumappweb.modulos

import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import org.koin.dsl.module

val repositories = module {
    single { TweetRepository(api = get(), mapper = get()) }
    single { UsuarioRepository(api = get(), usuarioLocal = get()) }
}
