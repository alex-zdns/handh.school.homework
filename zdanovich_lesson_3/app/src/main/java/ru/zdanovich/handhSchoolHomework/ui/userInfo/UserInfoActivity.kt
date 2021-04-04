package ru.zdanovich.handhSchoolHomework.ui.userInfo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.ActivityUserInfoBinding
import ru.zdanovich.handhSchoolHomework.domain.models.User
import ru.zdanovich.handhSchoolHomework.domain.repositories.UserRepository
import ru.zdanovich.handhSchoolHomework.domain.repositories.UserRepositoryMock


class UserInfoActivity : AppCompatActivity() {
    private var _binding: ActivityUserInfoBinding? = null
    private val binding get() = _binding!!
    private val repository: UserRepository = UserRepositoryMock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolBar()
        setupView(repository.getUserById(1))
        setupButton()
    }

    private fun setupView(user: User) {
        binding.auiCardNumber.text =
            getString(R.string.aui_card_number_and_position, user.id, user.position)

        binding.auiName.title.text = getString(R.string.aui_name_label)
        binding.auiName.field.text = user.name

        binding.auiSurname.title.text = getString(R.string.aui_surname_label)
        binding.auiSurname.field.text = user.surname

        binding.auiEmail.title.text = getString(R.string.aui_email_label)
        binding.auiEmail.field.text = user.email

        binding.auiLogin.title.text = getString(R.string.aui_login_label)
        binding.auiLogin.field.text = user.login

        binding.auiRegion.title.text = getString(R.string.aui_region_label)
        binding.auiRegion.field.text = user.region
    }

    private fun setupButton() {
        binding.auiLogout.setOnClickListener {
            showToast(getString(R.string.logout_message))
        }

        binding.auiRegionEdit.setOnClickListener {
            showToast(getString(R.string.pencil))
        }
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
            showToast(getString(R.string.pencil))
            return true
        }

        return false
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}