package com.example.models

@kotlinx.serialization.Serializable
data class Record(
    val name: String,
    val wins: Int = 0
)
