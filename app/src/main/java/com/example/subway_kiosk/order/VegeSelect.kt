package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
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

class VegeSelect : AppCompatActivity()
{
    var selectedSandwich = Sandwich.menuList.get(0).deepCopy()
    var shopping_cart = arrayListOf<Sandwich?>()
    lateinit var lettuceBtn: Button
    lateinit var tomatoBtn: Button
    lateinit var cucumberBtn: Button
    lateinit var pepperBtn: Button
    lateinit var onionBtn: Button
    lateinit var pickleBtn: Button
    lateinit var oliveBtn: Button
    lateinit var jalapenoBtn: Button
    lateinit var avacadoBtn: Button

    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("stock")
    var cnt: Int = 0
    var vege_arr = arrayOf(3,8,1,7,5,6,4,2)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vege_select)

        val VegeImageList = arrayListOf<Button>(
            findViewById(R.id.cucumber),
            findViewById(R.id.jalapeno),
            findViewById(R.id.lettuce),
            findViewById(R.id.olive),
            findViewById(R.id.onion),
            findViewById(R.id.pickle),
            findViewById(R.id.pimento),
            findViewById(R.id.tomato),
        )
        stockRef.child("vege").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var vege : Stock = item.getValue(Stock::class.java)!!
                    if(vege.num < 1){
                        VegeImageList[cnt].setCompoundDrawablesWithIntrinsicBounds(
                            null, getDrawable(R.drawable.sauce_none_xml), null, null
                        )
                        selectedSandwich.changeVege(vege_arr[cnt])
                        VegeImageList[cnt].setBackgroundResource(R.drawable.corner_button3)
                        VegeImageList[cnt].setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
                        VegeImageList[cnt].isEnabled = false;
                        VegeImageList[cnt].isClickable = false;
                    }
                    cnt++;
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        lettuceBtn = findViewById(R.id.lettuce)
        tomatoBtn = findViewById(R.id.tomato)
        cucumberBtn = findViewById(R.id.cucumber)
        pepperBtn = findViewById(R.id.pimento)
        onionBtn = findViewById(R.id.onion)
        pickleBtn = findViewById(R.id.pickle)
        oliveBtn = findViewById(R.id.olive)
        jalapenoBtn = findViewById(R.id.jalapeno)
        avacadoBtn = findViewById(R.id.avocado)

        if (intent.hasExtra("selectedSandwich"))
        {
            shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!
            selectedSandwich = intent.getParcelableExtra<Sandwich>("selectedSandwich")!!
            println(selectedSandwich.toString())

            updateBtnStatus()
        }

        val prevBtn = findViewById<Button>(R.id.breadCheeseSelect_toPrev)
        prevBtn.setOnClickListener {
            finish()
        }

        val passAllBtn = findViewById<Button>(R.id.breadCheeseSelect_passAll)
        passAllBtn.setOnClickListener {
            val nextIntent = Intent(this@VegeSelect, tester::class.java)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            nextIntent.putExtra("selectedSandwich", selectedSandwich)
            startActivity(nextIntent)
        }

        val passBtn = findViewById<Button>(R.id.breadCheeseSelect_pass)
        passBtn.setOnClickListener {
            val nextIntent = Intent(this@VegeSelect, SauceSelect::class.java)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            nextIntent.putExtra("selectedSandwich", selectedSandwich)
            startActivity(nextIntent)
        }
    }

    fun btnClick(v: View)
    {
        val selectedBtn = findViewById<Button>(v.id)

        when (selectedBtn.id)
        {
            R.id.lettuce  -> selectedSandwich.changeVege(1)
            R.id.tomato   -> selectedSandwich.changeVege(2)
            R.id.cucumber -> selectedSandwich.changeVege(3)
            R.id.pimento  -> selectedSandwich.changeVege(4)
            R.id.onion    -> selectedSandwich.changeVege(5)
            R.id.pickle   -> selectedSandwich.changeVege(6)
            R.id.olive    -> selectedSandwich.changeVege(7)
            R.id.jalapeno -> selectedSandwich.changeVege(8)
            R.id.avocado  -> selectedSandwich.changeVege(9)
            else          -> exitProcess(-1)
        }


        updateBtnStatus()
    }

    fun updateBtnStatus()
    {
        changeBtnStatus(lettuceBtn, selectedSandwich.getVege().getLettuce())
        changeBtnStatus(tomatoBtn, selectedSandwich.getVege().getTomato())
        changeBtnStatus(cucumberBtn, selectedSandwich.getVege().getCucumber())
        changeBtnStatus(pepperBtn, selectedSandwich.getVege().getPepper())
        changeBtnStatus(onionBtn, selectedSandwich.getVege().getOnion())
        changeBtnStatus(pickleBtn, selectedSandwich.getVege().getPickle())
        changeBtnStatus(oliveBtn, selectedSandwich.getVege().getOlive())
        changeBtnStatus(jalapenoBtn, selectedSandwich.getVege().getJalapeno())
        changeBtnStatus(avacadoBtn, selectedSandwich.getVege().getAvocado())
    }

    fun changeBtnStatus(btn: Button, bool: Boolean)
    {
        if (bool)
        {
            btn.setBackgroundResource(R.drawable.corner_button3_selected)
            btn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        }
        else
        {
            btn.setBackgroundResource(R.drawable.corner_button3)
            btn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
        }
    }
}