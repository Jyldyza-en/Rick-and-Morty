package kg.tutorialapp.taskfortimelysoft.data.remote

import kg.tutorialapp.taskfortimelysoft.data.model.Location
import kg.tutorialapp.taskfortimelysoft.data.model.LocationList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface LocationService {

    @GET("location/")
    suspend fun getAllLocations(): Response<LocationList>

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): Response<Location>
}