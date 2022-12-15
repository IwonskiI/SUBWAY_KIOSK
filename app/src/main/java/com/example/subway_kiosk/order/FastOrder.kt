package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.databinding.ManagerEditBinding
import com.example.subway_kiosk.util.Sandwich
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FastOrder : AppCompatActivity(){
    var RootRef = Firebase.database.reference
    var shopping_cart = arrayListOf<Sandwich?>()
    var stockRef = RootRef.child("recommend")
    var string_arr = arrayOf("menu1","menu2","menu3");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ManagerEditBinding.inflate(layoutInflater)

        setContentView(binding.root)
        shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!


        binding.recommend.setOnClickListener{
            val nextIntent = Intent(this@FastOrder, CookieSelect::class.java)
            stockRef.child(string_arr[0]).addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (item in snapshot.children) {
                        var rec : Sandwich? = item.getValue(Sandwich::class.java)
                        nextIntent.putExtra("selectedSandwich", rec!!)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    print(error.message)
                }
            } )
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }
        binding.cheap.setOnClickListener{
            val nextIntent = Intent(this@FastOrder, CookieSelect::class.java)
            stockRef.child(string_arr[1]).addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (item in snapshot.children) {
                        var rec : Sandwich? = item.getValue(Sandwich::class.java)
                        nextIntent.putExtra("selectedSandwich", rec)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    print(error.message)
                }
            } )
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }
        binding.most.setOnClickListener{
            val nextIntent = Intent(this@FastOrder, CookieSelect::class.java)
            stockRef.child(string_arr[2]).addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (item in snapshot.children) {
                        var rec : Sandwich? = item.getValue(Sandwich::class.java)
                        nextIntent.putExtra("selectedSandwich", rec)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    print(error.message)
                }
            } )
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }
        binding.home.setOnClickListener{
            finish()
        }


    }
}