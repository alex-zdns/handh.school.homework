package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.Advert

interface AdvertRepository {
    fun getAdverts(): List<Advert>
}