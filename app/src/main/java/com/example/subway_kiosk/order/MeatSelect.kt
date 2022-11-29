package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.subway_kiosk.R

class MeatSelect : AppCompatActivity()
{
    var pressedBtnList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meat_select)

        val nextBtn = findViewById<Button>(R.id.meatSelect_toNext)
        nextBtn.setOnClickListener {
            if (pressedBtnList.size == 0)
            {
                Toast.makeText(this@MeatSelect, "하나 이상의 재료를 선택해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val nextIntent = Intent(this@MeatSelect, MenuSelect::class.java)
            nextIntent.putExtra("selectedMeat", pressedBtnList)
            startActivity(nextIntent)
        }
    }

    fun btnClick(v: View)
    {
        val selectedBtn = findViewById<Button>(v.id)
        if (pressedBtnList.contains(selectedBtn.id))
        {
            selectedBtn.setBackgroundResource(R.drawable.corner_button3)
            selectedBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            pressedBtnList.remove(selectedBtn.id)
            return
        }

        if (pressedBtnList.size == 2)
        {
            val btn1 = findViewById<Button>(pressedBtnList[0])
            btn1.setBackgroundResource(R.drawable.corner_button3)
            btn1.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            pressedBtnList.removeFirst()
        }

        selectedBtn.setBackgroundResource(R.drawable.corner_button3_selected)
        selectedBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        pressedBtnList.add(selectedBtn.id)
    }
}