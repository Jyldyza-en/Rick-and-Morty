package kg.tutorialapp.taskfortimelysoft.data.remote

import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    private val locationService: LocationService
) : BaseDataSource(){

    suspend fun getLocations() = getResult { locationService.getAllLocations() }
    suspend fun getLocation(id: Int) = getResult { locationService.getLocation(id) }
}
