package kz.podcast.payloadsexample.models

enum class InternetStatus {
    Online,
    Offline,
    Undefined
}

data class Person(
    val id: Int,
    val name: String,
    val avaUrl: ImageUrl,
    val internetStatus: InternetStatus = InternetStatus.Undefined
)
