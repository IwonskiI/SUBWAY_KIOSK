package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.subway_kiosk.R
import com.example.subway_kiosk.util.Sandwich
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.Integer.min

class MenuSelect : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_select)

        val meatSelectView = View.inflate(this, R.layout.meat_select, null)
        val menuListByMeat = Sandwich.getMenuListByMeat(meatSelectView)

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

                for (i: Int in 0 .. min(2, menuListByMeat.getValue(selected.get(0)).size - 1))
                {
                    selectedMeatButtonList1[i].isVisible = true
                    selectedMeatButtonList1[i].setText(menuListByMeat.getValue(selected.get(0))[i].getSandwichName())
                    val id = menuListByMeat.getValue(selected.get(0))[i].getSandwichID()
                    selectedMeatButtonList1[i].setCompoundDrawablesWithIntrinsicBounds(
                        null, getDrawable(Sandwich.hashMapIDtoXML.getValue(id)), null, null
                    )
                }

                if (selected.size == 2)
                {
                    selectedMeatTextView2.isVisible = true
                    selectedMeatGridLayout2.isVisible = true

                    selectedMeatTextView2.setText(meatSelectView.findViewById<Button>(selected.get(1)).text)
                    for (i: Int in 0 .. min(2, menuListByMeat.getValue(selected.get(1)).size - 1))
                    {
                        selectedMeatButtonList2[i].isVisible = true
                        selectedMeatButtonList2[i].setText(menuListByMeat.getValue(selected.get(1))[i].getSandwichName())
                        val id = menuListByMeat.getValue(selected.get(1))[i].getSandwichID()
                        selectedMeatButtonList2[i].setCompoundDrawablesWithIntrinsicBounds(
                            null, getDrawable(Sandwich.hashMapIDtoXML.getValue(id)), null, null
                        )
                    }

                    menuSelectText = buildString {
                        append("${meatSelectView.findViewById<Button>(selected.get(0)).text}, ")
                        append("${meatSelectView.findViewById<Button>(selected.get(1)).text}")
                        append("에 대한 검색 결과입니다")
                    }

                }
                else menuSelectText = buildString {
                    append("${meatSelectView.findViewById<Button>(selected.get(0)).text}")
                    append("에 대한 검색 결과입니다")
                }

                selectedMeatTextView1.setText(meatSelectView.findViewById<Button>(selected.get(0)).text)
                mainTextView.setText(menuSelectText)
            }
        }

        val prevBtn = findViewById<Button>(R.id.menuSelect_toPrev)
        prevBtn.setOnClickListener {
            finish()
        }
    }

    fun btnClick(v: View)
    {
        val selectedBtn = findViewById<Button>(v.id)
        val dialog = BottomSheetDialog(this, R.style.SubWayDialog)

        dialog.setContentView(R.layout.menu_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);

        val prevBtn = dialog.findViewById<Button>(R.id.dialog_toPrev)
        prevBtn?.setOnClickListener({ dialog.hide() })

        var selectedSandwich = Sandwich.menuList.get(0).deepCopy()

        Sandwich.menuList.forEach {
            if (it.getSandwichName().equals(selectedBtn.text))
            {
                selectedSandwich = it.deepCopy()

                var sandwichNameTextView = dialog.findViewById<TextView>(R.id.dialog_name)
                sandwichNameTextView?.setText(it.getSandwichName())

                var sandwichImgView = dialog.findViewById<ImageView>(R.id.dialog_img)
                sandwichImgView?.setImageResource(Sandwich.hashMapIDtoXML.getValue(it.getSandwichID()))
            }
        }

        val selectBtn = dialog.findViewById<Button>(R.id.dialog_selectMenu)
        selectBtn?.setOnClickListener {
            dialog.hide()
            val nextIntent = Intent(this@MenuSelect, BreadCheeseSelect::class.java)
            nextIntent.putExtra("selectedSandwich", selectedSandwich)
            startActivity(nextIntent)
        }

        dialog.show()
    }
}