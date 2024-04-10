package ru.work.qa.notesapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.work.qa.notesapp.data.local.dao.UserDao
import ru.work.qa.notesapp.data.mapper.LocalDataDomainModelMapper
import ru.work.qa.notesapp.domain.model.UserDomainModel
import ru.work.qa.notesapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val localDataDomainModelMapper: LocalDataDomainModelMapper
) : UserRepository {
    override suspend fun createUser(userDomainModel: UserDomainModel) {
        withContext(Dispatchers.IO) {
            userDao.createUser(localDataDomainModelMapper.mapDomainModelToUserEntity(userDomainModel))
        }
    }
}