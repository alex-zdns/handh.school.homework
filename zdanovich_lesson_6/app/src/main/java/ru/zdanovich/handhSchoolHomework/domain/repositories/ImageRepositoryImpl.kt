package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.domain.models.ImageContainer

class ImageRepositoryImpl : ImageRepository {
    override fun getImages(): List<ImageContainer> = listOf(
        ImageContainer(
            id = 1,
            image = R.drawable.img_1
        ),
        ImageContainer(
            id = 2,
            image = R.drawable.img_2
        ), ImageContainer(
            id = 3,
            image = R.drawable.img_3
        )
    )
}