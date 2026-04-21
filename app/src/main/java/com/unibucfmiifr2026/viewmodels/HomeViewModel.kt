package com.unibucfmiifr2026.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unibucfmiifr2026.data.AppDatabase
import com.unibucfmiifr2026.data.entities.AddressEntity
import com.unibucfmiifr2026.data.entities.UserEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class HomeViewModel : ViewModel() {
    private val addressDAO = AppDatabase.getDatabase().addressDAO()

    val addresses = addressDAO.getAddresses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addAddress(street: String, city: String) {
        viewModelScope.launch {
            addressDAO.insert(
                AddressEntity(
                    street = street,
                    city = city,
                    country = "Romania"
                )
            )
        }
    }
}