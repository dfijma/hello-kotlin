package nl.dcn.kotlinworkshop.model

import com.google.gson.annotations.SerializedName
import jakarta.persistence.*

@Entity
data class Artist(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val identifier: Long? = null,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: String
)