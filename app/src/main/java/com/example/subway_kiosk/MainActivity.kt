package com.example.subway_kiosk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.subway_kiosk.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sandwichTest: Sandwich

        var sauceList = ArrayList<Sauce>()
        sauceList.add(Sauce(102))
        sauceList.add(Sauce((201)))

        sandwichTest = Sandwich(
            23, "테스트", 3, 3, Vegetable(
                lettuce = false,
                tomato = false,
                cucumber = false,
                pepper = false,
                onion = true,
                pickle = true,
                olive = true,
                jalapeno = false,
                avacado = false
            ), sauceList
        )
        sandwichTest.showInfo()
    }


}