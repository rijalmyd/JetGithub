package com.rijaldev.jetgithub.data.repository

import com.rijaldev.jetgithub.data.local.LocalDataSource
import com.rijaldev.jetgithub.domain.repository.UserRepository
import com.rijaldev.jetgithub.util.DataMapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
) : UserRepository {

    override fun searchUsers(query: String) = flow {
        val users = DataMapper.mapUserEntitiesToDomain(local.getUsers())
            .filter {
                it.fullName.contains(query, ignoreCase = true) or it.username.contains(query, ignoreCase = true)
            }
        emit(users)
    }

    override fun getDetailUserById(id: Int) = flow {
        val detailUser = local.getUsers().first { it.id == id }
        val detailUserDomain = DataMapper.mapUserEntityToDomain(detailUser)
        emit(detailUserDomain)
    }
}