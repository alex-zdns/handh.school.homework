package ru.zdanovich.handhSchoolHomework.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.databinding.ActivityFourthBinding
import java.text.SimpleDateFormat
import java.util.*

class FourthActivity : AppCompatActivity() {
    private var _binding: ActivityFourthBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNewIntent(intent)

        binding.activityFourthButtonGoTo4Again.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra(TIME_KEY, System.currentTimeMillis())
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    private fun formatAndShowDate(timeInMilliseconds: Long) {
        val date = Date(timeInMilliseconds)
        binding.activityFourthDateAndTime.text = formatDate.format(date)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val time: Long? = intent?.extras?.getLong(TIME_KEY)
        time?.let { formatAndShowDate(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TIME_KEY = "timeInMilliseconds"
        private const val DATE_PATTERN = "dd.MM.yyyy HH:mm:ss"
        val formatDate by lazy  {
            SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        }
    }
}