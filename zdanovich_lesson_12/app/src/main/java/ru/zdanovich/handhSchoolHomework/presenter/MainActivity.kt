package ru.zdanovich.handhSchoolHomework.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, BridgesListFragment())
                .commit()
        }
    }
}