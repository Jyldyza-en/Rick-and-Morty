package kg.tutorialapp.taskfortimelysoft.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kg.tutorialapp.taskfortimelysoft.data.model.Episode

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episodes")
    fun getAllEpisodes() : LiveData<List<Episode>>

    @Query("SELECT * FROM episodes WHERE id = :id")
    fun getEpisode(id: Int) : LiveData<Episode>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodes: List<Episode>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(episode: Episode)
}