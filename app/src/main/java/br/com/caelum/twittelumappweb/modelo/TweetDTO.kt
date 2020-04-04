package br.com.caelum.twittelumappweb.modelo

data class TweetDTO(
        val mensagem: String,
        val foto: String?,
        val dono: Usuario,
        val id: Int? = null
)
