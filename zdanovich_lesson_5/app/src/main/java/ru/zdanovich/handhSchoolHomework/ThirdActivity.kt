package ru.zdanovich.handhSchoolHomework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private var _binding: ActivityThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityThirdButtonGoTo1.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}