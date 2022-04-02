package kz.podcast.payloadsexample.utils

import kz.podcast.payloadsexample.models.Person
import kz.podcast.payloadsexample.models.ImageUrl

private val firstNames = listOf(
    "Thomas",
    "Robert",
    "Michael",
    "Kairat"
)

private val lastNames = listOf(
    "Anderson",
    "Martin",
    "Jackson",
    "Nurtas"
)

private val avaUrls = listOf(
    ImageUrl("https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/avatars/89/89496ab402ac222c0ddaed7add5d9eb6deb759f9_full.jpg"),
    ImageUrl("https://lh3.googleusercontent.com/b91FFh2EPsExNTHHqECbEQsqDSgaBeOxYWIZfNeYdXfmBOIFPpbyB2VphB_6m_g5iu_ACtgA11X-64TsqWUtdv5x9fFzco4N7OzFYio=w600"),
    ImageUrl("https://forums.heroesofnewerth.com/uploads/monthly_2020_08/32.thumb.jpg.46c2cadec656a63e6e0a038d712ef439.jpg"),
    ImageUrl("https://img-9gag-fun.9cache.com/photo/aGZ29yK_460s.jpg")
)

fun generatePersons(): List<Person> = mutableListOf<Person>().apply {
    repeat(10) { index ->
        add(
            Person(
                id = index,
                name = "${firstNames.random()} ${lastNames.random()}",
                avaUrl = avaUrls.random()
            )
        )
    }
}.toList()
