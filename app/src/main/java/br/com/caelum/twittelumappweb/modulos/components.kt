package br.com.caelum.twittelumappweb.modulos

import br.com.caelum.twittelumappweb.data.UsuarioLocal
import br.com.caelum.twittelumappweb.mapper.TweetMapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val components = module {
    single { UsuarioLocal(androidContext()) }
    single { TweetMapper(usuarioLocal = get()) }
}
