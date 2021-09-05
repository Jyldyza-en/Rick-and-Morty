package kg.tutorialapp.taskfortimelysoft.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kg.tutorialapp.taskfortimelysoft.data.model.Location
@Dao
interface LocationDao {

    @Query("SELECT * FROM locations")
    fun getAllLocations() : LiveData<List<Location>>

    @Query("SELECT * FROM locations WHERE id = :id")
    fun getLocation(id: Int) : LiveData<Location>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Location>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: Location)
}