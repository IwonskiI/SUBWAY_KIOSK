package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
import kotlin.system.exitProcess

class BreadCheeseSelect : AppCompatActivity()
{
    var selectedSandwich = Sandwich.menuList.get(0).deepCopy()
    var shopping_cart = arrayListOf<Sandwich?>()
    lateinit var selectedBread: Button
    lateinit var selectedCheese: Button

    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("stock")
    var cnt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bread_cheese_select)

        val BreadImageList = arrayListOf<Button>(
            findViewById(R.id.bread_flat),
            findViewById(R.id.bread_hati),
            findViewById(R.id.bread_honey),
            findViewById(R.id.bread_pamasan),
            findViewById(R.id.bread_wheet),
            findViewById(R.id.bread_white),
        )
        val CheeseImageList = arrayListOf<Button>(
            findViewById(R.id.cheeseBtn1),
            findViewById(R.id.cheeseBtn2),
            findViewById(R.id.cheeseBtn3),
        )
        stockRef.child("bread").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var bread : Stock = item.getValue(Stock::class.java)!!
                    if(bread.num < 1){
                        //Log.d("hi",BreadImageList[cnt].toString())
                        BreadImageList[cnt].setCompoundDrawablesWithIntrinsicBounds(
                            null, getDrawable(R.drawable.sauce_none_xml), null, null
                        )
                        BreadImageList[cnt].setBackgroundResource(R.drawable.corner_button3)
                        BreadImageList[cnt].setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
                        BreadImageList[cnt].isEnabled = false;
                        BreadImageList[cnt].isClickable = false;
                    }
                    cnt++;
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        stockRef.child("cheese").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var cheese : Stock = item.getValue(Stock::class.java)!!
                    if(cheese.num < 1){
                        CheeseImageList[cnt].setCompoundDrawablesWithIntrinsicBounds(
                            null, getDrawable(R.drawable.sauce_none_xml), null, null
                        )
                        CheeseImageList[cnt].setBackgroundResource(R.drawable.corner_button3)
                        CheeseImageList[cnt].setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
                        CheeseImageList[cnt].isEnabled = false;
                        CheeseImageList[cnt].isClickable = false;
                    }
                    cnt++;
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        if (intent.hasExtra("selectedSandwich"))
        {
            selectedSandwich = intent.getParcelableExtra<Sandwich>("selectedSandwich")!!
            shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!
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