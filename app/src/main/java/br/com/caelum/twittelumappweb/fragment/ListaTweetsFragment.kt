package br.com.caelum.twittelumappweb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_lista_tweets.view.*


class ListaTweetsFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(activity!!, ViewModelFactory).get(TweetViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.busca()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_lista_tweets, container, false)

        viewModel.lista().observe(activity!!, Observer { tweets ->
            val tweetAdapter = TweetAdapter(tweets)
            view.listaTweets.adapter = tweetAdapter
        })

        return view
    }
}
