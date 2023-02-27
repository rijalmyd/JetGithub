package com.rijaldev.jetgithub.domain.usecase.user

import com.rijaldev.jetgithub.domain.repository.UserRepository
import javax.inject.Inject

class DetailUserInteractor @Inject constructor(
    private val userRepository: UserRepository
) : DetailUserUseCase {

    override fun getDetailUserById(id: Int) = userRepository.getDetailUserById(id)
}