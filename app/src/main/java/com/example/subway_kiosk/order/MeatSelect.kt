package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.subway_kiosk.R
import com.example.subway_kiosk.util.Sandwich
import com.example.subway_kiosk.util.Stock
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MeatSelect : AppCompatActivity()
{
    var pressedBtnList = ArrayList<Int>()
    var shopping_cart = arrayListOf<Sandwich?>()
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("stock")
    var cnt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meat_select)
        val MeatImageList = arrayListOf<Button>(
            findViewById(R.id.meatSelect_avocado),
            findViewById(R.id.meatSelect_bacon),
            findViewById(R.id.meatSelect_chicken_breast),
            findViewById(R.id.meatSelect_chicken_breast_ham),
            findViewById(R.id.meatSelect_chicken_teriyaki),
            findViewById(R.id.meatSelect_egg_mayo),
            findViewById(R.id.meatSelect_ham),
            findViewById(R.id.meatSelect_pepperoni),
            findViewById(R.id.meatSelect_pulled_pork_bbq),
            findViewById(R.id.meatSelect_rotisserie_chicken),
            findViewById(R.id.meatSelect_salami),
            findViewById(R.id.meatSelect_shrimp),
            findViewById(R.id.meatSelect_spicy_bbq),
            findViewById(R.id.meatSelect_steak),
            findViewById(R.id.meatSelect_tuna),
            findViewById(R.id.meatSelect_vege)
        )

        stockRef.child("meat").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var meat : Stock = item.getValue(Stock::class.java)!!
                    Log.d("meat",meat.num.toString())
                    if(meat.num < 5){
                        MeatImageList[cnt].setCompoundDrawablesWithIntrinsicBounds(
                            null, getDrawable(R.drawable.sauce_none_xml), null, null
                        )
                        MeatImageList[cnt].setBackgroundResource(R.drawable.corner_button3)
                        MeatImageList[cnt].setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
                        MeatImageList[cnt].isEnabled = false;
                        MeatImageList[cnt].isClickable = false;
                    }
                    cnt++;
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        val nextBtn = findViewById<Button>(R.id.meatSelect_toNext)
        shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!
        nextBtn.setOnClickListener {
            if (pressedBtnList.size == 0)
            {
                Toast.makeText(this@MeatSelect, "하나 이상의 재료를 선택해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val nextIntent = Intent(this@MeatSelect, MenuSelect::class.java)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            nextIntent.putExtra("selectedMeat", pressedBtnList)
            startActivity(nextIntent)
        }

        val prevBtn = findViewById<Button>(R.id.meatSelect_toPrev)
        prevBtn.setOnClickListener {
            finish()
        }
    }

    fun btnClick(v: View)
    {
        val selectedBtn = findViewById<Button>(v.id)
        if (pressedBtnList.contains(selectedBtn.id))
        {
            selectedBtn.setBackgroundResource(R.drawable.corner_button3)
            selectedBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            pressedBtnList.remove(selectedBtn.id)
            return
        }

        if (pressedBtnList.size == 2)
        {
            val btn1 = findViewById<Button>(pressedBtnList[0])
            btn1.setBackgroundResource(R.drawable.corner_button3)
            btn1.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            pressedBtnList.removeFirst()
        }

        selectedBtn.setBackgroundResource(R.drawable.corner_button3_selected)
        selectedBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        pressedBtnList.add(selectedBtn.id)
    }
}