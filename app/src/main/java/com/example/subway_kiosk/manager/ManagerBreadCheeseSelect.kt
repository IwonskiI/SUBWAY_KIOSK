package com.example.subway_kiosk.manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import com.example.subway_kiosk.R
import com.example.subway_kiosk.util.Sandwich
import kotlin.system.exitProcess

class ManagerBreadCheeseSelect : AppCompatActivity()
{
    var selectedSandwich = Sandwich.menuList.get(0).deepCopy()
    lateinit var selectedBread: Button
    lateinit var selectedCheese: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bread_cheese_select)

        if (intent.hasExtra("selectedSandwich"))
        {
            selectedSandwich = intent.getParcelableExtra<Sandwich>("selectedSandwich")!!
            println(selectedSandwich.toString())

            when (selectedSandwich.getBreadID())
            {
                1    -> selectedBread = findViewById(R.id.bread_white)
                2    -> selectedBread = findViewById(R.id.bread_honey)
                3    -> selectedBread = findViewById(R.id.bread_wheet)
                4    -> selectedBread = findViewById(R.id.bread_pamasan)
                5    -> selectedBread = findViewById(R.id.bread_hati)
                6    -> selectedBread = findViewById(R.id.bread_flat)
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
        passAllBtn.visibility = View.INVISIBLE

        val passBtn = findViewById<Button>(R.id.breadCheeseSelect_pass)
        passBtn.setOnClickListener {
            val nextIntent = Intent(this@ManagerBreadCheeseSelect, ManagerVegeSelect::class.java)
            nextIntent.putExtra("selectedSandwich", selectedSandwich)
            nextIntent.putExtra("menuNum",intent.getStringExtra("menuNum"))
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
            R.id.bread_white  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(1)
            }
            R.id.bread_honey  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(2)
            }
            R.id.bread_wheet  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(3)
            }
            R.id.bread_pamasan  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(4)
            }
            R.id.bread_hati  ->
            {
                selectedBread = selectedBtn
                selectedSandwich.setBreadID(5)
            }
            R.id.bread_flat  ->
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