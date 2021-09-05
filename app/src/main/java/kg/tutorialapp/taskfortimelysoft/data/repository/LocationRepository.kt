package kg.tutorialapp.taskfortimelysoft.data.repository

import kg.tutorialapp.taskfortimelysoft.data.local.LocationDao
import kg.tutorialapp.taskfortimelysoft.data.remote.LocationRemoteDataSource
import kg.tutorialapp.taskfortimelysoft.util.getOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    private val remoteDataSource: LocationRemoteDataSource,
    private val localDataSource: LocationDao
) {
    fun getLocation(id: Int) = getOperation(
        databaseQuery = {localDataSource.getLocation(id)},
        networkCall = {remoteDataSource.getLocation(id)},
        saveCallResult = {localDataSource.insert(it)}
    )

    fun getLocations() = getOperation(
        databaseQuery = {localDataSource.getAllLocations()},
        networkCall = {remoteDataSource.getLocations()},
        saveCallResult = { localDataSource.insertAll(it.results!!) }
    )
}