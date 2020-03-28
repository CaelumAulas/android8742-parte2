package br.com.caelum.twittelumappweb.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_lista_tweets.*

class BuscadorDeTweetsFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(activity!!, ViewModelFactory).get(TweetViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lista_tweets, container, false)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_busca_fragment, menu)

        val item = menu.findItem(R.id.menuBuscaFragment)

        val searchView = item.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("onQueryTextSubmit", "chamou")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val listaFiltrados = viewModel.filtra(newText)
                listaTweets?.adapter = TweetAdapter(listaFiltrados)
                return true
            }
        })

    }


}
