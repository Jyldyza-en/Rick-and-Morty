package kg.tutorialapp.taskfortimelysoft.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey
    val id: Int? = null,
    val name: String? = null,
    val type: String? = null,
    val dimension: String? = null,
    val url: String? = null,
    val created: String? = null

)
