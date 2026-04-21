package com.unibucfmiifr2026.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.unibucfmiifr2026.data.entities.AddressEntity
import com.unibucfmiifr2026.data.models.AddressWithUser
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(address: AddressEntity): Long

    @Query("SELECT * FROM addresses WHERE ${AddressEntity.ARG_ID} = :id")
    suspend fun getAddressWithUser(id: Long): AddressWithUser
    @Query("SELECT * FROM addresses WHERE ${AddressEntity.ARG_ID} = :id")
    suspend fun getAddressWithUsers(id: Long): List<AddressWithUser>
    @Query("SELECT * FROM addresses")
    fun getAddresses(): Flow<List<AddressEntity>>

}