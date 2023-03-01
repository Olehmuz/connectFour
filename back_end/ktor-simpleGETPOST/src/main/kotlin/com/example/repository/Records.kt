package com.example.repository

import org.jetbrains.exposed.sql.Table

object Records : Table() {
    val userName = varchar("userName", 128).uniqueIndex()
    val wins = integer("wins")

    override val primaryKey = PrimaryKey(userName)
}