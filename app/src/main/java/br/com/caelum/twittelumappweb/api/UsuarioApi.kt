package br.com.caelum.twittelumappweb.api

import br.com.caelum.twittelumappweb.modelo.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

class UsuarioApi(retrofit: Retrofit) {


    private val service by lazy { retrofit.create(UsuarioService::class.java) }


    fun cria(
            usuario: Usuario,
            funcaoSucesso: (Usuario) -> Unit, //high order fuction
            funcaoErro: (Throwable) -> Unit
    ) {
        service.cria(usuario).enqueue(callback(funcaoErro, funcaoSucesso))
    }


    fun loga(
            usuario: Usuario,
            funcaoSucesso: (Usuario) -> Unit, //high order fuction
            funcaoErro: (Throwable) -> Unit
    ) {
        service.login(usuario).enqueue(callback(funcaoErro, funcaoSucesso))
    }

    private fun callback(funcaoErro: (Throwable) -> Unit, funcaoSucesso: (Usuario) -> Unit): Callback<Usuario> {
        return object : Callback<Usuario> {
            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                funcaoErro(t)
            }

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                if (response.isSuccessful) {
                    response.body()?.let(funcaoSucesso)
                } else {
                    val message = response.errorBody()?.string()
                    val erro = Throwable(message)
                    funcaoErro(erro)
                }
            }
        }
    }

    private interface UsuarioService {

        @POST("/usuario")
        fun cria(@Body usuario: Usuario): Call<Usuario>

        @POST("/usuario/login")
        fun login(@Body usuario: Usuario): Call<Usuario>
    }
}
