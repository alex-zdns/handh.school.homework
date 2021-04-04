package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.User

class UserRepositoryMock : UserRepository {
    override fun getUserById(id: Long): User = User(
        id = 7898769,
        name = "Анастасия",
        surname = "Антонина",
        email = "anykee.box@gmail.ru",
        position = "Специалист",
        login = "HIE023UOI88",
        region = "Санкт-Петербург"
    )
}