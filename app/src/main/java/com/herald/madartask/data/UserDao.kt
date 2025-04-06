package com.herald.madartask.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserModel)

    @Query("SELECT * FROM UserModel")
    fun getAllUsers(): Flow<List<UserModel>>
}