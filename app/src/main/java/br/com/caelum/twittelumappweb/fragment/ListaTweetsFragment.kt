package br.com.caelum.twittelumappweb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.fragment_lista_tweets.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ListaTweetsFragment : Fragment() {

    private val viewModel: TweetViewModel by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_lista_tweets, container, false)

        viewModel.lista().observe(activity!!, Observer { tweets ->
            val tweetAdapter = TweetAdapter(tweets)
            view.listaTweets.adapter = tweetAdapter
            view.swipe.isRefreshing = false
        })

        view.swipe.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_red_dark)
        view.swipe.setOnRefreshListener {
            view.swipe.isRefreshing = true
            viewModel.busca()
        }


        return view
    }
}
