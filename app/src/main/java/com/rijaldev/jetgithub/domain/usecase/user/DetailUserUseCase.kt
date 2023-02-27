package com.rijaldev.jetgithub.domain.usecase.user

import com.rijaldev.jetgithub.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface DetailUserUseCase {

    fun getDetailUserById(id: Int): Flow<User>
}