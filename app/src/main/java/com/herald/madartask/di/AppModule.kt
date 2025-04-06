package com.herald.madartask.di

import android.content.Context
import androidx.room.Room
import com.herald.madartask.data.UserDatabase
import com.herald.madartask.data.UserDatabase.Companion.DB_USERS_NAME
import com.herald.madartask.data.UsersRepoImpl
import com.herald.madartask.domain.UsersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            DB_USERS_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UsersRepo {
        return UsersRepoImpl(db.userDao())
    }
}
