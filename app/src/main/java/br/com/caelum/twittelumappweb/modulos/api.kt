package br.com.caelum.twittelumappweb.modulos

import br.com.caelum.twittelumappweb.api.InicializadorDoRetrofit
import br.com.caelum.twittelumappweb.api.TweetApi
import br.com.caelum.twittelumappweb.api.UsuarioApi
import org.koin.dsl.module

val apis = module {
    single { InicializadorDoRetrofit.getRetrofit() }
    single { TweetApi(retrofit = get()) }
    single { UsuarioApi(retrofit = get()) }
}
