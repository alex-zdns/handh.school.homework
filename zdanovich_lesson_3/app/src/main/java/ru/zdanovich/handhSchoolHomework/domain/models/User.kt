package ru.zdanovich.handhSchoolHomework.domain.models

data class User(
    val id: Long,
    val name: String,
    val surname: String,
    val position: String,
    val email: String,
    val login: String,
    val region: String
)
