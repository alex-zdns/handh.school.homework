package ru.zdanovich.handhSchoolHomework.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.zdanovich.handhSchoolHomework.domain.models.Bridge
import ru.zdanovich.handhSchoolHomework.presenter.bridgeInfo.BridgesInfoFragment
import ru.zdanovich.handhSchoolHomework.presenter.bridgeList.BridgesListFragment

class MainActivity : AppCompatActivity(), BridgesListFragment.BridgesListClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, BridgesListFragment())
                .commit()
        }
    }

    override fun openBridgeInfoFragment(bridge: Bridge) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(android.R.id.content, BridgesInfoFragment.newInstance(bridge))
            .commit()
    }
}