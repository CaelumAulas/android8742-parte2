package br.com.caelum.twittelumappweb.data

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

    init {
        usuarioLiveData.postValue(usuarioLocal.getUsuario())
    }

    fun getUsuario() = usuarioLiveData as LiveData<Usuario>
    fun getErro() = erroLiveData as LiveData<Throwable>

    fun cria(usuario: Usuario) {
        api.cria(usuario, funcaoSucesso = delegateSucesso(), funcaoErro = delegateErro())
    }


    fun desloga() {
        usuarioLocal.desloga()
        usuarioLiveData.postValue(usuarioLocal.getUsuario())
    }

    private fun delegateSucesso(): (Usuario) -> Unit = { usuario: Usuario ->
        usuarioLiveData.postValue(usuario)
        usuarioLocal.salva(usuario)
    }


    private fun delegateErro(): (Throwable) -> Unit = { erro: Throwable ->
        erroLiveData.postValue(erro)
    }


    fun logar(usuario: Usuario) {
        api.loga(usuario, funcaoSucesso = delegateSucesso(), funcaoErro = delegateErro())
    }

}
