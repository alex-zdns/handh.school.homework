package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.domain.models.CommunalService
import ru.zdanovich.handhSchoolHomework.domain.models.CommunalServiceType

class CommunalServiceRepositoryMock : CommunalServiceRepository {
    override fun getCommunalServiceCards(): List<CommunalService> = listOf(
        CommunalService(
            type = CommunalServiceType.Watter,
            icon = R.drawable.ic_water_cold,
            title = "Холодная вода",
            accountId = 54656553,
            messageInHTML = "Необходимо подать показания до 25.03.18",
            hasDept = true
        ),
        CommunalService(
            type = CommunalServiceType.Watter,
            icon = R.drawable.ic_water_hot,
            title = "Горячая вода",
            accountId = 54656564,
            messageInHTML = "Необходимо подать показания до 25.03.18",
            hasDept = true
        ),
        CommunalService(
            type = CommunalServiceType.Electricity,
            icon = R.drawable.ic_electro,
            title = "Электроэнергия",
            accountId = 54656579,
            messageInHTML = "Показания сданы <b>16.02.18</b> и будут учтены при следующем расчете <b>25.02.18</b>",
            hasDept = false
        )
    )
}