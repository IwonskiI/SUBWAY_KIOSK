package com.example.subway_kiosk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.manager.ManagerSell
import com.example.subway_kiosk.manager.ManagerStockMeat

class ManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)

        val editMenuBtn = findViewById<Button>(R.id.edit_menu)
        val stockStatusBtn = findViewById<Button>(R.id.stock_status)
        val sellStatusBtn = findViewById<Button>(R.id.sell_status)


        //listener
        editMenuBtn.setOnClickListener {
            val intent = Intent(this, ManagerSell::class.java)
            startActivity(intent)
        }

        stockStatusBtn.setOnClickListener {
            val intent = Intent(this, ManagerStockMeat::class.java)
            startActivity(intent)
        }

        sellStatusBtn.setOnClickListener {
            val intent = Intent(this, ManagerSell::class.java)
            startActivity(intent)
        }

    }

}