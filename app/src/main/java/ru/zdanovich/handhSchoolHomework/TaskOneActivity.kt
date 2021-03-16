package ru.zdanovich.handhSchoolHomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class TaskOneActivity : AppCompatActivity() {
    private val names = TreeSet<String>()
    private lateinit var editText: EditText
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_1)

        editText = findViewById(R.id.task_1_edit_text)
        textView = findViewById(R.id.task_1_text_view_names)

        findViewById<Button>(R.id.task_1_button_save).setOnClickListener {
            val name = editText.text.toString()

            if (name.isNotEmpty()) {
                names.add(name)
                editText.setText("")
            } else {
                Toast.makeText(
                    this@TaskOneActivity,
                    getString(R.string.task_1_error_message_empty_field),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        findViewById<Button>(R.id.task_1_button_show).setOnClickListener {
            textView.text = names.joinToString(separator = "\n")
        }
    }
}