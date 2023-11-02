package nl.dcn.kotlinworkshop.model

data class Song(
    val Id: Int,
    val Name: String,
    val Year: Int,
    val Artist: String,
    val Shortname: String,
    val Bpm: Int,
    val Duration: Int,
    val Genre: String,
    val SpotifyId: String,
    val Album: String
) {
}