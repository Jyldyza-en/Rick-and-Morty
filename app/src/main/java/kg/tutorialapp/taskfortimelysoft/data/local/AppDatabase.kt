package kg.tutorialapp.taskfortimelysoft.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kg.tutorialapp.taskfortimelysoft.data.model.Character
import kg.tutorialapp.taskfortimelysoft.data.model.Location

@Database(entities = [Character::class, Location::class], version = 4, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao

    companion object{

        @Volatile private var instance: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase =
            instance?: synchronized(this) { instance?: buildDatabase(context).also { instance = it} }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .fallbackToDestructiveMigration()
                .build()
    }


}