package br.com.caelum.twittelumappweb.app

import android.app.Application
import br.com.caelum.twittelumappweb.modulos.apis
import br.com.caelum.twittelumappweb.modulos.components
import br.com.caelum.twittelumappweb.modulos.repositories
import br.com.caelum.twittelumappweb.modulos.viewmodels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TwittelumApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@TwittelumApp)
            modules(arrayListOf(apis, components, repositories, viewmodels))
        }
    }

    companion object {
        private lateinit var instance: TwittelumApp

        fun getInstance() = instance
    }
}
