package com.example.subway_kiosk.manager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.ManagerActivity
import com.example.subway_kiosk.databinding.ManagerStockSauceBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.example.subway_kiosk.util.Stock

class ManagerStockSauce : AppCompatActivity()
{
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("stock")
    var sauce_num = Array<Int?>(16,{0});
    var sauce_str = arrayOf("chipotle","honey","horseradish","hot_chili","italianDressing","mayonnaise",
    "mustard","olive","pepper","ranch","redWine","salt","smoke","sweet_chili","sweet_onion")
    var stock_ary = Array<TextView?>(16,{null})
    var cnt: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ManagerStockSauceBinding.inflate(layoutInflater)

        setContentView(binding.root)
        stock_ary[0] = binding.chipotleStock
        stock_ary[1] = binding.honeyStock
        stock_ary[2] = binding.horseradishStock
        stock_ary[3] = binding.hotChiliStock
        stock_ary[4] = binding.italianDressingStock
        stock_ary[5] = binding.mayonnaiseStock
        stock_ary[6] = binding.mustardStock
        stock_ary[7] = binding.oliveStock
        stock_ary[8] = binding.pepperStock
        stock_ary[9] = binding.ranchStock
        stock_ary[10] = binding.redWineStock
        stock_ary[11] = binding.saltStock
        stock_ary[12] = binding.smokeStock
        stock_ary[13] = binding.sweetChiliStock
        stock_ary[14] = binding.sweetOnionStock

        stockRef.child("sauce").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var sauce : Stock? = item.getValue(Stock::class.java)
                    sauce_num[cnt] = sauce?.num;
                    stock_ary[cnt]?.text = sauce_num[cnt].toString().plus("개");
                    cnt++;
                }
                cnt = 0;
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        binding.home.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockSauce, ManagerActivity::class.java)
            startActivity(nextIntent)
        }
        binding.manageMeat.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockSauce, ManagerStockMeat::class.java)
            startActivity(nextIntent)
        }
        binding.manageBread.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockSauce, ManagerStockBread::class.java)
            startActivity(nextIntent)
        }
        binding.manageVege.setOnClickListener{
            val nextIntent = Intent(this@ManagerStockSauce, ManagerStockVege::class.java)
            startActivity(nextIntent)
        }
        binding.chipotleMinus.setOnClickListener{ minus(0) }
        binding.chipotlePlus.setOnClickListener{plus(0)}
        binding.honeyMinus.setOnClickListener{ minus(1) }
        binding.honeyPlus.setOnClickListener{plus(1)}
        binding.horseradishMinus.setOnClickListener{ minus(2) }
        binding.horseradishPlus.setOnClickListener{plus(2)}
        binding.hotChiliMinus.setOnClickListener{ minus(3) }
        binding.hotChiliPlus.setOnClickListener{plus(3)}
        binding.italianDressingMinus.setOnClickListener{ minus(4) }
        binding.italianDressingPlus.setOnClickListener{plus(4)}
        binding.mayonnaiseMinus.setOnClickListener{ minus(5) }
        binding.mayonnaisePlus.setOnClickListener{plus(5)}
        binding.mustardMinus.setOnClickListener{ minus(6) }
        binding.mustardPlus.setOnClickListener{plus(6)}
        binding.oliveMinus.setOnClickListener{ minus(7) }
        binding.olivePlus.setOnClickListener{plus(7)}
        binding.pepperMinus.setOnClickListener{ minus(8) }
        binding.pepperPlus.setOnClickListener{plus(8)}
        binding.ranchMinus.setOnClickListener{ minus(9) }
        binding.ranchPlus.setOnClickListener{plus(9)}
        binding.redWineMinus.setOnClickListener{ minus(10) }
        binding.redWinePlus.setOnClickListener{plus(10)}
        binding.saltMinus.setOnClickListener{ minus(11) }
        binding.saltPlus.setOnClickListener{plus(11)}
        binding.smokeMinus.setOnClickListener{ minus(12) }
        binding.smokePlus.setOnClickListener{plus(12)}
        binding.sweetChiliMinus.setOnClickListener{ minus(13) }
        binding.sweetChiliPlus.setOnClickListener{plus(13)}
        binding.sweetOnionMinus.setOnClickListener{ minus(14) }
        binding.sweetOnionPlus.setOnClickListener{plus(14)}
    }
    fun minus(i: Int){
        sauce_num[i] = sauce_num[i]?.minus(1)
        stockRef.child("sauce").child(sauce_str[i]).child("num").setValue(sauce_num[i])
    }
    fun plus(i:Int){
        sauce_num[i] = sauce_num[i]?.plus(1)
        stockRef.child("sauce").child(sauce_str[i]).child("num").setValue(sauce_num[i])
    }
}

