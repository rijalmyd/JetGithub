package com.rijaldev.jetgithub.domain.usecase.user

import com.rijaldev.jetgithub.domain.repository.UserRepository
import javax.inject.Inject

class ListUserInteractor @Inject constructor(
    private val userRepository: UserRepository
) : ListUserUseCase {
    
    override fun searchUsers(query: String) = userRepository.searchUsers(query)
}