package com.rijaldev.jetgithub.data.local

import com.rijaldev.jetgithub.util.GithubUserData
import javax.inject.Inject

class LocalDataSource @Inject constructor() {

    fun getUsers() = GithubUserData.getDummyUsers()
}