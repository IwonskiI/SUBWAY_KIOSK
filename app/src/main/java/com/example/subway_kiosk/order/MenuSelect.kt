package com.example.subway_kiosk.order

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.R

class MenuSelect : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra("selectedMeat"))
        {
            val selected = intent.getIntegerArrayListExtra("selectedMeat")
            val testView = findViewById<TextView>(R.id.testView)
            if (selected != null)
            {
                val dialogView = View.inflate(this@MenuSelect, R.layout.meat_select, null)
                testView.setText(buildString {
                    append("총 ${selected.size} 개\n")
                    if (selected.size == 2)
                    {
                        append("{${dialogView.findViewById<Button>(selected.get(1)).text}}")
                        append("과 ")
                    }
                    if (selected.size > 0)
                    {
                        append("{${dialogView.findViewById<Button>(selected.get(0)).text}}")
                        append("선택하셨습니다")
                    }
                })
            }
        }
    }
}