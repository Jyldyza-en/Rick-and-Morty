package kg.tutorialapp.taskfortimelysoft.ui.locationsdetail

import dagger.hilt.android.lifecycle.HiltViewModel
import kg.tutorialapp.taskfortimelysoft.util.Resource
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import kg.tutorialapp.taskfortimelysoft.data.model.Location
import kg.tutorialapp.taskfortimelysoft.data.repository.LocationRepository

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _location = _id.switchMap { id ->
        repository.getLocation(id)
    }
    val location: LiveData<Resource<Location>> = _location


    fun start(id: Int) {
        _id.value = id
    }

}