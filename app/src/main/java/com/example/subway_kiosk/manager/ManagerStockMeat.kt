package com.example.subway_kiosk.manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.subway_kiosk.R
import com.example.subway_kiosk.order.MenuSelect
import com.example.subway_kiosk.order.VegeSelect
import com.example.subway_kiosk.util.Sandwich
import kotlin.system.exitProcess

class ManagerStockMeat : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_stock_meat)

        val meatBtn = findViewById<Button>(R.id.manage_bread)
        meatBtn.setOnClickListener {
            val nextIntent = Intent(this@ManagerStockMeat, ManagerStockBread::class.java)
            startActivity(nextIntent)
        }
    }



}