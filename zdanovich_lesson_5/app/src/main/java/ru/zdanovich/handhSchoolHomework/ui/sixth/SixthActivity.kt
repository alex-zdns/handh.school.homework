package ru.zdanovich.handhSchoolHomework.ui.sixth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.ActivitySixthBinding
import ru.zdanovich.handhSchoolHomework.domain.repositories.AdvertRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.AdvertRepositoryMock

class SixthActivity : AppCompatActivity(), AdvertAdapter.OnRecyclerItemClicked {
    private var _binding: ActivitySixthBinding? = null
    private val binding get() = _binding!!
    private val repository: AdvertRepository = AdvertRepositoryMock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySixthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activitySixthRecyclerview.adapter = AdvertAdapter(repository.getAdverts(), this)

        binding.activitySixthToolbarAddAll.setOnClickListener {
            showToast(getString(R.string.ad_all))
        }

        binding.activitySixthOfferAd.setOnClickListener {
            showToast(getString(R.string.activity_sixth_offer_ad))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(title: String) {
        showToast(title)
    }
}