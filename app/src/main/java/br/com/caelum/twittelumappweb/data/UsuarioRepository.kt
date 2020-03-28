package br.com.caelum.twittelumappweb.data

import android.util.Log
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioRepository {

    fun cria(usuario: Usuario) {
        Log.i("usuario", "criado")
    }

    fun logar(usuario: Usuario) {
        Log.i("usuario", "logado")
    }

}
