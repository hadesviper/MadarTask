package com.herald.madartask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herald.madartask.data.UserModel
import com.herald.madartask.domain.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UsersRepo
) : ViewModel() {

    val allUsers = repository.getUsers()

    fun addUser(user: UserModel) {
        viewModelScope.launch {
            repository.addUser(user)
        }
    }
}