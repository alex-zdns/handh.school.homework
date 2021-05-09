package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.ImageContainer

interface ImageRepository {
    fun getImages(): List<ImageContainer>
}