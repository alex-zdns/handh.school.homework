package ru.zdanovich.handhSchoolHomework.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgeListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, BridgeListFragment())
                .commit()
        }
    }
}