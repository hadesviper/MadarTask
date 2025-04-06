package com.herald.madartask.data

import com.herald.madartask.domain.UsersRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepoImpl @Inject constructor(private val usersDao: UserDao): UsersRepo {
    override suspend fun addUser(user: UserModel) = usersDao.insertUser(user)
    override fun getUsers(): Flow<List<UserModel>> = usersDao.getAllUsers()
}