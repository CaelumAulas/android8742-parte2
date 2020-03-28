package br.com.caelum.twittelumappweb.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.api.UsuarioApi
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioRepository(
        private val api: UsuarioApi,
        private val usuarioLocal: UsuarioLocal
) {

    private val usuarioLiveData = MutableLiveData<Usuario>()
    private val erroLiveData = MutableLiveData<Throwable>()

    fun getUsuario() = usuarioLiveData as LiveData<Usuario>
    fun getErro() = erroLiveData as LiveData<Throwable>

    fun cria(usuario: Usuario) {
        api.cria(usuario, funcaoSucesso = delegateSucesso(), funcaoErro = delegateErro())
    }

    private fun delegateSucesso(): (Usuario) -> Unit = { usuario: Usuario ->
        usuarioLiveData.postValue(usuario)
        usuarioLocal.salva(usuario)
    }


    private fun delegateErro(): (Throwable) -> Unit = { erro: Throwable ->
        erroLiveData.postValue(erro)
    }


    fun logar(usuario: Usuario) {
        Log.i("usuario", "logado")
    }

}
