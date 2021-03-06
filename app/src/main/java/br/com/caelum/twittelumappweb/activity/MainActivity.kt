package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.fragment.BuscadorDeTweetsFragment
import br.com.caelum.twittelumappweb.fragment.ListaTweetsFragment
import br.com.caelum.twittelumappweb.fragment.MapaFragment
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val tweetViewModel: TweetViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tweetViewModel.busca()

        bottomNavigation.setOnNavigationItemSelectedListener { itemSelecionado ->
            when (itemSelecionado.itemId) {
                R.id.menuLista -> exibe(ListaTweetsFragment())
                R.id.menuBusca -> exibe(BuscadorDeTweetsFragment())
                R.id.menuMapa -> exibe(MapaFragment())
            }
            return@setOnNavigationItemSelectedListener true
        }
        bottomNavigation.selectedItemId = R.id.menuLista


        fabMain.setOnClickListener {
            val vaiParaFormulario = Intent(this, TweetActivity::class.java)
            startActivity(vaiParaFormulario)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sair -> {
                tweetViewModel.desloga()
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun exibe(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }
}
