package ru.zdanovich.handhSchoolHomework.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.zdanovich.handhSchoolHomework.R
import ru.zdanovich.handhSchoolHomework.databinding.ActivityMainBinding
import ru.zdanovich.handhSchoolHomework.ui.second.SecondFragment
import ru.zdanovich.handhSchoolHomework.ui.third.ImageFragment
import ru.zdanovich.handhSchoolHomework.ui.third.ThirdFragment

class MainActivity : AppCompatActivity(), ImageFragment.OnImageClicked {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == binding.bottomNavigation.selectedItemId) {
                return@OnNavigationItemSelectedListener true
            }

            when (menuItem.itemId) {
                R.id.item_one -> replaceFragment(FirstFragment())
                R.id.item_two -> replaceFragment(SecondFragment())
                R.id.item_three -> replaceFragment(ThirdFragment())
                else -> return@OnNavigationItemSelectedListener false
            }

            return@OnNavigationItemSelectedListener true
        })

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.ma_container, FirstFragment())
                .commit()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.ma_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onImageClick(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}