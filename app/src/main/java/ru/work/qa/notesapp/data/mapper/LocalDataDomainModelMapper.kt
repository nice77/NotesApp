package ru.work.qa.notesapp.data.mapper

import ru.work.qa.notesapp.data.local.entity.User
import ru.work.qa.notesapp.domain.model.UserDomainModel
import javax.inject.Inject

class LocalDataDomainModelMapper @Inject constructor(

){

    fun mapUserEntityToDomainModel(user : User) : UserDomainModel {
        return UserDomainModel(
            id = user.id,
            email = user.email,
            username = user.username,
            password = user.password
        )
    }

    fun mapDomainModelToUserEntity(userDomainModel: UserDomainModel) : User {
        return User(
            id = userDomainModel.id,
            email = userDomainModel.email,
            username = userDomainModel.username,
            password = userDomainModel.password
        )
    }

}