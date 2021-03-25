package ru.zdanovich.handhSchoolHomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.zdanovich.handhSchoolHomework.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {
    private var _binding: ActivityUserInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}