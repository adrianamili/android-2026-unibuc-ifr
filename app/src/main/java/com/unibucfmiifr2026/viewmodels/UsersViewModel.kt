package com.unibucfmiifr2026.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibucfmiifr2026.network.RetrofitClient
import com.unibucfmiifr2026.network.dto.UserDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UsersState(
    val isLoading: Boolean = false,
    val users: List<UserDTO> = emptyList(),
    val error: String? = null
)

class UsersViewModel : ViewModel() {
    private val _getUsersState = MutableStateFlow(UsersState())
    val getUsersState: StateFlow<UsersState> = _getUsersState



    init {
        getUsers()
    }

    fun getUsers() {
        _getUsersState.value = UsersState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getUsers(1)
                _getUsersState.value = UsersState(users = response.data)
            } catch (e: Exception) {
                _getUsersState.value = UsersState(error = e.message)

            }
        }
    }

}