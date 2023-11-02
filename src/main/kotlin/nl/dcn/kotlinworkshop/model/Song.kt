package nl.dcn.kotlinworkshop.model

import com.google.gson.annotations.SerializedName
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Song(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val identifier: Long? = null,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Year")
    val year: Int,
    @SerializedName("Artist")
    val artist: String,
    @SerializedName("Shortname")
    val shortname: String,
    @SerializedName("Bpm")
    val bpm: Int,
    @SerializedName("Duration")
    val duration: Int,
    @SerializedName("Genre")
    val genre: String,
    @SerializedName("SpotifyId")
    val spotifyId: String,
    @SerializedName("Album")
    val album: String
) {
}