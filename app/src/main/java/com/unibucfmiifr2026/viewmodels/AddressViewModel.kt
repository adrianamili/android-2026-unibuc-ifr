package com.unibucfmiifr2026.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibucfmiifr2026.data.AppDatabase
import com.unibucfmiifr2026.data.entities.AddressEntity
import com.unibucfmiifr2026.data.entities.UserEntity
import kotlinx.coroutines.launch

class AddressViewModel : ViewModel() {
    private val userDAO = AppDatabase.getDatabase().userDAO()
    private val addressDAO = AppDatabase.getDatabase().addressDAO()

    fun addUser(firstName: String, lastName: String, addressId: Long) {
        viewModelScope.launch {
            userDAO.insert(
                UserEntity(
                    firstName = firstName,
                    lastName = lastName,
                    addressId = addressId
                )
            )
        }
    }


}