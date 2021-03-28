package ru.zdanovich.handhSchoolHomework

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
        setSupportActionBar(binding.maToolbar)
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
            R.id.action_home -> showHomeToast()
            R.id.action_info -> showInfoDialog()
            else -> return false
        }

        return true
    }

    private fun showInfoDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.ma_info_dialog_title))
                .setMessage(getString(R.string.ma_info_dialog_message))
                .setIcon(R.drawable.ic_info)
                .setPositiveButton(getString(R.string.ma_info_dialog_positive_button)) { dialog, _ ->
                    dialog.cancel()
                }
                .create()
                .show()
    }

    private fun showHomeToast() {
        Toast.makeText(this, getString(R.string.ma_home_button_message), Toast.LENGTH_LONG)
                .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}