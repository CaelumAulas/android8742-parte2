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
            funcaoSucesso: (Usuario) -> Unit,
            funcaoErro: (Throwable) -> Unit
    ) {
        service.cria(usuario).enqueue(object : Callback<Usuario> {
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
        })
    }

    private interface UsuarioService {

        @POST("/usuario")
        fun cria(@Body usuario: Usuario): Call<Usuario>
    }
}
