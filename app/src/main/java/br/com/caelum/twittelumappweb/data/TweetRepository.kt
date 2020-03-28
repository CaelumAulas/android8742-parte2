package br.com.caelum.twittelumappweb.data

import br.com.caelum.twittelumappweb.modelo.Tweet

class TweetRepository {

    fun salva(tweet: Tweet) {}
    fun lista(): List<Tweet> {
        return listOf(
                Tweet("Corona virus é chato", null),
                Tweet("Sdd de sair de casa", null),
                Tweet("Qnd isso acabar, vou estar parecendo o Ragnar", null),
                Tweet("Aula de sábado é maneira", null),
                Tweet("Kotlin é show", null),
                Tweet("A galera é quieta", null),
                Tweet("Deu tela azul aqui", null),
                Tweet("Tweet teste", null)
        )
    }

    fun filtra(newText: String?): List<Tweet> {
        if (newText.isNullOrBlank()) {
            return emptyList()
        }
        val todosOsTweets = lista()
        return todosOsTweets.filter { tweet -> tweet.mensagem.contains(newText, ignoreCase = true) }
    }

}
