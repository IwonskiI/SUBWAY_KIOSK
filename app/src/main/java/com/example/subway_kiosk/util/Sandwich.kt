package com.example.subway_kiosk.util

import kotlin.system.exitProcess

class Sandwich(
    val sandwichID: Int,
    val sandwichName: String,
    var breadID: Int,
    var cheeseID: Int,
    var vege: Vegetable,
    var sauceID: ArrayList<Sauce>
)
{
    var breadName: String
    var cheeseName: String

    init
    {
        when (breadID)
        {
            1    -> breadName = "화이트"
            2    -> breadName = "허니오트"
            3    -> breadName = "위트"
            4    -> breadName = "파마산 오레가노"
            5    -> breadName = "하티"
            6    -> breadName = "플랫브레드"
            else ->
            {
                println("invalid bread ID")
                exitProcess(1)
            }
        }
        when (cheeseID)
        {
            1    -> cheeseName = "아메리칸 치즈"
            2    -> cheeseName = "모짜렐라 치즈"
            3    -> cheeseName = "슈레드 치즈"
            else ->
            {
                println("invalid cheese ID")
                exitProcess(1)
            }
        }
    }

    fun deepCopy(sandwich: Sandwich): Sandwich
    {
        return Sandwich(
            sandwich.sandwichID,
            sandwich.sandwichName,
            sandwich.breadID,
            sandwich.cheeseID,
            sandwich.vege,
            sandwich.sauceID
        )
    }

    fun showInfo()
    {
        println("sandwichName: " + sandwichName)
        println("sandwichID: " + sandwichID)
        println("breadID: " + breadID)
        println("breadName: " + breadName)
        println("cheeseID: " + cheeseID)
        println("cheeseName: " + cheeseName)
        println("vege: " + vege.showInfo())
        println("sauce(ArrayList): " + sauceID.forEach { print('[' + it.sauceNameKor + "] ") })
    }
}