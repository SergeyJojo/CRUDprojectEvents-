package com.learning.crud_events_practice.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Items(
    val title: String,
    val description: String,
    val amount: Int? = 0,
    val isDone: Boolean,
    @PrimaryKey val id: Int?
)
