package com.example.subway_kiosk

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.subway_kiosk.databinding.ActivityMainBinding
import com.example.subway_kiosk.databinding.PaymentBinding
import com.example.subway_kiosk.order.FastOrder
import com.example.subway_kiosk.order.MeatSelect
import com.example.subway_kiosk.util.Sandwich
import com.example.subway_kiosk.util.Stock
import com.example.subway_kiosk.util.cartAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class Payment : AppCompatActivity()
{
    var shopping_cart = arrayListOf<Sandwich?>()
    var total: Int = 0
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("stock")
    var saleRef = RootRef.child("sale_status")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val binding = PaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cash_btn = findViewById<Button>(R.id.pay_cash)
        val card_btn = findViewById<Button>(R.id.pay_card)
        val pay_btn = findViewById<Button>(R.id.pay_pay)
        var mainnum = HashMap<String, Int>()
        var meat_str = arrayOf("avocado","bacon","chicken_breast","chicken_breast_ham","chicken_teriyaki","egg_mayo",
            "ham","pepperoni","pulled_pork","rotisserie","salami","shrimp","spicy_bbq","steak","tuna")
        var cnt: Int = 0
        var breadnum = HashMap<String, Int>()
        var bread_str = arrayOf("flat","hati","honey","pamasan","wheet","white")
        var cheesenum = HashMap<String, Int>()
        var cheese_str = arrayOf("american","mozzarella","shred")
        var saucenum = HashMap<String, Int>()
        var sauce_str = arrayOf("chipotle","honey","horseradish","hot_chili","italianDressing","mayonnaise",
            "mustard","olive","pepper","ranch","redWine","salt","smoke","sweet_chili","sweet_onion")
        var vegenum = HashMap<String, Int>()
        var vege_str = arrayOf("cucumber","jalapeno","lettuce","olive","onion","pickle",
            "pimento","tomato")

        stockRef.child("meat").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var meat : Stock? = item.getValue(Stock::class.java)
                    mainnum[meat_str[cnt]] = meat!!.num
                    cnt++
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )
        stockRef.child("bread").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var meat : Stock? = item.getValue(Stock::class.java)
                    breadnum[bread_str[cnt]] = meat!!.num
                    cnt++
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
                    var meat : Stock? = item.getValue(Stock::class.java)
                    cheesenum[cheese_str[cnt]] = meat!!.num
                    cnt++
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )
        stockRef.child("sauce").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var meat : Stock? = item.getValue(Stock::class.java)
                    saucenum[sauce_str[cnt]] = meat!!.num
                    cnt++
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )
        stockRef.child("vege").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var meat : Stock? = item.getValue(Stock::class.java)
                    vegenum[vege_str[cnt]] = meat!!.num
                    cnt++
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        cash_btn.setOnClickListener {
            for(item in shopping_cart){
                var setcheck = item!!.getSetBool()
                var set = ""
                if (setcheck == true) set = "SET"
                else set = "SINGLE"
                val tempMap = mapOf("menu" to item!!.getSandwichName(), "price" to item.getPrice(), "date" to LocalDate.now().toString(), "type" to set)
                saleRef.push().setValue(tempMap)
                var MainHM = item.getMainbyID(item.getSandwichID())
                for ((key, value) in MainHM){
                    stockRef.child("meat").child(key).child("num").setValue(mainnum[key]!!.minus(value))
                }
                var BreadHM = item.getBreadNameByID(item.getBreadID())
                stockRef.child("bread").child(BreadHM).child("num").setValue(breadnum[BreadHM]!!.minus(1))
                var cheeseHM = item.getCheeseNameByID(item.getCheeseID())
                stockRef.child("cheese").child(cheeseHM).child("num").setValue(cheesenum[cheeseHM]!!.minus(1))
                for(va in item.getSauceIDList()){
                    var sauceHM = item.getSauceNameByID(va)
                    stockRef.child("sauce").child(sauceHM).child("num").setValue(saucenum[sauceHM]!!.minus(1))
                }
                var vege = item.getVege()
                var check = false
                var cnt2 = 0
                if(vege.getCucumber()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("cucumber").child("num").setValue(vegenum["cucumber"]!!.minus(cnt2))
                if(vege.getJalapeno()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("jalapeno").child("num").setValue(vegenum["jalapeno"]!!.minus(cnt2))
                if(vege.getLettuce()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("lettuce").child("num").setValue(vegenum["lettuce"]!!.minus(cnt2))
                if(vege.getOlive()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("olive").child("num").setValue(vegenum["olive"]!!.minus(cnt2))
                if(vege.getOnion()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("onion").child("num").setValue(vegenum["onion"]!!.minus(cnt2))
                if(vege.getPickle()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("pickle").child("num").setValue(vegenum["pickle"]!!.minus(cnt2))
                if(vege.getPepper()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("pimento").child("num").setValue(vegenum["pimento"]!!.minus(cnt2))
                if(vege.getTomato()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("tomato").child("num").setValue(vegenum["tomato"]!!.minus(cnt2))
            }
            Toast.makeText(this@Payment, "현금 결제가 완료되었습니다.", Toast.LENGTH_LONG).show()
            val nextIntent = Intent(this@Payment, OrderComplete::class.java)
            nextIntent.putExtra("total",total)
            startActivity(nextIntent)
        }
        card_btn.setOnClickListener {
            for(item in shopping_cart){
                var setcheck = item!!.getSetBool()
                var set = ""
                if (setcheck == true) set = "SET"
                else set = "SINGLE"
                val tempMap = mapOf("menu" to item!!.getSandwichName(), "price" to item.getPrice(), "date" to LocalDate.now().toString(), "type" to set)
                saleRef.push().setValue(tempMap)
                var MainHM = item.getMainbyID(item.getSandwichID())
                for ((key, value) in MainHM){
                    stockRef.child("meat").child(key).child("num").setValue(mainnum[key]!!.minus(value))
                }
                var BreadHM = item.getBreadNameByID(item.getBreadID())
                stockRef.child("bread").child(BreadHM).child("num").setValue(breadnum[BreadHM]!!.minus(1))
                var cheeseHM = item.getCheeseNameByID(item.getCheeseID())
                stockRef.child("cheese").child(cheeseHM).child("num").setValue(cheesenum[cheeseHM]!!.minus(1))
                for(va in item.getSauceIDList()){
                    var sauceHM = item.getSauceNameByID(va)
                    stockRef.child("sauce").child(sauceHM).child("num").setValue(saucenum[sauceHM]!!.minus(1))
                }
                var vege = item.getVege()
                var check = false
                var cnt2 = 0
                if(vege.getCucumber()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("cucumber").child("num").setValue(vegenum["cucumber"]!!.minus(cnt2))
                if(vege.getJalapeno()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("jalapeno").child("num").setValue(vegenum["jalapeno"]!!.minus(cnt2))
                if(vege.getLettuce()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("lettuce").child("num").setValue(vegenum["lettuce"]!!.minus(cnt2))
                if(vege.getOlive()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("olive").child("num").setValue(vegenum["olive"]!!.minus(cnt2))
                if(vege.getOnion()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("onion").child("num").setValue(vegenum["onion"]!!.minus(cnt2))
                if(vege.getPickle()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("pickle").child("num").setValue(vegenum["pickle"]!!.minus(cnt2))
                if(vege.getPepper()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("pimento").child("num").setValue(vegenum["pimento"]!!.minus(cnt2))
                if(vege.getTomato()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("tomato").child("num").setValue(vegenum["tomato"]!!.minus(cnt2))
            }
            Toast.makeText(this@Payment, "카드 결제가 완료되었습니다.", Toast.LENGTH_LONG).show()
            val nextIntent = Intent(this@Payment, OrderComplete::class.java)
            nextIntent.putExtra("total",total)
            startActivity(nextIntent)
        }
        pay_btn.setOnClickListener {
            for(item in shopping_cart){
                var setcheck = item!!.getSetBool()
                var set = ""
                if (setcheck == true) set = "SET"
                else set = "SINGLE"
                val tempMap = mapOf("menu" to item!!.getSandwichName(), "price" to item.getPrice(), "date" to LocalDate.now().toString(), "type" to set)
                saleRef.push().setValue(tempMap)
                var MainHM = item.getMainbyID(item.getSandwichID())
                for ((key, value) in MainHM){
                    stockRef.child("meat").child(key).child("num").setValue(mainnum[key]!!.minus(value))
                }
                var BreadHM = item.getBreadNameByID(item.getBreadID())
                stockRef.child("bread").child(BreadHM).child("num").setValue(breadnum[BreadHM]!!.minus(1))
                var cheeseHM = item.getCheeseNameByID(item.getCheeseID())
                stockRef.child("cheese").child(cheeseHM).child("num").setValue(cheesenum[cheeseHM]!!.minus(1))
                for(va in item.getSauceIDList()){
                    var sauceHM = item.getSauceNameByID(va)
                    stockRef.child("sauce").child(sauceHM).child("num").setValue(saucenum[sauceHM]!!.minus(1))
                }
                var vege = item.getVege()
                var check = false
                var cnt2 = 0
                if(vege.getCucumber()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("cucumber").child("num").setValue(vegenum["cucumber"]!!.minus(cnt2))
                if(vege.getJalapeno()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("jalapeno").child("num").setValue(vegenum["jalapeno"]!!.minus(cnt2))
                if(vege.getLettuce()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("lettuce").child("num").setValue(vegenum["lettuce"]!!.minus(cnt2))
                if(vege.getOlive()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("olive").child("num").setValue(vegenum["olive"]!!.minus(cnt2))
                if(vege.getOnion()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("onion").child("num").setValue(vegenum["onion"]!!.minus(cnt2))
                if(vege.getPickle()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("pickle").child("num").setValue(vegenum["pickle"]!!.minus(cnt2))
                if(vege.getPepper()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("pimento").child("num").setValue(vegenum["pimento"]!!.minus(cnt2))
                if(vege.getTomato()) cnt2 = 1
                else cnt2 = 0
                stockRef.child("vege").child("tomato").child("num").setValue(vegenum["tomato"]!!.minus(cnt2))
            }
            Toast.makeText(this@Payment, "페이 결제가 완료되었습니다.", Toast.LENGTH_LONG).show()
            val nextIntent = Intent(this@Payment, OrderComplete::class.java)
            nextIntent.putExtra("total", total)
            startActivity(nextIntent)
        }

        if(intent.hasExtra("shoppingCart")){
            shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!
            for(item in shopping_cart){
                total += item!!.getPrice()
            }
            binding.total.text = "총 금액: " + total
            binding.shoppingCart.layoutManager= LinearLayoutManager(this)
            binding.shoppingCart.adapter= cartAdapter(shopping_cart)
            binding.shoppingCart.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        }

    }
}