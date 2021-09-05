package kg.tutorialapp.taskfortimelysoft.data.remote

import kg.tutorialapp.taskfortimelysoft.data.model.Episode
import kg.tutorialapp.taskfortimelysoft.data.model.EpisodeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeService {

    @GET("episode/")
    suspend fun getAllEpisodes(): Response<EpisodeList>

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Response<Episode>
}
