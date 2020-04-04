package br.com.caelum.twittelumappweb.modelo

data class Usuario(
        val nome: String,
        val senha: String,
        val username: String,
        val id: Int = 0,
        val foto: String? = null
) {
    fun isValid(): Boolean {
        return id != 0
    }
}
