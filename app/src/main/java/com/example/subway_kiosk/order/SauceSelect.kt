package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
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

class SauceSelect : AppCompatActivity()
{
    var selectedSandwich = Sandwich.menuList.get(0).deepCopy()
    var shopping_cart = arrayListOf<Sandwich?>()
    var sauceBtnList = arrayListOf<Button>()

    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("stock")
    var cnt: Int = 0


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sauce_select)
        var sauce_arr = arrayOf(202,102,204,201,301,402,203,501,503,401,302,502,104,103,101)
        val SauceImageList = arrayListOf<Button>(
            findViewById(R.id.chipotle),
            findViewById(R.id.honeyMustard),
            findViewById(R.id.horseradish),
            findViewById(R.id.hotChilli),
            findViewById(R.id.italianDressing),
            findViewById(R.id.mayonnaise),
            findViewById(R.id.mustard),
            findViewById(R.id.oliveOil),
            findViewById(R.id.pepper),
            findViewById(R.id.ranch),
            findViewById(R.id.redWine),
            findViewById(R.id.salt),
            findViewById(R.id.smokeBBQ),
            findViewById(R.id.sweetChili),
            findViewById(R.id.sweetOnion),
        )

        sauceBtnList.add(findViewById(R.id.sweetOnion))//1
        sauceBtnList.add(findViewById(R.id.honeyMustard))//2
        sauceBtnList.add(findViewById(R.id.sweetChili))//3
        sauceBtnList.add(findViewById(R.id.smokeBBQ))//4
        sauceBtnList.add(findViewById(R.id.hotChilli))//5
        sauceBtnList.add(findViewById(R.id.chipotle))//6
        sauceBtnList.add(findViewById(R.id.mustard))//7
        sauceBtnList.add(findViewById(R.id.horseradish))//8
        sauceBtnList.add(findViewById(R.id.italianDressing))//9
        sauceBtnList.add(findViewById(R.id.redWine))//10
        sauceBtnList.add(findViewById(R.id.ranch))//11
        sauceBtnList.add(findViewById(R.id.mayonnaise))//12
        sauceBtnList.add(findViewById(R.id.oliveOil))//13
        sauceBtnList.add(findViewById(R.id.salt))//14
        sauceBtnList.add(findViewById(R.id.pepper))//15
        sauceBtnList.add(findViewById(R.id.noSauce))//16



        if (intent.hasExtra("selectedSandwich"))
        {
            shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!
            selectedSandwich = intent.getParcelableExtra<Sandwich>("selectedSandwich")!!
            println(selectedSandwich.toString())

            updateBtnStatus()
        }

        stockRef.child("sauce").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var sauce : Stock = item.getValue(Stock::class.java)!!
                    if(sauce.num < 5){
                        SauceImageList[cnt].setCompoundDrawablesWithIntrinsicBounds(
                            null, getDrawable(R.drawable.sauce_none_xml), null, null
                        )
                        selectedSandwich.removeSauce(sauce_arr[cnt])
                        SauceImageList[cnt].setBackgroundResource(R.drawable.corner_button3)
                        SauceImageList[cnt].setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
                        SauceImageList[cnt].isEnabled = false;
                        SauceImageList[cnt].isClickable = false;
                    }
                    cnt++;
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        val prevBtn = findViewById<Button>(R.id.breadCheeseSelect_toPrev)
        prevBtn.setOnClickListener {
            finish()
        }

        val passAllBtn = findViewById<Button>(R.id.breadCheeseSelect_passAll)
        passAllBtn.setOnClickListener {
            val nextIntent = Intent(this@SauceSelect, CookieSelect::class.java)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            nextIntent.putExtra("selectedSandwich", selectedSandwich)
            startActivity(nextIntent)
        }

        val passBtn = findViewById<Button>(R.id.breadCheeseSelect_pass)
        passBtn.setOnClickListener {
            val nextIntent = Intent(this@SauceSelect, CookieSelect::class.java)
            nextIntent.putExtra("shoppingCart",shopping_cart)
            nextIntent.putExtra("selectedSandwich", selectedSandwich)
            startActivity(nextIntent)
        }
    }

    fun btnClick(v: View)
    {
        val selectedBtn = findViewById<Button>(v.id)

        var sauceID: Int

        when (selectedBtn.id)
        {
            R.id.sweetOnion      -> sauceID = 101
            R.id.honeyMustard    -> sauceID = 102
            R.id.sweetChili      -> sauceID = 103
            R.id.smokeBBQ        -> sauceID = 104

            R.id.hotChilli       -> sauceID = 201
            R.id.chipotle        -> sauceID = 202
            R.id.mustard         -> sauceID = 203
            R.id.horseradish     -> sauceID = 204

            R.id.italianDressing -> sauceID = 301
            R.id.redWine         -> sauceID = 302

            R.id.ranch           -> sauceID = 401
            R.id.mayonnaise      -> sauceID = 402

            R.id.oliveOil        -> sauceID = 501
            R.id.salt            -> sauceID = 502
            R.id.pepper          -> sauceID = 503
            R.id.noSauce         ->
            {
                selectedSandwich.getSauceIDList().clear()
                updateBtnStatus()
                selectedBtn.setBackgroundResource(R.drawable.corner_button3_selected)
                return
            }
            else                 -> exitProcess(-1)
        }

        if (selectedSandwich.getSauceIDList().contains(sauceID))
            selectedSandwich.getSauceIDList().remove(sauceID)
        else if(selectedSandwich.getSauceIDList().size < 5)
            selectedSandwich.getSauceIDList().add(sauceID)
        else Toast.makeText(this@SauceSelect, "최대 5가지의 소스를 선택 가능합니다", Toast.LENGTH_SHORT).show()

        updateBtnStatus()
    }

    fun updateBtnStatus()
    {
        changeBtnStatus(sauceBtnList.get(0), false)
        changeBtnStatus(sauceBtnList.get(1), false)
        changeBtnStatus(sauceBtnList.get(2), false)
        changeBtnStatus(sauceBtnList.get(3), false)
        changeBtnStatus(sauceBtnList.get(4), false)
        changeBtnStatus(sauceBtnList.get(5), false)
        changeBtnStatus(sauceBtnList.get(6), false)
        changeBtnStatus(sauceBtnList.get(7), false)
        changeBtnStatus(sauceBtnList.get(8), false)
        changeBtnStatus(sauceBtnList.get(9), false)
        changeBtnStatus(sauceBtnList.get(10), false)
        changeBtnStatus(sauceBtnList.get(11), false)
        changeBtnStatus(sauceBtnList.get(12), false)
        changeBtnStatus(sauceBtnList.get(13), false)
        changeBtnStatus(sauceBtnList.get(14), false)
        changeBtnStatus(sauceBtnList.get(15), false)

        if (selectedSandwich.getSauceIDList().size == 0)
        {
            changeBtnStatus(findViewById(R.id.noSauce), true)
            return
        }

        selectedSandwich.getSauceIDList().forEach {
            when (it)
            {
                101  -> changeBtnStatus(sauceBtnList.get(0), true)
                102  -> changeBtnStatus(sauceBtnList.get(1), true)
                103  -> changeBtnStatus(sauceBtnList.get(2), true)
                104  -> changeBtnStatus(sauceBtnList.get(3), true)
                201  -> changeBtnStatus(sauceBtnList.get(4), true)
                202  -> changeBtnStatus(sauceBtnList.get(5), true)
                203  -> changeBtnStatus(sauceBtnList.get(6), true)
                204  -> changeBtnStatus(sauceBtnList.get(7), true)
                301  -> changeBtnStatus(sauceBtnList.get(8), true)
                302  -> changeBtnStatus(sauceBtnList.get(9), true)
                401  -> changeBtnStatus(sauceBtnList.get(10), true)
                402  -> changeBtnStatus(sauceBtnList.get(11), true)
                501  -> changeBtnStatus(sauceBtnList.get(12), true)
                502  -> changeBtnStatus(sauceBtnList.get(13), true)
                503  -> changeBtnStatus(sauceBtnList.get(14), true)
                else -> exitProcess(-1)
            }
        }
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