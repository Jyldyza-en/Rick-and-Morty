package kg.tutorialapp.taskfortimelysoft.data.remote

import javax.inject.Inject

class EpisodeRemoteDataSource @Inject constructor(
    private val episodeService: EpisodeService
) : BaseDataSource(){

    suspend fun getEpisodes() = getResult { episodeService.getAllEpisodes() }
    suspend fun getEpisode(id: Int) = getResult { episodeService.getEpisode(id) }
}