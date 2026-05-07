package com.unibucfmiifr2026.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.unibucfmiifr2026.localData.AuthDataStore
import com.unibucfmiifr2026.network.RetrofitClient
import com.unibucfmiifr2026.network.dto.LoginRequestDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class AuthState(
    val isLoading: Boolean = false,
    val error: String? = null
)

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val isLoggedIn: Boolean = auth.currentUser != null
    private val authDataStore = AuthDataStore()
    val isApiLoggedIn  = authDataStore.token.stateIn(
        viewModelScope,
        initialValue = null,
        started = SharingStarted.WhileSubscribed(5000)
    )
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState



    fun register(email: String, password: String, onSuccess: () -> Unit) {
        _authState.value = AuthState(isLoading = true)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Registration successful
                _authState.value = AuthState()
                onSuccess()
            }
            .addOnFailureListener { error ->
                // Registration failed
                _authState.value = AuthState(error = error.message)
            }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        _authState.value = AuthState(isLoading = true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Login successful
                Log.e("TAG", "login:Success")
                _authState.value = AuthState()
                onSuccess()
            }
            .addOnFailureListener {
                // Login failed
                Log.e("TAG", "login:Failed")
                _authState.value = AuthState(error = it.message)
            }
    }

    fun apiLogin(email: String, password: String, onSuccess: () -> Unit) {
        _authState.value = AuthState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.authApi.login(LoginRequestDTO(email, password))
                if (response.token.isNullOrEmpty()) {
                    _authState.value = AuthState(error = "Login error")
                    return@launch


                }


                authDataStore.saveToken(response.token)
                _authState.value = AuthState()
                onSuccess()
            } catch (e: Exception) {
                _authState.value = AuthState(error = e.message)
            }
        }
    }

    fun logout() {
        auth.signOut()
        viewModelScope.launch {
            authDataStore.removeToken()
        }


    }

}