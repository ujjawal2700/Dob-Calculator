package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var displayMinute : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker : Button = findViewById(R.id.buttonDatePicker)
        tvSelectedDate = findViewById(R.id.displayDate)
        displayMinute = findViewById(R.id.displayMinute)
        datePicker.setOnClickListener{
            clickdatePicker()
        }
    }

    private fun clickdatePicker(){

        val myCal = Calendar.getInstance()
        val year = myCal.get(Calendar.YEAR)
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Button DatePicker is Pressed", Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate.let {
                    val selectedDateInMinut = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinuts = currentDate.time / 60000

                        val differenceInMinut = currentDateInMinuts - selectedDateInMinut

                        displayMinute?.text = differenceInMinut.toString()
                    }

                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}