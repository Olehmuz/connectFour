package com.example.repository

import com.example.models.Record
import com.example.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class RecordsRepository : Repository {
    override suspend fun addRecord(name: String, wins: Int): Record? {
        var statement: InsertStatement<Number>? = null

        dbQuery {

            try {
                Records.deleteWhere { Records.userName.eq(name) }
            } finally {

            }
            statement = Records.insert {
                it[Records.userName] = name
                it[Records.wins] = wins
            }
        }
        println(rowToRecord(statement?.resultedValues?.get(0)))
        return rowToRecord(statement?.resultedValues?.get(0))
    }

    override suspend fun allRecord(): List<Record> {
        return dbQuery {
            Records.selectAll().mapNotNull { rowToRecord(it) }.sortedBy { it.wins }.reversed()
        }
    }

    private fun rowToRecord(row :ResultRow?): Record? {
        row ?: return null
        return Record(
            name = row[Records.userName],
            wins = row[Records.wins]
        )
    }
}