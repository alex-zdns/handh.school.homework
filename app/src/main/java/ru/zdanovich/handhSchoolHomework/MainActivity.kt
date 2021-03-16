package ru.zdanovich.handhSchoolHomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.zdanovich.handhSchoolHomework.task2.TaskTwoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.ma_button_task_1).setOnClickListener {
            val intent = Intent(this, TaskOneActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.ma_button_task_2).setOnClickListener {
            val intent = Intent(this, TaskTwoActivity::class.java)
            startActivity(intent)
        }
    }
}