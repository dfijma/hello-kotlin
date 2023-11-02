package nl.dcn.kotlinworkshop.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nl.dcn.kotlinworkshop.model.Artist
import org.springframework.stereotype.Component
import java.io.File

@Component
class Artists {
    val get: List<Artist>
        get() {
            val jsonContent = File("artists.json").readText()
            val listOfSongType = object : TypeToken<List<Artist>>() {}.type
            return Gson().fromJson(jsonContent, listOfSongType);
        }
}