package nl.dcn.kotlinworkshop

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nl.dcn.kotlinworkshop.model.Artist
import nl.dcn.kotlinworkshop.model.Song
import org.junit.jupiter.api.Test
import java.io.File

class ArtistsTest {
    @Test
    fun loadArtistsFile(){
        val jsonContent = File("./src/test/resources/artists.json").readText()
        val listOfArtistType = object : TypeToken<List<Artist>>() {}.type
        val artists: List<Artist> = Gson().fromJson(jsonContent, listOfArtistType)

        println(artists)
    }

    @Test
    fun loadSongsFile(){
        val jsonContent = File("./src/test/resources/songs.json").readText()
        val listOfSongType = object : TypeToken<List<Song>>() {}.type
        val songs: List<Song> = Gson().fromJson(jsonContent, listOfSongType)

        println(songs)
    }
}
