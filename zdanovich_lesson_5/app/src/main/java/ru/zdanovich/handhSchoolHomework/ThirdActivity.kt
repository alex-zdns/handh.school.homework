package ru.zdanovich.handhSchoolHomework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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

        binding.activityThirdButtonGoTo5.setOnClickListener {
            val intent = Intent(this, FifthActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != REQUEST_CODE) {
            return
        }

        if (resultCode == RESULT_OK) {
            val message = data?.getStringExtra(MESSAGE)
            message?.let {
                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val REQUEST_CODE = 1
        const val MESSAGE = "MESSAGE"
    }
}