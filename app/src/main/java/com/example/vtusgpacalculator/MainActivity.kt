package com.example.vtusgpacalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        var buttons= listOf<TextView>(findViewById(R.id.button1),findViewById(R.id.button2),findViewById(R.id.button3),findViewById(R.id.button4),findViewById(R.id.button5),findViewById(R.id.button6),findViewById(R.id.button7),findViewById(R.id.button8))
        for(i in 1..8){
            buttons[i-1].setOnClickListener{
                val message = "$i"
                val intent = Intent(this, SGPAPage::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                }
                startActivity(intent)
            }
        }
    }
}