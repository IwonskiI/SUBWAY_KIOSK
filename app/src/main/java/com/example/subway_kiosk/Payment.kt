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
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.subway_kiosk.databinding.ActivityMainBinding
import com.example.subway_kiosk.databinding.PaymentBinding
import com.example.subway_kiosk.order.FastOrder
import com.example.subway_kiosk.order.MeatSelect
import com.example.subway_kiosk.util.Sandwich
import com.example.subway_kiosk.util.cartAdapter

class Payment : AppCompatActivity()
{
    var shopping_cart = arrayListOf<Sandwich?>()
    var total: Int = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val binding = PaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cash_btn = findViewById<Button>(R.id.pay_cash)
        val card_btn = findViewById<Button>(R.id.pay_card)
        val pay_btn = findViewById<Button>(R.id.pay_pay)

        cash_btn.setOnClickListener {
            Toast.makeText(this@Payment, "현금 결제가 완료되었습니다.", Toast.LENGTH_LONG).show()
            val nextIntent = Intent(this@Payment, OrderComplete::class.java)
            nextIntent.putExtra("total",total)
            startActivity(nextIntent)
        }
        card_btn.setOnClickListener {
            Toast.makeText(this@Payment, "카드 결제가 완료되었습니다.", Toast.LENGTH_LONG).show()
            val nextIntent = Intent(this@Payment, OrderComplete::class.java)
            nextIntent.putExtra("total",total)
            startActivity(nextIntent)
        }
        pay_btn.setOnClickListener {
            Toast.makeText(this@Payment, "페이 결제가 완료되었습니다.", Toast.LENGTH_LONG).show()
            val nextIntent = Intent(this@Payment, OrderComplete::class.java)
            nextIntent.putExtra("total", total)
            startActivity(nextIntent)
        }

        if(intent.hasExtra("shoppingCart")){
            shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!
            for(item in shopping_cart){
                total += item!!.getPrice()
            }
            binding.total.text = "총 금액: " + total
            binding.shoppingCart.layoutManager= LinearLayoutManager(this)
            binding.shoppingCart.adapter= cartAdapter(shopping_cart)
            binding.shoppingCart.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        }

    }
}