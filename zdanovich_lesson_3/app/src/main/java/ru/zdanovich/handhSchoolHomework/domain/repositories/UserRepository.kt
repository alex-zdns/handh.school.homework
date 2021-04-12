package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.User

interface UserRepository {
    fun getUserById(id: Long): User
}