package kg.tutorialapp.taskfortimelysoft.ui.locations

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.tutorialapp.taskfortimelysoft.data.repository.LocationRepository
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    repository: LocationRepository
): ViewModel() {

    val locations = repository.getLocations()
}