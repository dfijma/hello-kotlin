package nl.dcn.kotlinworkshop.service

import nl.dcn.kotlinworkshop.data.Artists
import nl.dcn.kotlinworkshop.data.Songs
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DataParser @Autowired constructor(
    private val artists: Artists,
    private val songs: Songs
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun log(s: String) {
        logger.info(s)
    }

    init {
        logger.info("Artists: {}", artists.get.size)
        logger.info("Songs: {}", songs.get.size)
    }


    fun prepareData(){

    }
}