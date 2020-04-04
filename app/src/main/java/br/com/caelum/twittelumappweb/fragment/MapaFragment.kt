package br.com.caelum.twittelumappweb.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.modelo.TweetDTO
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaFragment : SupportMapFragment() {

    private val viewModel by lazy { ViewModelProvider(activity!!, ViewModelFactory)[TweetViewModel::class.java] }

    override fun onResume() {
        super.onResume()
        getMapAsync { map ->
            viewModel.lista().observe(this, Observer { tweets ->
                tweets.map { it.toMarker() }.forEach { map.addMarker(it) }
            })
        }
    }
}

private fun TweetDTO.toMarker(): MarkerOptions {
    val markerOptions = MarkerOptions()
    markerOptions.title(mensagem)
    markerOptions.snippet(dono.nome)
    markerOptions.position(LatLng(latitude, longitude))
    return markerOptions
}
