package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.subway_kiosk.R
import com.example.subway_kiosk.util.Sandwich

class tester : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testText = findViewById<TextView>(R.id.testView)
        if(intent.hasExtra("selectedSandwich"))
            testText.setText(intent.getParcelableExtra<Sandwich>("selectedSandwich")?.toString())

        var prevBtn = findViewById<Button>(R.id.testBtn)
        prevBtn.setOnClickListener{
            finish()
        }
    }
}