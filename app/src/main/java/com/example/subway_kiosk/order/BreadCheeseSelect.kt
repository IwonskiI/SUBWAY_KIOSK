package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.subway_kiosk.R
import com.example.subway_kiosk.util.Sandwich
import kotlin.system.exitProcess

class BreadCheeseSelect : AppCompatActivity()
{
    var selectedSandwich = Sandwich.menuList.get(0).deepCopy()
    var shopping_cart = arrayListOf<Sandwich?>()
    lateinit var selectedBread: Button
    lateinit var selectedCheese: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bread_cheese_select)

        if (intent.hasExtra("selectedSandwich"))
        {
            selectedSandwich = intent.getParcelableExtra<Sandwich>("selectedSandwich")!!
            shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!
            println(selectedSandwich.toString())

            when (selectedSandwich.getBreadID())
            {
                1    -> selectedBread = findViewById(R.id.breadBtn1)
                2    -> selectedBread = findViewById(R.id.breadBtn2)
                3    -> selectedBread = findViewById(R.id.breadBtn3)
                4    -> selectedBread = findViewById(R.id.breadBtn4)
                5    -> selectedBread = findViewById(R.id.breadBtn5)
                6    -> selectedBread = findViewById(R.id.breadBtn6)
                else -> exitProcess(-1)
            }

            when (selectedSandwich.getCheeseID())
            {
                1    -> selectedCheese = findViewById(R.id.cheeseBtn1)
                2    -> selectedCheese = findViewById(R.id.cheeseBtn2)
                3    -> selectedCheese = findViewById(R.id.cheeseBtn3)
                else -> exitProcess(-1)
            }

            selectedBread.setBackgroundResource(R.drawable.corner_button3_selected)
            selectedBread.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            selectedCheese.setBackgroundResource(R.drawable.corner_button3_selected)
            selectedCheese.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        }

        val prevBtn = findViewById<Button>(R.id.breadCheeseSelect_toPrev)
        prevBtn.setOnClickListener {
            finish()
        }

        val passAllBtn = findViewById<Button>(R.id.breadCheeseSelect_passAll)
        passAllBtn.setOnClickListener {
            val nextIntent = Intent(this@BreadCheeseSelect, tester::class.java)
            nextIntent.putExtra("selectedSandwich", selectedSandwich)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }

        val passBtn = findViewById<Button>(R.id.breadCheeseSelect_pass)
        passBtn.setOnClickListener {
            val nextIntent = Intent(this@BreadCheeseSelect, VegeSelect::class.java)
            nextIntent.putExtra("selectedSandwich", selectedSandwich)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }
    }

    fun btnClick(v: View)
    {
        val selectedBtn = findViewById<Button>(v.id)

        selectedBread.setBackgroundResource(R.drawable.corner_button3)
        selectedBread.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
        selectedCheese.setBackgroundResource(R.drawable.corner_button3)
        selectedCheese.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))

        when (selectedBtn.id)
        {
            R.id.breadBtn1  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(1)
            }
            R.id.breadBtn2  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(2)
            }
            R.id.breadBtn3  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(3)
            }
            R.id.breadBtn4  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(4)
            }
            R.id.breadBtn5  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(5)
            }
            R.id.breadBtn6  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(6)
            }
            R.id.cheeseBtn1 ->
            {
                selectedCheese = selectedBtn
                selectedSandwich.setCheeseID(1)
            }
            R.id.cheeseBtn2 ->
            {
                selectedCheese = selectedBtn
                selectedSandwich.setCheeseID(2)
            }
            R.id.cheeseBtn3 ->
            {
                selectedCheese = selectedBtn
                selectedSandwich.setCheeseID(3)
            }
            else            -> exitProcess(-1)
        }

        selectedBread.setBackgroundResource(R.drawable.corner_button3_selected)
        selectedBread.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        selectedCheese.setBackgroundResource(R.drawable.corner_button3_selected)
        selectedCheese.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
    }
}