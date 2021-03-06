package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: UsuarioViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.getUsuario().observe(this, Observer {
            if (it.isValid()) {
                Log.i("user", "$it")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })

        viewModel.getErro().observe(this, Observer {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        })

        login_entrar.setOnClickListener { viewModel.loga(pegaUsuarioDaTela()) }
        login_criar.setOnClickListener { viewModel.cria(pegaUsuarioDaTela()) }

    }

    private fun pegaUsuarioDaTela(): Usuario {
        val nome = login_campoNome.text.toString()
        val senha = login_campoSenha.text.toString()
        val username = login_campoUsername.text.toString()

        return Usuario(nome = nome, username = username, senha = senha)
    }
}
