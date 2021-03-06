package br.com.caelum.twittelumappweb.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.decodificaParaBase64
import br.com.caelum.twittelumappweb.gps.GPS
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.activity_tweet.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File


class TweetActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by viewModel()

    private var localFoto: String? = null

    private val gps by lazy { GPS(this) }
    private val accessFineLocation = Manifest.permission.ACCESS_FINE_LOCATION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (checkSelfPermission(accessFineLocation) == PackageManager.PERMISSION_GRANTED) {
            gps.busca()
        } else {
            requestPermissions(arrayOf(accessFineLocation), 456)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 456 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            gps.busca()
        } else {
            Toast.makeText(this, "Não conseguimos pegar sua localizacão sem a permissão", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.tweet_menu, menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            android.R.id.home -> finish()


            R.id.tweet_menu_cadastrar -> {

                publicaTweet()

                finish()

            }


            R.id.tweet_menu_foto -> {

                tiraFoto()

            }

        }

        return true

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                carregaFoto()
            }
        }
    }


    private fun publicaTweet() {

        val tweet = criaTweet()

        viewModel.salva(tweet)

        Toast.makeText(this, "$tweet foi salvo com sucesso :D", Toast.LENGTH_LONG).show()
    }

    fun criaTweet(): Tweet {

        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.tweet_mensagem)

        val mensagemDoTweet: String = campoDeMensagemDoTweet.text.toString()

        val foto: String? = tweet_foto.tag as String?

        val (latitude, longitude) = gps.getCoordenadas()

        return Tweet(mensagemDoTweet, foto, latitude, longitude)
    }


    private fun tiraFoto() {

        val vaiPraCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val caminhoFoto = defineLocalDaFoto()

        vaiPraCamera.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)

        startActivityForResult(vaiPraCamera, 123)

    }

    private fun defineLocalDaFoto(): Uri {

        localFoto = "${getExternalFilesDir("fotos")}/${System.currentTimeMillis()}.jpg"

        val arquivo = File(localFoto)

        return FileProvider.getUriForFile(this, "TweetProvider", arquivo)
    }


    private fun carregaFoto() {

        val bitmap = BitmapFactory.decodeFile(localFoto)

        val bm = Bitmap.createScaledBitmap(bitmap, 300, 300, true)

        tweet_foto.setImageBitmap(bm)

        val fotoNaBase64 = bm.decodificaParaBase64()

        tweet_foto.tag = fotoNaBase64

        tweet_foto.scaleType = ImageView.ScaleType.FIT_XY

    }


}
