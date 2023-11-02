package nl.dcn.kotlinworkshop.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nl.dcn.kotlinworkshop.model.Song
import org.springframework.stereotype.Component
import java.io.File

@Component
class Songs {
    val get: List<Song>
        get() {
            val jsonContent = File("songs.json").readText()
            val listOfSongType = object : TypeToken<List<Song>>() {}.type
            return Gson().fromJson(jsonContent, listOfSongType);
        }
}