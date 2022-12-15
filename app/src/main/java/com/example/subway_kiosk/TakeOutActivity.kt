package com.example.subway_kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TakeOutActivity : AppCompatActivity()
{
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("order_num")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.takeout_select)

        val tohere_btn = findViewById<Button>(R.id.to_here)
        val togo_btn = findViewById<TextView>(R.id.to_go)
        val manager_login = findViewById<Button>(R.id.logo_manager)

        tohere_btn.setOnClickListener{
            if(intent.hasExtra("order")){
                stockRef.child("num").setValue(intent.getIntExtra("order",0))
            }
            val nextIntent = Intent(this@TakeOutActivity, MainActivity::class.java)
            startActivity(nextIntent)
        }

        togo_btn.setOnClickListener{
            if(intent.hasExtra("order")){
                stockRef.child("num").setValue(intent.getIntExtra("order",0))
            }
            val nextIntent = Intent(this@TakeOutActivity, MainActivity::class.java)
            startActivity(nextIntent)
        }

        manager_login.setOnLongClickListener(View.OnLongClickListener {
            val nextIntent = Intent(this@TakeOutActivity, ManagerActivity::class.java)
            startActivity(nextIntent)
            true
        })
    }
}