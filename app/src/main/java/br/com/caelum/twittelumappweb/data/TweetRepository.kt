package br.com.caelum.twittelumappweb.data

import br.com.caelum.twittelumappweb.modelo.Tweet

class TweetRepository {

    fun salva(tweet: Tweet) {}
    fun lista(): List<Tweet> {
        return listOf(
                Tweet("Tweet 1", null),
                Tweet("Tweet 2", null),
                Tweet("Tweet 3", null),
                Tweet("Tweet 4", null),
                Tweet("Tweet 5", null),
                Tweet("Tweet 6", null),
                Tweet("Tweet 7", null),
                Tweet("Tweet 8", null)
        )
    }

}
