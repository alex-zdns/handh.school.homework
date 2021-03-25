package ru.zdanovich.handhSchoolHomework

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.databinding.ActivityUserInfoBinding


class UserInfoActivity : AppCompatActivity() {
    private var _binding: ActivityUserInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolBar()
        setupView(User.getSampleUser())
    }

    private fun setupView(user: User) {
        binding.auiCardNumber.text =
            resources.getString(R.string.aui_card_number_and_position, user.id, user.position)
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
        menuInflater.inflate(R.menu.user_info_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_edit) {
            Toast.makeText(
                this,
                getString(R.string.pencil),
                Toast.LENGTH_LONG
            ).show()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}