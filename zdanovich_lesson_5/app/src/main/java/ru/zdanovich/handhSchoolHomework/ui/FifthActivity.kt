package ru.zdanovich.handhSchoolHomework.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.databinding.ActivityFifthBinding


class FifthActivity : AppCompatActivity() {
    private var _binding: ActivityFifthBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityFifthDeliverResultButton.setOnClickListener {
            val message = binding.activityFifthEditText.text.toString()
            sendMessage(message)
        }
    }

    private fun sendMessage(message: String) {
        val data = Intent()
        data.putExtra(ThirdActivity.MESSAGE, message)
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}