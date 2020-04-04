package br.com.caelum.twittelumappweb.fragment

import androidx.lifecycle.Observer
import br.com.caelum.twittelumappweb.modelo.TweetDTO
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MapaFragment : SupportMapFragment() {

    private val viewModel: TweetViewModel by sharedViewModel()

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
