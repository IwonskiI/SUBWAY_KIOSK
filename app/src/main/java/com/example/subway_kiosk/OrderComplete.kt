package com.example.subway_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.subway_kiosk.databinding.ActivityMainBinding
import com.example.subway_kiosk.order.FastOrder
import com.example.subway_kiosk.order.MeatSelect
import com.example.subway_kiosk.util.Sandwich
import com.example.subway_kiosk.util.cartAdapter

class OrderComplete : AppCompatActivity()
{
    var shopping_cart = arrayListOf<Sandwich?>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)

        val home = findViewById<Button>(R.id.pay_cash)

        home.setOnClickListener {
            val nextIntent = Intent(this@OrderComplete, TakeOutActivity::class.java)
            startActivity(nextIntent)
        }

    }
}