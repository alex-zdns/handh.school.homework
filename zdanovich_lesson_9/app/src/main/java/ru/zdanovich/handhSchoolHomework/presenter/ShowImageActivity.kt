package ru.zdanovich.handhSchoolHomework.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.databinding.ActivityShowImageBinding

class ShowImageActivity : AppCompatActivity() {
    private var _binding: ActivityShowImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityShowImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent?.let(::handleIntent)
    }

    private fun handleIntent(intent: Intent) {
        intent.extras?.getParcelable<Uri>(KEY_IMAGE_URI)?.let { uri ->
            binding.siaImage.setImageURI(uri)
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val KEY_IMAGE_URI = "image_uri"
    }
}