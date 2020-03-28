package br.com.caelum.twittelumappweb.data

import android.content.Context
import android.content.SharedPreferences
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioLocal(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UsuarioSP", Context.MODE_PRIVATE)

    fun salva(usuario: Usuario) {
        sharedPreferences
                .edit()
                .putInt("id", usuario.id)
                .putString("nome", usuario.nome)
                .putString("username", usuario.username)
                .putString("senha", usuario.senha)
                .apply()
    }

    fun getUsuario(): Usuario {
        val username = sharedPreferences.getString("username", "")!!
        val nome = sharedPreferences.getString("nome", "")!!
        val senha = sharedPreferences.getString("senha", "")!!
        val id = sharedPreferences.getInt("id", 0)
        return Usuario(nome = nome, senha = senha, username = username, id = id)
    }


}
