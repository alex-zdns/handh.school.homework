package ru.zdanovich.handhSchoolHomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.zdanovich.handhSchoolHomework.databinding.ActivityMainBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Visit
import ru.zdanovich.handhSchoolHomework.domain.repositories.VisitsRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.VisitsRepositoryRandomImpl
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val repository: VisitsRepository = VisitsRepositoryRandomImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun mapVisitToColumnChartViewData(visit: List<Visit>): List<ColumnChartView.Data> =
        repository.getVisits().map {
            ColumnChartView.Data(
                value = it.minutes,
                caption = it.date.format(formatter)
            )
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd:MM")
    }
}