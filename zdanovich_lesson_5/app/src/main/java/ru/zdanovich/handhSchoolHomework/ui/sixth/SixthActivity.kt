package ru.zdanovich.handhSchoolHomework.ui.sixth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.databinding.ActivitySixthBinding
import ru.zdanovich.handhSchoolHomework.domain.repositories.AdvertRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.AdvertRepositoryMock

class SixthActivity : AppCompatActivity() {
    private var _binding: ActivitySixthBinding? = null
    private val binding get() = _binding!!

    private val repository: AdvertRepository = AdvertRepositoryMock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySixthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activitySixthRecyclerview.adapter = AdvertAdapter(repository.getAdverts())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}