package kg.tutorialapp.taskfortimelysoft.ui.characters

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.tutorialapp.taskfortimelysoft.data.repository.CharacterRepository
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    repository: CharacterRepository
): ViewModel() {

    val characters = repository.getCharacters()
}