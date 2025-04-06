package com.herald.madartask.domain

import com.herald.madartask.data.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersRepo {
    suspend fun addUser(user: UserModel)
    fun getUsers(): Flow<List<UserModel>>
}
