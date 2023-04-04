package com.example.ageinminutesapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDateOfBirth.setOnClickListener {
            selectDate()
        }
    }

    private fun selectDate() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val dp = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
            view, sYear, sMonth, sDayOfMonth ->
            Toast.makeText(this,"$sDayOfMonth/${sMonth+1}/$sYear",Toast.LENGTH_SHORT).show()
            val dateBirth = "$sDayOfMonth/${sMonth+1}/$sYear"
            txtDateOfBirth.text = dateBirth

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val dateOfBirth = sdf.parse(dateBirth)
            dateBirth?.let {
                val dateOfBirthInMinutes = dateOfBirth.time/60000

                // thoi gian hien tai
                val currDate =sdf.parse(sdf.format(System.currentTimeMillis()))
                currDate?.let {
                    val currDateInMinutes = currDate.time/60000

                    // tinh toan = ngay hien tai - ngay sinh
                    val diff = currDateInMinutes - dateOfBirthInMinutes
                    txtMinutesToLive.text = diff.toString()
                }
            }
        },year,month,day)
        dp.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dp.show()
    }
}