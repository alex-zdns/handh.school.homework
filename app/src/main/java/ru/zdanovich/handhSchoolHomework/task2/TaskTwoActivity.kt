package ru.zdanovich.handhSchoolHomework.task2

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.zdanovich.handhSchoolHomework.R

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

        editText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event?.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    val studentsFields = editText.text.toString().trim().split(delimiter)

                    if (studentsFields.size != STUDENT_FIELDS_COUNT) {
                        showErrorMessage(getString(R.string.task_2_incorrect_input_error_message))
                        return true
                    }

                    val isSuccess = addStudentAndShowExceptionHandler(studentsFields)

                    if (isSuccess) {
                        editText.clearFocus()
                        editText.isCursorVisible = false
                        editText.setText("")
                        hideKeyboard()
                    }

                    return true
                }

                return false
            }
        })
    }

    private fun addStudentAndShowExceptionHandler(studentsFields: List<String>): Boolean =
        try {
            addStudentAndShow(studentsFields)
            true
        } catch (e: NumberFormatException) {
            showErrorMessage(getString(R.string.task_2_incorrect_input_birthday_year_error_message))
            false
        } catch (e: Exception) {
            Log.e(this::class.simpleName, e.message ?: "")
            showErrorMessage(getString(R.string.task_2_other_error_message))
            false
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

        val ids: String = students.joinToString { it.id.toString() }
        val names: String = students.joinToString { it.name }
        val surnames: String = students.joinToString { it.surname }
        val grades: String = students.joinToString { it.grade }
        val years: String = students.joinToString { it.birthdayYear.toString() }

        studentId.text = ids
        studentName.text = names
        studentSurname.text = surnames
        studentGrade.text = grades
        studentBirthdayYear.text = years
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        )
            .show()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }


    companion object {
        val delimiter = "\\s+".toRegex()
        const val STUDENT_FIELDS_COUNT = 4
    }
}