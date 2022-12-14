package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.subway_kiosk.MainActivity
import com.example.subway_kiosk.R
import com.example.subway_kiosk.util.Sandwich
import kotlin.system.exitProcess

class CookieSelect : AppCompatActivity()
{
    var selectedSandwich = Sandwich.menuList.get(0).deepCopy()

    lateinit var selectedSet: Button
    lateinit var selectedCookie: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cookie_select)

        val cookieLayout: GridLayout = findViewById<GridLayout>(R.id.cookie_grid)
        val cookieTitle: TextView = findViewById<TextView>(R.id.cookie_select)

        cookieLayout.visibility = View.INVISIBLE
        cookieTitle.visibility = View.INVISIBLE

        if (intent.hasExtra("selectedSandwich"))
        {
            selectedSandwich = intent.getParcelableExtra<Sandwich>("selectedSandwich")!!
            println(selectedSandwich.toString())

            when (selectedSandwich.getSetBool())
            {
                false  -> selectedSet = findViewById(R.id.setBtn1)
                true   -> selectedSet = findViewById(R.id.setBtn2)
                else -> exitProcess(-1)
            }

            when (selectedSandwich.getCookieID())
            {
                0    -> selectedCookie = findViewById(R.id.cookieBtn1)
                1    -> selectedCookie = findViewById(R.id.cookieBtn1)
                2    -> selectedCookie = findViewById(R.id.cookieBtn2)
                3    -> selectedCookie = findViewById(R.id.cookieBtn3)
                4    -> selectedCookie = findViewById(R.id.cookieBtn4)
                5    -> selectedCookie = findViewById(R.id.cookieBtn5)
                else -> exitProcess(-1)
            }

            selectedSet.setBackgroundResource(R.drawable.corner_button3_selected)
            selectedSet.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            selectedCookie.setBackgroundResource(R.drawable.corner_button3_selected)
            selectedCookie.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        }

        val prevBtn = findViewById<Button>(R.id.setSelect_toPrev)
        prevBtn.setOnClickListener {
            finish()
        }

        val passBtn = findViewById<Button>(R.id.setSelect_pass)
        passBtn.setOnClickListener {
            val nextIntent = Intent(this@CookieSelect, MainActivity::class.java)
            nextIntent.putExtra("selectedSandwich", selectedSandwich)
            startActivity(nextIntent)
        }
    }

    fun btnClick(v: View)
    {
        val selectedBtn = findViewById<Button>(v.id)
        val cookieLayout: GridLayout = findViewById<GridLayout>(R.id.cookie_grid)
        val cookieTitle: TextView = findViewById<TextView>(R.id.cookie_select)

        selectedSet.setBackgroundResource(R.drawable.corner_button3)
        selectedSet.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
        selectedCookie.setBackgroundResource(R.drawable.corner_button3)
        selectedCookie.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))

        when (selectedBtn.id)
        {
            R.id.setBtn1  ->
            {
                cookieLayout.visibility = View.INVISIBLE
                cookieTitle.visibility = View.INVISIBLE
                selectedSet = selectedBtn
                selectedSandwich.setCookieID(0)
                selectedSandwich.setSet(false)
            }
            R.id.setBtn2  ->
            {
                cookieLayout.visibility = View.VISIBLE
                cookieTitle.visibility = View.VISIBLE
                selectedSet = selectedBtn
                selectedSandwich.setCookieID(1)
                selectedCookie = findViewById(R.id.cookieBtn1)
                selectedSandwich.setSet(true)

            }
            R.id.cookieBtn1 ->
            {
                selectedCookie = selectedBtn
                selectedSandwich.setCookieID(1)
            }
            R.id.cookieBtn2 ->
            {
                selectedCookie = selectedBtn
                selectedSandwich.setCookieID(2)
            }
            R.id.cookieBtn3 ->
            {
                selectedCookie = selectedBtn
                selectedSandwich.setCookieID(3)
            }
            R.id.cookieBtn4 ->
            {
                selectedCookie = selectedBtn
                selectedSandwich.setCookieID(4)
            }
            R.id.cookieBtn5 ->
            {
                selectedCookie = selectedBtn
                selectedSandwich.setCookieID(5)
            }
            else            -> exitProcess(-1)
        }

        selectedSet.setBackgroundResource(R.drawable.corner_button3_selected)
        selectedSet.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        selectedCookie.setBackgroundResource(R.drawable.corner_button3_selected)
        selectedCookie.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
    }
}