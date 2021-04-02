package ru.zdanovich.handhSchoolHomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.zdanovich.handhSchoolHomework.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private var _binding: ActivityFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityFirstButtonGoTo4.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra(FourthActivity.TIME_KEY, System.currentTimeMillis())
            startActivity(intent)
        }
    }
}