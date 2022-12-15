package com.example.subway_kiosk.manager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.databinding.ManagerEditBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ManagerEditMenu : AppCompatActivity(){
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("recommend")
    var string_arr = arrayOf("menu1","menu2","menu3");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ManagerEditBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.recommend.setOnClickListener{
            val nextIntent = Intent(this@ManagerEditMenu, ManagerMeatSelect::class.java)
            nextIntent.putExtra("menuNum", string_arr[0])
            startActivity(nextIntent)
        }
        binding.cheap.setOnClickListener{
            val nextIntent = Intent(this@ManagerEditMenu, ManagerMeatSelect::class.java)
            nextIntent.putExtra("menuNum", string_arr[1])
            startActivity(nextIntent)
        }
        binding.most.setOnClickListener{
            val nextIntent = Intent(this@ManagerEditMenu, ManagerMeatSelect::class.java)
            nextIntent.putExtra("menuNum", string_arr[2])
            startActivity(nextIntent)
        }
        binding.home.setOnClickListener{
            finish()
        }


    }
}