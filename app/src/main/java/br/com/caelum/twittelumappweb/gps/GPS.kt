package br.com.caelum.twittelumappweb.gps

import android.content.Context
import android.location.Location
import com.google.android.gms.location.*

class GPS(contexto: Context) : LocationCallback() {

    private val client: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(contexto)

    private var ultimaLocalizacao: Location? = null

    fun busca() {
        val request = LocationRequest()
        request.apply {
            smallestDisplacement = 10F
            interval = 1000 * 10
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        client.requestLocationUpdates(request, this, null)
    }

    override fun onLocationResult(resultado: LocationResult) {
        ultimaLocalizacao = resultado.lastLocation
    }

    fun getCoordenadas(): Pair<Double, Double> {
        val latitude = ultimaLocalizacao?.latitude ?: 0.0
        val longitude = ultimaLocalizacao?.longitude ?: 0.0
        return latitude to longitude
    }
}
