package com.example.subway_kiosk.manager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.databinding.ManagerStockBreadBinding
import com.example.subway_kiosk.databinding.ManagerStockMeatBinding
import com.example.subway_kiosk.databinding.ManagerStockVegeBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.example.subway_kiosk.util.Stock

class ManagerStockVege : AppCompatActivity()
{
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("stock")
    var vege_num = Array<Int?>(9,{0});
    var vege_str = arrayOf("cucumber","jalapeno","lettuce","olive","onion","pickle",
    "pimento","tomato")
    var stock_ary = Array<TextView?>(9,{null})
    var cnt: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ManagerStockVegeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        stock_ary[0] = binding.cucumberStock
        stock_ary[1] = binding.jalapenoStock
        stock_ary[2] = binding.lettuceStock
        stock_ary[3] = binding.oliveStock
        stock_ary[4] = binding.onionStock
        stock_ary[5] = binding.pickleStock
        stock_ary[6] = binding.pimentoStock
        stock_ary[7] = binding.tomatoStock

        stockRef.child("vege").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var vege : Stock? = item.getValue(Stock::class.java)
                    vege_num[cnt] = vege?.num;
                    stock_ary[cnt]?.text = vege_num[cnt].toString().plus("개");
                    cnt++;
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        binding.manageMeat.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockVege, ManagerStockMeat::class.java)
            startActivity(nextIntent)
        }

        binding.manageBread.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockVege, ManagerStockBread::class.java)
            startActivity(nextIntent)
        }
        binding.manageSauce.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockVege, ManagerStockSauce::class.java)
            startActivity(nextIntent)
        }

        binding.cucumberMinus.setOnClickListener{ minus(0) }
        binding.cucumberPlus.setOnClickListener{plus(0)}
        binding.jalapenoMinus.setOnClickListener{ minus(1) }
        binding.jalapenoPlus.setOnClickListener{plus(1)}
        binding.lettuceMinus.setOnClickListener{ minus(2) }
        binding.lettucePlus.setOnClickListener{plus(2)}
        binding.oliveMinus.setOnClickListener{ minus(3) }
        binding.olivePlus.setOnClickListener{plus(3)}
        binding.onionMinus.setOnClickListener{ minus(4) }
        binding.onionPlus.setOnClickListener{plus(4)}
        binding.pickleMinus.setOnClickListener{ minus(5) }
        binding.picklePlus.setOnClickListener{plus(5)}
        binding.pimentoMinus.setOnClickListener{ minus(6) }
        binding.pimentoPlus.setOnClickListener{plus(6)}
        binding.tomatoMinus.setOnClickListener{ minus(7) }
        binding.tomatoPlus.setOnClickListener{plus(7)}
    }
    fun minus(i: Int){
        vege_num[i] = vege_num[i]?.minus(1)
        stockRef.child("vege").child(vege_str[i]).child("num").setValue(vege_num[i])
    }
    fun plus(i:Int){
        vege_num[i] = vege_num[i]?.plus(1)
        stockRef.child("vege").child(vege_str[i]).child("num").setValue(vege_num[i])
    }
}

