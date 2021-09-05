package kg.tutorialapp.taskfortimelysoft.data.repository

import kg.tutorialapp.taskfortimelysoft.data.local.EpisodeDao
import kg.tutorialapp.taskfortimelysoft.data.remote.EpisodeRemoteDataSource
import kg.tutorialapp.taskfortimelysoft.util.getOperation
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val remoteDataSource: EpisodeRemoteDataSource,
    private val localDataSource: EpisodeDao
) {
    fun getEpisode(id: Int) = getOperation(
        databaseQuery = {localDataSource.getEpisode(id)},
        networkCall = {remoteDataSource.getEpisode(id)},
        saveCallResult = {localDataSource.insert(it)}
    )

    fun getEpisodes() = getOperation(
        databaseQuery = {localDataSource.getAllEpisodes()},
        networkCall = {remoteDataSource.getEpisodes()},
        saveCallResult = { localDataSource.insertAll(it.results!!) }
    )
}