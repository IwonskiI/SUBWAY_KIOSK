package com.example.subway_kiosk.manager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.databinding.ManagerStockBreadBinding
import com.example.subway_kiosk.databinding.ManagerStockMeatBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.example.subway_kiosk.util.Stock

class ManagerStockMeat : AppCompatActivity()
{
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("stock")
    var meat_num = Array<Int?>(16,{0});
    var meat_str = arrayOf("avocado","bacon","chicken_breast","chicken_breast_ham","chicken_teriyaki","egg_mayo",
    "ham","pepperoni","pulled_pork","rotisserie","salami","shrimp","spicy_bbq","steak","tuna")
    var stock_ary = Array<TextView?>(16,{null})
    var cnt: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ManagerStockMeatBinding.inflate(layoutInflater)

        setContentView(binding.root)
        stock_ary[0] = binding.avocadoStock
        stock_ary[1] = binding.baconStock
        stock_ary[2] = binding.chickenBreastStock
        stock_ary[3] = binding.chickenBreastHamStock
        stock_ary[4] = binding.chickenTeriyakiStock
        stock_ary[5] = binding.eggMayoStock
        stock_ary[6] = binding.hamStock
        stock_ary[7] = binding.pepperoniStock
        stock_ary[8] = binding.pulledPorkStock
        stock_ary[9] = binding.rotisserieStock
        stock_ary[10] = binding.salamiStock
        stock_ary[11] = binding.shrimpStock
        stock_ary[12] = binding.spicyBbqStock
        stock_ary[13] = binding.steakStock
        stock_ary[14] = binding.tunaStock

        stockRef.child("meat").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var meat : Stock? = item.getValue(Stock::class.java)
                    meat_num[cnt] = meat?.num;
                    stock_ary[cnt]?.text = meat_num[cnt].toString().plus("개");
                    cnt++;
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        binding.manageBread.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockMeat, ManagerStockBread::class.java)
            startActivity(nextIntent)
        }
        binding.manageVege.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockMeat, ManagerStockVege::class.java)
            startActivity(nextIntent)
        }
        binding.manageSauce.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockMeat, ManagerStockSauce::class.java)
            startActivity(nextIntent)
        }
        binding.avocadoMinus.setOnClickListener{ minus(0) }
        binding.avocadoPlus.setOnClickListener{plus(0)}
        binding.baconMinus.setOnClickListener{ minus(1) }
        binding.baconPlus.setOnClickListener{plus(1)}
        binding.chickenBreastMinus.setOnClickListener{ minus(2) }
        binding.chickenBreastPlus.setOnClickListener{plus(2)}
        binding.chickenBreastHamMinus.setOnClickListener{ minus(3) }
        binding.chickenBreastHamPlus.setOnClickListener{plus(3)}
        binding.chickenTeriyakiMinus.setOnClickListener{ minus(4) }
        binding.chickenTeriyakiPlus.setOnClickListener{plus(4)}
        binding.eggMayoMinus.setOnClickListener{ minus(5) }
        binding.eggMayoPlus.setOnClickListener{plus(5)}
        binding.hamMinus.setOnClickListener{ minus(6) }
        binding.hamPlus.setOnClickListener{plus(6)}
        binding.pepperoniMinus.setOnClickListener{ minus(7) }
        binding.pepperoniPlus.setOnClickListener{plus(7)}
        binding.pulledPorkMinus.setOnClickListener{ minus(8) }
        binding.pulledPorkPlus.setOnClickListener{plus(8)}
        binding.rotisserieMinus.setOnClickListener{ minus(9) }
        binding.rotisseriePlus.setOnClickListener{plus(9)}
        binding.salamiMinus.setOnClickListener{ minus(10) }
        binding.salamiPlus.setOnClickListener{plus(10)}
        binding.shrimpMinus.setOnClickListener{ minus(11) }
        binding.shrimpPlus.setOnClickListener{plus(11)}
        binding.spicyBbqMinus.setOnClickListener{ minus(12) }
        binding.spicyBbqPlus.setOnClickListener{plus(12)}
        binding.steakMinus.setOnClickListener{ minus(13) }
        binding.steakPlus.setOnClickListener{plus(13)}
        binding.tunaMinus.setOnClickListener{ minus(14) }
        binding.tunaPlus.setOnClickListener{plus(14)}
    }
    fun minus(i: Int){
        meat_num[i] = meat_num[i]?.minus(1)
        stockRef.child("meat").child(meat_str[i]).child("num").setValue(meat_num[i])
    }
    fun plus(i:Int){
        meat_num[i] = meat_num[i]?.plus(1)
        stockRef.child("meat").child(meat_str[i]).child("num").setValue(meat_num[i])
    }
}

