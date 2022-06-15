package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate = findViewById<Button>(R.id.btnSelectDate)
        btnSelectDate.setOnClickListener {
            clickDatePicker()
        //  Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_LONG).show()
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, R.style.DialogTheme, {
                _, selectedYear, selectedMonth, selectedDay ->

            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            Toast.makeText(this@MainActivity, "The chosen date is $selectedDate", Toast.LENGTH_LONG).show()

            val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
            tvSelectedDate.text = selectedDate

            val theDate = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH).parse(selectedDate)


            if(theDate != null){
                val diffMillis: Long = myCalendar.timeInMillis - theDate.time
                val diffMinutes = (diffMillis / 1000) / 60

                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                tvSelectedDateInMinutes.text = "$diffMinutes"
            }

        }, year, month, day).show()
    }
}