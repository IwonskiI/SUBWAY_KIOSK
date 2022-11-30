package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.subway_kiosk.R
import com.example.subway_kiosk.util.Sandwich
import com.example.subway_kiosk.util.Vegetable
import java.lang.Integer.min
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MenuSelect : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_select)

        val meatSelectView = View.inflate(this, R.layout.meat_select, null)
        val menuList = Sandwich.loadSandwichData(meatSelectView)

        if (intent.hasExtra("selectedMeat"))
        {
            val mainTextView = findViewById<TextView>(R.id.menuselect_text)
            val selectedMeatTextView1 = findViewById<TextView>(R.id.menuselect_meat1)
            val selectedMeatTextView2 = findViewById<TextView>(R.id.menuselect_meat2)
            val selectedMeatGridLayout1 = findViewById<GridLayout>(R.id.menuselect_meat1_grid)
            val selectedMeatGridLayout2 = findViewById<GridLayout>(R.id.menuselect_meat2_grid)
            val selectedMeatButtonList1 = arrayListOf<Button>(
                findViewById(R.id.button101),
                findViewById(R.id.button102),
                findViewById(R.id.button103)
            )
            val selectedMeatButtonList2 = arrayListOf<Button>(
                findViewById(R.id.button201),
                findViewById(R.id.button202),
                findViewById(R.id.button203),
            )
            selectedMeatButtonList1.forEach { it.isVisible = false }
            selectedMeatButtonList2.forEach { it.isVisible = false }
            selectedMeatTextView2.isVisible = false
            selectedMeatGridLayout2.isVisible = false

            var selected = intent.getIntegerArrayListExtra("selectedMeat")
            if (selected != null)
            {
                val menuSelectText: String

                if (selected.size == 2)
                {
                    if (menuList.getValue(selected[0]).size < menuList.getValue(selected[1]).size) Collections.swap(
                        selected, 0, 1
                    )

                    menuSelectText = buildString {
                        append("${meatSelectView.findViewById<Button>(selected.get(0)).text}, ")
                        append("${meatSelectView.findViewById<Button>(selected.get(1)).text}")
                        append("에 대한 검색 결과입니다")
                    }

                    selectedMeatTextView2.setText(meatSelectView.findViewById<Button>(selected.get(1)).text)

                    for (i: Int in 0 .. min(3, menuList.getValue(selected.get(1)).size - 1))
                    {
                        selectedMeatButtonList2[i].setText(menuList.getValue(selected.get(1))[i].getSandwichName())
                        selectedMeatButtonList2[i].isVisible = true
                    }

                    selectedMeatTextView2.isVisible = true
                    selectedMeatGridLayout2.isVisible = true
                }
                else menuSelectText = buildString {
                    append("${meatSelectView.findViewById<Button>(selected.get(0)).text}")
                    append("에 대한 검색 결과입니다")
                }

                menuList.getValue(selected.get(0)).forEach { it.showInfo() }
                for (i: Int in 0 .. min(6, menuList.getValue(selected.get(0)).size - 1))
                {
                    selectedMeatButtonList1[i].setText(menuList.getValue(selected.get(0))[i].getSandwichName())
                    selectedMeatButtonList1[i].setCompoundDrawablesWithIntrinsicBounds(
                        null, getDrawable(R.drawable.meat_select_bacon_xml), null, null
                    )
                    selectedMeatButtonList1[i].isVisible = true
                }

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
}