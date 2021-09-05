package kg.tutorialapp.taskfortimelysoft.ui.episodes

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.tutorialapp.taskfortimelysoft.data.repository.EpisodeRepository
import javax.inject.Inject
@HiltViewModel
class EpisodeViewModel @Inject constructor(
    repository: EpisodeRepository
): ViewModel() {

    val episodes = repository.getEpisodes()
}