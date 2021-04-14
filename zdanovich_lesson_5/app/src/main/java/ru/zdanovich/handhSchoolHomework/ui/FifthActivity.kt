package ru.zdanovich.handhSchoolHomework.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.databinding.ActivityFifthBinding
import ru.zdanovich.handhSchoolHomework.domain.models.Data

class FifthActivity : AppCompatActivity() {
    private var _binding: ActivityFifthBinding? = null
    private val binding get() = _binding!!

    private var data: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            data = savedInstanceState.getParcelable(DATA) as Data?
            data?.let {
                binding.activityFifthTextView.text = data?.value
            }
        }

        binding.activityFifthDeliverResultButton.setOnClickListener {
            val message = binding.activityFifthEditText.text.toString()
            sendMessage(message)
        }

        binding.activityFifthSaveButton.setOnClickListener {
            val text = binding.activityFifthEditText.text.toString()
            data = Data(text)
            binding.activityFifthTextView.text = text
        }
    }

    private fun sendMessage(message: String) {
        val data = Intent()
        data.putExtra(ThirdActivity.MESSAGE, message)
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        data?.let {
            outState.putParcelable(DATA, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val DATA = "DATA"
    }
}