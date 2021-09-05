package kg.tutorialapp.taskfortimelysoft.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kg.tutorialapp.taskfortimelysoft.data.local.AppDatabase
import kg.tutorialapp.taskfortimelysoft.data.local.CharacterDao
import kg.tutorialapp.taskfortimelysoft.data.local.LocationDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase): CharacterDao {
        return db.characterDao()
    }

    @Singleton
    @Provides
    fun provideLocationDao(db: AppDatabase): LocationDao {
        return db.locationDao()
    }

}
