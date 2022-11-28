package com.example.subway_kiosk.util

import kotlin.system.exitProcess

class Sandwich(
    private val sandwichID: Int,
    private val sandwichName: String,
    private var breadID: Int,
    private var cheeseID: Int,
    private var vege: Vegetable,
    private var sauceID: ArrayList<Int>
)
{
    private lateinit var breadName: String
    private lateinit var cheeseName: String
    private var sauceNameSet = HashMap<Int, String>()

    init
    {
        setBreadName()
        setCheeseName()

        // 10X: 달콤, 20X: 매콤, 30X: 새콤, 40X: 고소, 50X: 기타
        sauceNameSet.put(101, "스위트 어니언")
        sauceNameSet.put(102, "허니 머스타드")
        sauceNameSet.put(103, "스위트 칠리")
        sauceNameSet.put(104, "스모크 바비큐")
        sauceNameSet.put(201, "핫 칠리")
        sauceNameSet.put(202, "사우스웨스트 치폴레")
        sauceNameSet.put(203, "머스타드")
        sauceNameSet.put(204, "홀스래디쉬")
        sauceNameSet.put(301, "이탈리안 드레싱")
        sauceNameSet.put(302, "레드와인 식초")
        sauceNameSet.put(401, "랜치")
        sauceNameSet.put(402, "마요네즈")
        sauceNameSet.put(501, "올리브 오일")
        sauceNameSet.put(502, "소금")
        sauceNameSet.put(503, "후추")
    }

    fun setBreadID(newID: Int)
    {
        breadID = newID
        setBreadName()
    }

    fun setCheeseID(newID: Int)
    {
        cheeseID = newID
        setCheeseName()
    }

    fun setVege(newVege: Vegetable)
    {
        vege = newVege.deepCopy()
    }

    fun setVege(vegeName: String, boolean: Boolean)
    {
        when (vegeName)
        {
            "양상추"  -> vege.changeLettuce(boolean)
            "토마토"  -> vege.changeTomato(boolean)
            "오이"   -> vege.changeCucumter(boolean)
            "피망"   -> vege.changePepper(boolean)
            "양파"   -> vege.changeOnion(boolean)
            "피클"   -> vege.changePickle(boolean)
            "올리브"  -> vege.changeOlive(boolean)
            "할라피뇨" -> vege.changeJalapeno(boolean)
            "아보카도" -> vege.changeAvocado(boolean)
            else   ->
            {
                println("invalid vegetable name")
                exitProcess(1)
            }
        }
    }

    fun setVege(vegeID: Int, boolean: Boolean)
    {
        when (vegeID)
        {
            1    -> vege.changeLettuce(boolean)
            2    -> vege.changeTomato(boolean)
            3    -> vege.changeCucumter(boolean)
            4    -> vege.changePepper(boolean)
            5    -> vege.changeOnion(boolean)
            6    -> vege.changePickle(boolean)
            7    -> vege.changeOlive(boolean)
            8    -> vege.changeJalapeno(boolean)
            9    -> vege.changeAvocado(boolean)
            else ->
            {
                println("invalid vegetable ID")
                exitProcess(1)
            }
        }
    }

    fun addSauce(newSauceID: Int): Boolean
    {
        return sauceID.add(newSauceID)
    }

    fun removeSauce(curSauceID: Int): Boolean
    {
        return sauceID.remove(curSauceID)
    }

    private fun setBreadName()
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
    }

    private fun setCheeseName()
    {
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
        var sauceCopy = ArrayList<Int>()
        sauceCopy.addAll(sandwich.sauceID)

        return Sandwich(
            sandwich.sandwichID,
            sandwich.sandwichName,
            sandwich.breadID,
            sandwich.cheeseID,
            sandwich.vege.deepCopy(),
            sauceCopy
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
        vege.showInfo()
        print("sauce(ArrayList): ")
        sauceID.forEach { print("[" + sauceNameSet.getValue(it) + "] ") }
        println("")
    }
}