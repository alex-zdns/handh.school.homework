package ru.zdanovich.handhSchoolHomework.domain.repositories

import ru.zdanovich.handhSchoolHomework.domain.models.Visit
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class VisitsRepositoryRandomImpl : VisitsRepository {
    private val random = Random(Date().time)

    override fun getVisits(): List<Visit> {
        val number = generateRandomIntFromRange(numberRange)
        val list: MutableList<Visit> = ArrayList<Visit>(number)

        val today = LocalDate.now()

        for (i in 0 until number) {
            list.add(
                Visit(
                    minutes = generateRandomIntFromRange(minutesRange),
                    date = today.minusDays(i.toLong())
                )
            )
        }

        return list
    }

    private fun generateRandomIntFromRange(range: IntRange): Int =
        random.nextInt(range.last - range.first + 1) + range.first

    companion object {
        val numberRange = 1..9
        val minutesRange = 10..99
    }
}