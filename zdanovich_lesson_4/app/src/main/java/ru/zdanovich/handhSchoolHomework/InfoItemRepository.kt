package ru.zdanovich.handhSchoolHomework

import ru.zdanovich.handhSchoolHomework.models.DetailInfoItem
import ru.zdanovich.handhSchoolHomework.models.InfoItem

class InfoItemRepository {
    fun getInfoItem(): List<InfoItem> = listOf(
        DetailInfoItem(
            icon = R.drawable.ic_bill,
            title = "Квитанции",
            message = "- 40 080,55 ₽",
            hasDebt = true
        ),
        DetailInfoItem(
            icon = R.drawable.ic_counter,
            title = "Счетчики",
            message = "Подайте показания",
            hasDebt = true
        ),
        DetailInfoItem(
            icon = R.drawable.ic_installment,
            title = "Рассрочка",
            message = "Сл. платеж 25.02.2018"
        ),
        DetailInfoItem(
            icon = R.drawable.ic_insurance,
            title = "Страхование",
            message = "Полис до 12.01.2019"
        ),
        DetailInfoItem(
            icon = R.drawable.ic_tv,
            title = "Интернет и ТВ",
            message = "Баланс 350 ₽"
        ),
        DetailInfoItem(
            icon = R.drawable.ic_homephone,
            title = "Домофон",
            message = "Подключен"
        ),
        DetailInfoItem(
            icon = R.drawable.ic_guard,
            title = "Охрана",
            message = "Нет"
        )
    )
}