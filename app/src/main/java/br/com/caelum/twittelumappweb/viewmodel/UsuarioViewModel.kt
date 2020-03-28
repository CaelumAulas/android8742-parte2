package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    fun loga(usuario: Usuario) = repository.logar(usuario)
    fun cria(usuario: Usuario) = repository.cria(usuario)

}
