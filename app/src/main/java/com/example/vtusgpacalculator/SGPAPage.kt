package com.example.vtusgpacalculator

import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.TypedValue
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import java.lang.Exception

class SGPAPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sgpapage)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val semester = intent.getStringExtra(EXTRA_MESSAGE)?.toInt()
        val titleSem = findViewById<TextView>(R.id.textView13)
        titleSem.text = "Semester $semester"
        var dialog:Dialog = Dialog(this)
        val sem1 = mapOf("18***11" to 4, "18***12" to 4, "18***13" to 3, "18***14" to 3, "18**15" to 3, "18***16" to 1, "18***17" to 1, "18***18" to 1)
        val sem2 = mapOf("18***21" to 4, "18***22" to 4, "18***23" to 3, "18***24" to 3, "18**25" to 3, "18***26" to 1, "18***27" to 1, "18***28" to 1)
        val sem3 = mapOf("18***31" to 3, "18**32" to 4, "18**33" to 3, "18**34" to 3, "18**35" to 3, "18**36" to 3, "18***37" to 2, "18***38" to 2, "18***39" to 1)
        val sem4 = mapOf("18***41" to 3, "18**42" to 4, "18**43" to 3, "18**44" to 3, "18**45" to 3, "18**46" to 3, "18***47" to 2, "18***48" to 2, "18***49" to 1)
        val sem5 = mapOf("18**51" to 3, "18**52" to 4, "18**53" to 4, "18**54" to 3, "18**55" to 3, "18**56" to 3, "18***57" to 2, "18***58" to 2, "18***59" to 1)
        val sem6 = mapOf("18**61" to 4, "18**62" to 4, "18**63" to 4, "18**64*" to 3, "18**65*" to 3, "18***66" to 2, "18***67" to 2, "18****68" to 2)
        val sem7 = mapOf("18**71" to 4, "18**72" to 4, "18**73*" to 3, "18**74*" to 3, "18**75*" to 3, "18***76" to 2, "18***77" to 1)
        val sem8 = mapOf("18***81" to 3, "18**82*" to 3, "18***83" to 8, "18***84" to 1, "18***85" to 3)
        val currSem=
            when(semester){
                1 -> sem1
                2 -> sem2
                3 -> sem3
                4 -> sem4
                5 -> sem5
                6 -> sem6
                7 -> sem7
                else -> sem8
            }
        val textViews = listOf<TextView>(findViewById(R.id.textView4),findViewById(R.id.textView5),findViewById(R.id.textView6),findViewById(R.id.textView7),findViewById(R.id.textView8),findViewById(R.id.textView9),findViewById(R.id.textView10),findViewById(R.id.textView11),findViewById(R.id.textView12))
        val horizontalLayouts = listOf<LinearLayout>(findViewById(R.id.horiz1),findViewById(R.id.horiz2),findViewById(R.id.horiz3),findViewById(R.id.horiz4),findViewById(R.id.horiz5),findViewById(R.id.horiz6),findViewById(R.id.horiz7),findViewById(R.id.horiz8),findViewById(R.id.horiz9))
        val editTexts = listOf<EditText>(findViewById(R.id.editTextNumber1),findViewById(R.id.editTextNumber2),findViewById(R.id.editTextNumber3),findViewById(R.id.editTextNumber4),findViewById(R.id.editTextNumber5),findViewById(R.id.editTextNumber6),findViewById(R.id.editTextNumber7),findViewById(R.id.editTextNumber8),findViewById(R.id.editTextNumber9))
        val marks = mutableListOf<Int>()
        var count=0
        val subjectNo = currSem.size - 1
        val creditsList = mutableListOf<Int>()
        for ((key,value) in currSem) {
            textViews[count].text="$key"
            creditsList.add(value)
            count++
        }
        val layout = findViewById<LinearLayout>(R.id.linLay)

        for(i in count..8){
            layout.removeView(horizontalLayouts[i])
        }

        val btnClear = findViewById<Button>(R.id.buttonClear)
        btnClear.setOnClickListener{
            for(i in 0..subjectNo)
                editTexts[i].setText("")
        }

        val calculateButton = findViewById<Button>(R.id.button)

        calculateButton.setOnClickListener{
            var sum=0
            var creditSum=0
            for(j in 0..subjectNo) {
                val i = editTexts[j]
                var k = i.text.toString().toIntOrNull()
                if (k == null){
                    Toast.makeText(applicationContext, "Enter Valid marks", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                else if (k < 0 || k > 100){
                    Toast.makeText(applicationContext, "Enter marks between 0 & 100", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else{
                    sum+=creditsList[j]*calculateGP(k)
                    creditSum+=creditsList[j]
                }
            }
            var cgpa = sum/creditSum.toDouble()
            try {
                dialog.setContentView(R.layout.dialogbox)
                val contentOfDialog = dialog.findViewById<TextView>(R.id.textView15)
                val closeButton = dialog.findViewById<Button>(R.id.button9)
                val contentString = "Your SGPA is "+String.format("%.2f", cgpa)
                contentOfDialog.text = contentString
                closeButton.setOnClickListener{
                    dialog.dismiss()
                }
                dialog.show()
            }
            catch (e:Exception){
                Toast.makeText(applicationContext,e.toString(),Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun calculateGP(a:Int):Int=
        when{
                a>=90 -> 10
                a>=80 -> 9
                a>=70 -> 8
                a>=60 -> 7
                a>=45 -> 6
                a>=40 -> 4
                else -> 0
        }
}