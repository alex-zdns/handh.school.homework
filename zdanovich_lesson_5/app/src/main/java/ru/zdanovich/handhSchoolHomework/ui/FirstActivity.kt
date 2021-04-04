package ru.zdanovich.handhSchoolHomework.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.zdanovich.handhSchoolHomework.databinding.ActivityFirstBinding
import ru.zdanovich.handhSchoolHomework.ui.sixth.SixthActivity

class FirstActivity : AppCompatActivity() {
    private var _binding: ActivityFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityFirstButtonGoTo2.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.activityFirstButtonGoTo4.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra(FourthActivity.TIME_KEY, System.currentTimeMillis())
            startActivity(intent)
        }

        binding.activityFirstButtonGoTo6.setOnClickListener {
            val intent = Intent(this, SixthActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}