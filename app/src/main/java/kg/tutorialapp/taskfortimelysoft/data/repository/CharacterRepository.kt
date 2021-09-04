package kg.tutorialapp.taskfortimelysoft.data.repository

import kg.tutorialapp.taskfortimelysoft.data.local.CharacterDao
import kg.tutorialapp.taskfortimelysoft.data.remote.CharacterRemoteDataSource
import kg.tutorialapp.taskfortimelysoft.util.getOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDao
) {
    fun getCharacter(id: Int) = getOperation(
        databaseQuery = {localDataSource.getCharacter(id)},
        networkCall = {remoteDataSource.getCharacter(id)},
        saveCallResult = {localDataSource.insert(it)}
    )

    fun getCharacters() = getOperation(
        databaseQuery = {localDataSource.getAllCharacters()},
        networkCall = {remoteDataSource.getCharacters()},
        saveCallResult = { localDataSource.insertAll(it.results!!) }
    )
}