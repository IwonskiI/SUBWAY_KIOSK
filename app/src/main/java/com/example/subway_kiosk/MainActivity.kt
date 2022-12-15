package com.example.subway_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.subway_kiosk.databinding.ActivityMainBinding
import com.example.subway_kiosk.order.MeatSelect
import com.example.subway_kiosk.util.Sandwich

class MainActivity : AppCompatActivity()
{
    var shopping_cart = arrayListOf<Sandwich?>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val regularorder = findViewById<Button>(R.id.regular_order)
        val shop = findViewById<TextView>(R.id.shopping_cart)
        val pay_btn = findViewById<Button>(R.id.payment)

        regularorder.setOnClickListener{
            val nextIntent = Intent(this@MainActivity, MeatSelect::class.java)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }


        if(intent.hasExtra("selectedSandwich")){
            shop.visibility = View.VISIBLE
            pay_btn.visibility = View.VISIBLE
            shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!
            shopping_cart.add(intent.getParcelableExtra<Sandwich>("selectedSandwich"))
            for (item in shopping_cart) {
                shop.setText(shop.text.toString() + item?.getSandwichName() + "  +  " + item?.getCookieName() + '\n')
            }
        }
        else{
            shop.visibility = View.INVISIBLE
            pay_btn.visibility = View.INVISIBLE
        }
    }
}