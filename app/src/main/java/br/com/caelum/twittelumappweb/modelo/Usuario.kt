package br.com.caelum.twittelumappweb.modelo

data class Usuario(
        val nome: String,
        val senha: String,
        val username: String,
        val foto: String? = null
)
