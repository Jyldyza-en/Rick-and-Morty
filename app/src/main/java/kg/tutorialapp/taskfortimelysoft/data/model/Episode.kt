package kg.tutorialapp.taskfortimelysoft.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey
    val id: Int? = null,
    val name: String? = null,
    val air_date: String? = null,
    val episode: String? = null,
    val url: String? = null,
    val created: String? = null
)
