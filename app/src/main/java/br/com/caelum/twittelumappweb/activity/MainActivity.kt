package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.fragment.BuscadorDeTweetsFragment
import br.com.caelum.twittelumappweb.fragment.ListaTweetsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener { itemSelecionado ->
            when (itemSelecionado.itemId) {
                R.id.menuLista -> exibe(ListaTweetsFragment())
                R.id.menuBusca -> exibe(BuscadorDeTweetsFragment())
                R.id.menuMapa -> exibe(Fragment())
            }
            return@setOnNavigationItemSelectedListener true
        }
        bottomNavigation.selectedItemId = R.id.menuLista


        fabMain.setOnClickListener {
            val vaiParaFormulario = Intent(this, TweetActivity::class.java)
            startActivity(vaiParaFormulario)
        }

    }


    private fun exibe(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }
}
