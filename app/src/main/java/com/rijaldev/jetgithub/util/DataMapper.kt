package com.rijaldev.jetgithub.util

import com.rijaldev.jetgithub.data.local.entity.UserEntity
import com.rijaldev.jetgithub.domain.model.user.User

object DataMapper {

    fun mapUserEntitiesToDomain(users: List<UserEntity>): List<User> =
        users.map { user -> mapUserEntityToDomain(user = user) }

    fun mapUserEntityToDomain(user: UserEntity): User =
        User(
            id = user.id,
            username = user.username,
            type = user.type,
            avatarUrl = user.avatarUrl,
            fullName = user.fullName,
            bio = user.bio,
            repositories = user.repositories,
            following = user.following,
            followers = user.followers
        )
}