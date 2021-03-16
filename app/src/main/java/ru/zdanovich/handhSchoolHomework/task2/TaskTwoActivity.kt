package ru.zdanovich.handhSchoolHomework.task2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.R
import java.lang.Exception
import java.lang.NumberFormatException

class TaskTwoActivity : AppCompatActivity() {
    private val students = ArrayList<Student>()
    private lateinit var editText: EditText

    private lateinit var studentId: TextView
    private lateinit var studentName: TextView
    private lateinit var studentSurname: TextView
    private lateinit var studentGrade: TextView
    private lateinit var studentBirthdayYear: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_2)

        editText = findViewById(R.id.task_2_edit_text)

        studentId = findViewById(R.id.task_2_id)
        studentName = findViewById(R.id.task_2_name)
        studentSurname = findViewById(R.id.task_2_surname)
        studentGrade = findViewById(R.id.task_2_grade)
        studentBirthdayYear = findViewById(R.id.task_2_birthday_year)

        findViewById<Button>(R.id.task_2_save_and_show_button).setOnClickListener {
            val studentsFields = editText.text.toString().split(" ")

            if (studentsFields.size == 4) {
                addStudentAndShowExceptionHandler(studentsFields)
            } else {
                showErrorMessage(getString(R.string.task_2_incorrect_input_error_message))
            }
        }
    }

    private fun addStudentAndShowExceptionHandler(studentsFields: List<String>) {
        try {
            addStudentAndShow(studentsFields)
        } catch (e: NumberFormatException) {
            showErrorMessage(getString(R.string.task_2_incorrect_input_birthday_year_error_message))
        } catch (e: Exception) {
            Log.e(this::class.simpleName, e.message ?: "")
            showErrorMessage(getString(R.string.task_2_other_error_message))
        }
    }

    private fun addStudentAndShow(studentsFields: List<String>) {
        students.add(
            Student(
                id = students.size + 1,
                name = studentsFields[0],
                surname = studentsFields[1],
                grade = studentsFields[2],
                birthdayYear = studentsFields[3].toInt()
            )
        )

        editText.setText("")

        val ids: String = students.map { it.id }.joinToString(separator = "\n")
        val names: String = students.joinToString(separator = "\n") { it.name }
        val surnames: String = students.joinToString(separator = "\n") { it.surname }
        val grades: String = students.joinToString(separator = "\n") { it.grade }
        val years: String = students.map { it.birthdayYear }.joinToString(separator = "\n")

        studentId.text = ids
        studentName.text = names
        studentSurname.text = surnames
        studentGrade.text = grades
        studentBirthdayYear.text = years
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(
            this@TaskTwoActivity,
            message,
            Toast.LENGTH_LONG
        )
            .show()
    }
}