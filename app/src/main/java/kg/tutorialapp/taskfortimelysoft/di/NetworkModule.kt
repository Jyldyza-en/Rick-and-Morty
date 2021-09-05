package kg.tutorialapp.taskfortimelysoft.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.tutorialapp.taskfortimelysoft.data.local.CharacterDao
import kg.tutorialapp.taskfortimelysoft.data.remote.CharacterRemoteDataSource
import kg.tutorialapp.taskfortimelysoft.data.remote.CharacterService
import kg.tutorialapp.taskfortimelysoft.data.remote.LocationService
import kg.tutorialapp.taskfortimelysoft.data.repository.CharacterRepository
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharacterService) = CharacterRemoteDataSource(characterService)

    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource,
                          localDataSource: CharacterDao
    ) = CharacterRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideCharacterApiService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)



    @Singleton
    @Provides
    fun provideLocationApiService(retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)


    /*@Singleton
    @Provides
    fun provideEpisodeApiService(retrofit: Retrofit): EpisodeService =
        retrofit.create(EpisodeService::class.java)
    */
}