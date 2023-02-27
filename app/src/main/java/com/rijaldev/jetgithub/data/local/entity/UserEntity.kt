package com.rijaldev.jetgithub.data.local.entity

data class UserEntity(
    val id: Int,
    val username: String,
    val type: String,
    val avatarUrl: String,
    val fullName: String,
    val bio: String,
    val repositories: Int,
    val followers: Int,
    val following: Int,
)