package com.example.subway_kiosk.manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.subway_kiosk.ManagerActivity
import com.example.subway_kiosk.R
import com.example.subway_kiosk.util.Sandwich
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.system.exitProcess


class ManagerSauceSelect : AppCompatActivity()
{
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("recommend")
    var selectedSandwich = Sandwich.menuList.get(0).deepCopy()

    var sauceBtnList = arrayListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sauce_select)

        sauceBtnList.add(findViewById(R.id.sweetOnion))
        sauceBtnList.add(findViewById(R.id.honeyMustard))
        sauceBtnList.add(findViewById(R.id.sweetChili))
        sauceBtnList.add(findViewById(R.id.smokeBBQ))
        sauceBtnList.add(findViewById(R.id.hotChilli))
        sauceBtnList.add(findViewById(R.id.chipotle))
        sauceBtnList.add(findViewById(R.id.mustard))
        sauceBtnList.add(findViewById(R.id.horseradish))
        sauceBtnList.add(findViewById(R.id.italianDressing))
        sauceBtnList.add(findViewById(R.id.redWine))
        sauceBtnList.add(findViewById(R.id.ranch))
        sauceBtnList.add(findViewById(R.id.mayonnaise))
        sauceBtnList.add(findViewById(R.id.oliveOil))
        sauceBtnList.add(findViewById(R.id.salt))
        sauceBtnList.add(findViewById(R.id.pepper))
        sauceBtnList.add(findViewById(R.id.noSauce))

        if (intent.hasExtra("selectedSandwich"))
        {
            selectedSandwich = intent.getParcelableExtra<Sandwich>("selectedSandwich")!!
            println(selectedSandwich.toString())

            updateBtnStatus()
        }

        val prevBtn = findViewById<Button>(R.id.breadCheeseSelect_toPrev)
        prevBtn.setOnClickListener {
            finish()
        }

        val passAllBtn = findViewById<Button>(R.id.breadCheeseSelect_passAll)
        passAllBtn.visibility = View.INVISIBLE

        val passBtn = findViewById<Button>(R.id.breadCheeseSelect_pass)
        passBtn.setText("완료")
        passBtn.setBackgroundResource(R.drawable.corner_button3_selected)
        passBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
        passBtn.setOnClickListener {
            val nextIntent = Intent(this@ManagerSauceSelect, ManagerActivity::class.java)
            stockRef.child(intent.getStringExtra("menuNum")!!).setValue(intent.getParcelableExtra<Sandwich>("selectedSandwich"))
            Toast.makeText(this@ManagerSauceSelect, "추천 메뉴가 변경되었습니다.", Toast.LENGTH_LONG).show()
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
        else Toast.makeText(this@ManagerSauceSelect, "최대 5가지의 소스를 선택 가능합니다", Toast.LENGTH_SHORT).show()

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