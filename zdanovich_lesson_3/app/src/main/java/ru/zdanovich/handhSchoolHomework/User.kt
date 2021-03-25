package ru.zdanovich.handhSchoolHomework

data class User(
    val id: Long,
    val name: String,
    val surname: String,
    val position: String,
    val email: String,
    val login: String,
    val region: String
) {
    companion object {
        fun getSampleUser(): User = User(
            id = 7898769,
            name = "Анастасия",
            surname = "Антонина",
            email = "anykee.box@gmail.ru",
            position = "Специалист",
            login = "HIE023UOI88",
            region = "Санкт-Петербург"
        )
    }
}