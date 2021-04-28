package ru.zdanovich.handhSchoolHomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.databinding.ActivityMainBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Visit
import ru.zdanovich.handhSchoolHomework.domain.repositories.VisitsRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.VisitsRepositoryRandomImpl
import ru.zdanovich.handhSchoolHomework.views.ColumnChartView
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val repository: VisitsRepository = VisitsRepositoryRandomImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.columnChartView.setData(mapVisitToColumnChartViewData(repository.getVisits()))
    }

    private fun mapVisitToColumnChartViewData(visit: List<Visit>): List<ColumnChartView.Data> =
        visit.map {
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