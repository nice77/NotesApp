package ru.work.qa.notesapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.work.qa.notesapp.data.local.database.dao.UserDao
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

    override suspend fun containsEmail(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            val result = userDao.containsEmail(email)
            result != null
        }
    }

    override suspend fun findByEmail(email: String): UserDomainModel? {
        return withContext(Dispatchers.IO) {
            val result = userDao.findByEmail(email)
            result?.let {
                return@withContext localDataDomainModelMapper.mapUserEntityToDomainModel(result)
            }
            return@withContext null
        }
    }
}