package com.unibucfmiifr2026.data.models

import androidx.room.Embedded
import androidx.room.Relation
import com.unibucfmiifr2026.data.entities.AddressEntity
import com.unibucfmiifr2026.data.entities.UserEntity

data class AddressWithUser (
    @Embedded
    val address: AddressEntity,
    @Relation(
        parentColumn = AddressEntity.ARG_ID,
        entityColumn = UserEntity.ARG_ADDRESS_ID
    )
    val user: UserEntity
){}