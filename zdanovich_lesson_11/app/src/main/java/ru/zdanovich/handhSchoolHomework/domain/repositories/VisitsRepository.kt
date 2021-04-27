package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.Visit

interface VisitsRepository {
    fun getVisits(): List<Visit>
}