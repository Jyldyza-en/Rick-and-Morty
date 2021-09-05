package kg.tutorialapp.taskfortimelysoft.ui.episodesdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.tutorialapp.taskfortimelysoft.data.model.Episode
import kg.tutorialapp.taskfortimelysoft.data.repository.EpisodeRepository
import kg.tutorialapp.taskfortimelysoft.util.Resource
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _episode = _id.switchMap { id ->
        repository.getEpisode(id)
    }
    val episode: LiveData<Resource<Episode>> = _episode


    fun start(id: Int) {
        _id.value = id
    }

}