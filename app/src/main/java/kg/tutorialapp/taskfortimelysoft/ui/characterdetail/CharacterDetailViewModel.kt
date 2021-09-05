package kg.tutorialapp.taskfortimelysoft.ui.characterdetail

import kg.tutorialapp.taskfortimelysoft.data.repository.CharacterRepository
import kg.tutorialapp.taskfortimelysoft.util.Resource
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.tutorialapp.taskfortimelysoft.data.model.Character

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val character: LiveData<Resource<Character>> = _character


    fun start(id: Int) {
        _id.value = id
    }

}