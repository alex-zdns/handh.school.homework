package ru.zdanovich.handhSchoolHomework.domain.models

sealed class CommunalServiceType {
    object Watter : CommunalServiceType()
    object Electricity : CommunalServiceType()
}