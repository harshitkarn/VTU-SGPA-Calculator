package com.example.vtusgpacalculator

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        var dialog: Dialog = Dialog(this)
        var btn = findViewById<TextView>(R.id.calc_SGPA)
        btn.setOnClickListener {
            try {
                dialog.setContentView(R.layout.select_dialog)
                var buttons= listOf<TextView>(dialog.findViewById(R.id.sem1),dialog.findViewById(R.id.sem2),dialog.findViewById(R.id.sem3),dialog.findViewById(R.id.sem4),dialog.findViewById(R.id.sem5),dialog.findViewById(R.id.sem6),dialog.findViewById(R.id.sem7),dialog.findViewById(R.id.sem8))
                for(i in 1..8){
                    buttons[i-1].setOnClickListener{
                        val message = "$i"
                        val intent = Intent(this, SGPAPage::class.java).apply {
                            putExtra(EXTRA_MESSAGE, message)
                        }
                        startActivity(intent)
                    }
                }
                val closeButton = dialog.findViewById<ImageView>(R.id.dialogclsa)
                closeButton.setOnClickListener{
                    dialog.dismiss()
                }
                dialog.show()
            }
            catch (e: Exception){
                Toast.makeText(applicationContext,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        val calcCgpa = findViewById<TextView>(R.id.calc_CGPA)
        calcCgpa.setOnClickListener {
            try{
                val intentCGPA = Intent(this, CGPAPage::class.java)
                startActivity(intentCGPA)
            }
            catch (e: Exception){
                Toast.makeText(applicationContext,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}

