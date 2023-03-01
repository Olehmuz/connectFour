package com.example

import com.example.models.Record
import com.example.repository.Repository

class TestRepository : Repository {
    private val records = mutableListOf<Record>()
    override suspend fun addRecord(name: String, wins: Int): Record {
        val oldRecord = records.find { it.name == name }
        if (oldRecord == null) {
            val record = Record(name, wins)
            records += record
            return record
        }
        records -= oldRecord
        val newRecord = oldRecord.copy(wins = oldRecord.wins + 1)
        records += newRecord
        return newRecord
    }

    override suspend fun allRecord(): List<Record> = records
}