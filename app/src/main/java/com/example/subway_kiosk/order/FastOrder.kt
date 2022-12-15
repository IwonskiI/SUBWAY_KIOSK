package com.example.subway_kiosk.order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.databinding.ManagerEditBinding
import com.example.subway_kiosk.util.Sandwich
import com.example.subway_kiosk.util.SandwichInfo
import com.example.subway_kiosk.util.Vegetable
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
        var sand = arrayListOf<Sandwich>()
        sand.add(Sandwich.menuList.get(0).deepCopy())
        sand.add(Sandwich.menuList.get(0).deepCopy())
        sand.add(Sandwich.menuList.get(0).deepCopy())


        setContentView(binding.root)
        shopping_cart = intent.getParcelableArrayListExtra<Sandwich>("shoppingCart")!!

        stockRef.child(string_arr[0]).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var rec : SandwichInfo? = snapshot.getValue(SandwichInfo::class.java)

                if (rec != null) {
                    sand[0].setSandwichID(rec.sandwichID)
                    sand[0].setSandwichName(rec.sandwichName)
                    sand[0].setBreadID(rec.breadID)
                    sand[0].setCheeseID(rec.cheeseID)
                    sand[0].setSet(false)
                    sand[0].setCookieID(0)
                    sand[0].setPrice(rec.price)
                    sand[0].setVege(Vegetable(rec.vege.lettuce,rec.vege.tomato,rec.vege.cucumber,rec.vege.pepper,rec.vege.onion,rec.vege.pickle,rec.vege.olive,rec.vege.jalapeno,rec.vege.avocado))
                    for(sauce in rec.sauceID)
                        sand[0].setSauce(sauce)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )
        stockRef.child(string_arr[1]).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var rec : SandwichInfo? = snapshot.getValue(SandwichInfo::class.java)
                if (rec != null) {
                    sand[1].setSandwichID(rec.sandwichID)
                    sand[1].setSandwichName(rec.sandwichName)
                    sand[1].setBreadID(rec.breadID)
                    sand[1].setCheeseID(rec.cheeseID)
                    sand[1].setSet(false)
                    sand[1].setCookieID(0)
                    sand[1].setPrice(rec.price)
                    sand[1].setVege(Vegetable(rec.vege.lettuce,rec.vege.tomato,rec.vege.cucumber,rec.vege.pepper,rec.vege.onion,rec.vege.pickle,rec.vege.olive,rec.vege.jalapeno,rec.vege.avocado))
                    for(sauce in rec.sauceID)
                        sand[0].setSauce(sauce)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )
        stockRef.child(string_arr[2]).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var rec : SandwichInfo? = snapshot.getValue(SandwichInfo::class.java)
                if (rec != null) {
                    sand[2].setSandwichID(rec.sandwichID)
                    sand[2].setSandwichName(rec.sandwichName)
                    sand[2].setBreadID(rec.breadID)
                    sand[2].setCheeseID(rec.cheeseID)
                    sand[2].setSet(false)
                    sand[2].setCookieID(0)
                    sand[2].setPrice(rec.price)
                    sand[2].setVege(Vegetable(rec.vege.lettuce,rec.vege.tomato,rec.vege.cucumber,rec.vege.pepper,rec.vege.onion,rec.vege.pickle,rec.vege.olive,rec.vege.jalapeno,rec.vege.avocado))
                    for(sauce in rec.sauceID)
                        sand[0].setSauce(sauce)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )


        binding.recommend.setOnClickListener{
            val nextIntent = Intent(this@FastOrder, CookieSelect::class.java)

            nextIntent.putExtra("selectedSandwich", sand[0])
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }
        binding.cheap.setOnClickListener{
            val nextIntent = Intent(this@FastOrder, CookieSelect::class.java)
            nextIntent.putExtra("selectedSandwich", sand[1])
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }
        binding.most.setOnClickListener{
            val nextIntent = Intent(this@FastOrder, CookieSelect::class.java)
            nextIntent.putExtra("selectedSandwich", sand[2])
            nextIntent.putExtra("shoppingCart",shopping_cart)
            startActivity(nextIntent)
        }
        binding.home.setOnClickListener{
            finish()
        }


    }
}