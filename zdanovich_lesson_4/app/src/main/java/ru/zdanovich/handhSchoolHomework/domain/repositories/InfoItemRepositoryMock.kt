package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.domain.models.BaseInfoItem
import ru.zdanovich.handhSchoolHomework.domain.models.DetailInfoItem

class InfoItemRepositoryMock: InfoItemRepository {
    override fun getInfoItem(): List<BaseInfoItem> = listOf(
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
        ),
        BaseInfoItem(
            icon = R.drawable.ic_uk_contacts,
            title = "Контакты УК и служб"
        ),
        BaseInfoItem(
            icon = R.drawable.ic_request,
            title = "Мои заявки"
        ),
        BaseInfoItem(
            icon = R.drawable.ic_about,
            title = "Памятка жителя А101 "
        )
    )
}