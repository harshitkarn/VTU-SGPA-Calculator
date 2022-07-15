package com.example.vtusgpacalculator

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import kotlin.math.roundToInt

class CGPAPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cgpapage)
        var dialog: Dialog = Dialog(this)
        val credits = arrayOf(20,20,24,24,25,24,20,18)
        val sgpaList = arrayOf<EditText>(findViewById(R.id.sem1_cgpa),findViewById(R.id.sem2_cgpa),findViewById(R.id.sem3_cgpa),findViewById(R.id.sem4_cgpa),findViewById(R.id.sem5_cgpa),findViewById(R.id.sem6_cgpa),findViewById(R.id.sem7_cgpa),findViewById(R.id.sem8_cgpa))
        val btnClear = findViewById<Button>(R.id.buttonClear_cgpa)
        btnClear.setOnClickListener{
            for(i in 0..7)
                sgpaList[i].setText("")
        }

        val calculateButton = findViewById<Button>(R.id.button_cgpa)
        calculateButton.setOnClickListener {
            var sum = 0
            var mult = 0.0
            var available = listOf<Int>()
            var availableSGPA = listOf<Double?>()
            for (i in 0..7){
                val curr = sgpaList[i]
                val currStr = curr.text.toString().trim()
                val currDouble = currStr.toDoubleOrNull()
                availableSGPA += currDouble
                if(currStr != "")available += (i)
            }
            for (i in available.indices){
                if(i!=available[i]){
                    Toast.makeText(applicationContext, "Enter Sem ${i+1} marks", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }
            val len = available.size-1
            for (i in 0..len){
                if(availableSGPA[i] == null || availableSGPA[i]!! <0 || availableSGPA[i]!! >10){
                    Toast.makeText(applicationContext, "Enter valid SGPA(0-10)", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                sum += credits[i]
                mult += (credits[i]* availableSGPA[i]!!)
            }
            val result = mult/sum
            val roundoff = (result * 100.0).roundToInt() / 100.0
            try {
                dialog.setContentView(R.layout.dialogbox)
                val contentOfDialog = dialog.findViewById<TextView>(R.id.textView15)
                val closeButton = dialog.findViewById<Button>(R.id.button9)
                val contentString = "Your CGPA is $roundoff"
                contentOfDialog.text = contentString
                closeButton.setOnClickListener{
                    dialog.dismiss()
                }
                dialog.show()
            }
            catch (e: Exception){
                Toast.makeText(applicationContext,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}