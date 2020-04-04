package br.com.caelum.twittelumappweb.mapper

import br.com.caelum.twittelumappweb.data.UsuarioLocal
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.modelo.TweetDTO

class TweetMapper(private val usuarioLocal: UsuarioLocal) {

    fun map(tweet: Tweet): TweetDTO {
        return TweetDTO(tweet.mensagem, tweet.foto, usuarioLocal.getUsuario())
    }


}
