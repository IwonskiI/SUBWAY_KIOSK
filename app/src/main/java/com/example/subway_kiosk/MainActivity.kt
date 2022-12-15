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

class MainActivity : AppCompatActivity()
{
    var shopping_cart = arrayListOf<Sandwich?>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val regularorder = findViewById<Button>(R.id.regular_order)
        val fastorder = findViewById<Button>(R.id.fast_order)
        val shop = findViewById<RecyclerView>(R.id.shopping_cart)
        val pay_btn = findViewById<Button>(R.id.payment)
        val home_btn = findViewById<Button>(R.id.home)

        regularorder.setOnClickListener{
            val nextIntent = Intent(this@MainActivity, MeatSelect::class.java)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }

        fastorder.setOnClickListener{
            val nextIntent = Intent(this@MainActivity, FastOrder::class.java)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }

        pay_btn.setOnClickListener {
            val nextIntent = Intent(this@MainActivity, Payment::class.java)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }

        home_btn.setOnClickListener {
            finish()
        }


        if(intent.hasExtra("selectedSandwich")){
            shop.visibility = View.VISIBLE
            pay_btn.visibility = View.VISIBLE
            shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!
            shopping_cart.add(intent.getParcelableExtra<Sandwich>("selectedSandwich"))
            for (item in shopping_cart) {
                //shop.setText(shop.text.toString() + item?.getSandwichName() + "  +  " + item?.getCookieName() + '\n')
            }
            Log.d("cart",shopping_cart.toString())
            binding.shoppingCart.layoutManager= LinearLayoutManager(this)
            binding.shoppingCart.adapter= cartAdapter(shopping_cart)
            binding.shoppingCart.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        }
        else{
            shop.visibility = View.INVISIBLE
            pay_btn.visibility = View.INVISIBLE
        }
    }
}