package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.domain.models.Advert

class AdvertRepositoryMock : AdvertRepository {
    override fun getAdverts(): List<Advert> = listOf(
        Advert(
            image = R.drawable.img_croissant,
            title = "Царь пышка",
            message = "Скидка 10% на выпечку по коду",
            address = "Лермонтовский пр, 52, МО №1"
        ),
        Advert(
            image = R.drawable.img_logomayh,
            title = "Химчистка «МАЙ?»",
            message = "Скидка 5% на чистку пальто",
            address = "Лермонтовский пр, 48"
        ),
        Advert(
            image = R.drawable.img_shawarma,
            title = "Шаверма У Ашота",
            message = "При покупке шавермы, фалафель бесплатно",
            address = "Лермонтовский пр, 50, МО №1"
        ),
        Advert(
            image = R.drawable.img_croissant,
            title = "Царь пышка",
            message = "Скидка 10% на выпечку по коду",
            address = "Лермонтовский пр, 52, МО №1"
        ),
        Advert(
            image = R.drawable.img_logomayh,
            title = "Химчистка «МАЙ?»",
            message = "Скидка 5% на чистку пальто",
            address = "Лермонтовский пр, 48"
        ),
        Advert(
            image = R.drawable.img_shawarma,
            title = "Шаверма У Ашота",
            message = "При покупке шавермы, фалафель бесплатно",
            address = "Лермонтовский пр, 50, МО №1"
        ),
        Advert(
            image = R.drawable.img_croissant,
            title = "Царь пышка",
            message = "Скидка 10% на выпечку по коду",
            address = "Лермонтовский пр, 52, МО №1"
        ),
        Advert(
            image = R.drawable.img_logomayh,
            title = "Химчистка «МАЙ?»",
            message = "Скидка 5% на чистку пальто",
            address = "Лермонтовский пр, 48"
        ),
        Advert(
            image = R.drawable.img_shawarma,
            title = "Шаверма У Ашота",
            message = "При покупке шавермы, фалафель бесплатно",
            address = "Лермонтовский пр, 50, МО №1"
        ),
        Advert(
            image = R.drawable.img_croissant,
            title = "Царь пышка",
            message = "Скидка 10% на выпечку по коду",
            address = "Лермонтовский пр, 52, МО №1"
        ),
        Advert(
            image = R.drawable.img_logomayh,
            title = "Химчистка «МАЙ?»",
            message = "Скидка 5% на чистку пальто",
            address = "Лермонтовский пр, 48"
        ),
        Advert(
            image = R.drawable.img_shawarma,
            title = "Шаверма У Ашота",
            message = "При покупке шавермы, фалафель бесплатно",
            address = "Лермонтовский пр, 50, МО №1"
        )
    ).shuffled()
}