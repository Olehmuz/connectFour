package com.example.repository

import com.example.models.Record

interface Repository {

    suspend fun addRecord(name: String, wins: Int): Record?

    suspend fun allRecord(): List<Record>
}