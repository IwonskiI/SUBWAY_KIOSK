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
import com.example.subway_kiosk.databinding.OrderCompleteBinding
import com.example.subway_kiosk.databinding.PaymentBinding
import com.example.subway_kiosk.order.FastOrder
import com.example.subway_kiosk.order.MeatSelect
import com.example.subway_kiosk.util.Sandwich
import com.example.subway_kiosk.util.Stock
import com.example.subway_kiosk.util.cartAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class OrderComplete : AppCompatActivity()
{
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("order_num")
    lateinit var order_num: Stock

    var shopping_cart = arrayListOf<Sandwich?>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val binding = OrderCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val home = findViewById<Button>(R.id.home)

        home.setOnClickListener {
            val nextIntent = Intent(this@OrderComplete, TakeOutActivity::class.java)
            nextIntent.putExtra("order",order_num.num + 1)
            startActivity(nextIntent)
        }

        if(intent.hasExtra("total")){
            binding.total.text = "총 금액: " + intent.getIntExtra("total",0).toString()
        }
        stockRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                order_num = snapshot.getValue(Stock::class.java)!!
                binding.orderNum.text = "주문 번호: "+ order_num.num.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

    }
}