package com.rijaldev.jetgithub.domain.repository

import com.rijaldev.jetgithub.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun searchUsers(query: String): Flow<List<User>>

    fun getDetailUserById(id: Int): Flow<User>
}