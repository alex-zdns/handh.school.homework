package ru.zdanovich.handhSchoolHomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import ru.zdanovich.handhSchoolHomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolBar()
    }

    private fun setupToolBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home ->
                Toast.makeText(this, getString(R.string.ma_home_button_message), Toast.LENGTH_LONG)
                    .show()

            R.id.action_info -> Toast.makeText(this, "Инфо", Toast.LENGTH_LONG).show()
            else -> return false
        }

        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}