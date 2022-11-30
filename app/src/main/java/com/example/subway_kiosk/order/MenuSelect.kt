package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.subway_kiosk.R
import com.example.subway_kiosk.util.Sandwich
import com.example.subway_kiosk.util.Vegetable

class MenuSelect : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_select)

        val meatSelectView = View.inflate(this, R.layout.meat_select, null)
        val menuList = loadSandwichData(meatSelectView)

        if (intent.hasExtra("selectedMeat"))
        {
            val mainTextView = findViewById<TextView>(R.id.menuselect_text)
            val selectedMeatTextView1 = findViewById<TextView>(R.id.menuselect_meat1)
            val selectedMeatTextView2 = findViewById<TextView>(R.id.menuselect_meat2)
            val meatGridLayout = findViewById<GridLayout>(R.id.menuselect_meat1_grid)
            selectedMeatTextView2.isVisible = false
            meatGridLayout.isVisible = false

            val selected = intent.getIntegerArrayListExtra("selectedMeat")
            if (selected != null)
            {
                val menuSelectText: String

                if (selected.size == 2)
                {
                    menuSelectText = buildString {
                        append("${meatSelectView.findViewById<Button>(selected.get(0)).text}, ")
                        append("${meatSelectView.findViewById<Button>(selected.get(1)).text}")
                        append("에 대한 검색 결과입니다")
                    }
                    selectedMeatTextView2.setText(meatSelectView.findViewById<Button>(selected.get(1)).text)
                    selectedMeatTextView2.isVisible = true
                    meatGridLayout.isVisible = true

                    menuList.getValue(selected.get(1)).forEach { it.showInfo() }
                    var btn = Button(this)
                    meatGridLayout.addView(btn)
                }
                else menuSelectText = buildString {
                    append("${meatSelectView.findViewById<Button>(selected.get(0)).text}")
                    append("에 대한 검색 결과입니다")
                }

                menuList.getValue(selected.get(0)).forEach { it.showInfo() }
                selectedMeatTextView1.setText(meatSelectView.findViewById<Button>(selected.get(0)).text)
                mainTextView.setText(menuSelectText)
            }
        }

        val nextBtn = findViewById<Button>(R.id.menuSelect_toNext)
        nextBtn.setOnClickListener {
            val nextIntent = Intent(this, MeatSelect::class.java)
            startActivity(nextIntent)
        }
    }

    fun loadSandwichData(v:View) : HashMap<Int, ArrayList<Sandwich>>
    {
        val menuList = hashMapOf<Int, ArrayList<Sandwich>>(
            v.findViewById<Button>(R.id.meatSelect_ham).id to arrayListOf<Sandwich>(
                Sandwich(
                    101,
                    "이탈리안 비엠티",
                    1,
                    1,
                    Vegetable(true, true, true, true, true, true, true, true, false),
                    arrayListOf<Int>(301, 101)
                ), Sandwich(
                    102,
                    "써브웨이 클럽",
                    1,
                    1,
                    Vegetable(true, true, true, true, true, true, true, true, false),
                    arrayListOf<Int>(301, 101)
                ), Sandwich(
                    103,
                    "햄",
                    1,
                    1,
                    Vegetable(true, true, true, true, true, true, true, true, false),
                    arrayListOf<Int>(301, 101)
                ), Sandwich(
                    103,
                    "햄2",
                    1,
                    1,
                    Vegetable(true, true, true, true, true, true, true, true, false),
                    arrayListOf<Int>(301, 101)
                )
            ), v.findViewById<Button>(R.id.meatSelect_bacon).id to arrayListOf<Sandwich>(
                Sandwich(
                    201,
                    "베이컨 비엠티",
                    1,
                    1,
                    Vegetable(true, true, true, true, true, true, true, true, false),
                    arrayListOf<Int>(301, 101)
                ), Sandwich(
                    202,
                    "베이컨 클럽",
                    1,
                    1,
                    Vegetable(true, true, true, true, true, true, true, true, false),
                    arrayListOf<Int>(301, 101)
                )
            )
        )
        return menuList
    }
}