package com.example.subway_kiosk.util

import android.view.View
import android.widget.Button
import com.example.subway_kiosk.R
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

    fun getSandwichID(): Int
    {
        return sandwichID
    }

    fun getSandwichName(): String
    {
        return sandwichName
    }

    fun getBreadID(): Int
    {
        return breadID
    }

    fun getBreadName(): String
    {
        return breadName
    }

    fun getCheeseID(): Int
    {
        return cheeseID
    }

    fun getCheeseName(): String
    {
        return cheeseName
    }

    fun getVege(): Vegetable
    {
        return vege.deepCopy()
    }

    fun getSauceIDList(): ArrayList<Int>
    {
        return sauceID
    }

    fun getSauceName(sauceID: Int): String
    {
        return sauceNameSet.getValue(sauceID)
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

    fun deepCopy(): Sandwich
    {
        var sauceCopy = ArrayList<Int>()
        sauceCopy.addAll(sauceID)

        return Sandwich(
            sandwichID,
            sandwichName,
            breadID,
            cheeseID,
            vege.deepCopy(),
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

    companion object
    {
        val hashMapIDtoXML = hashMapOf<Int, Int>(
            1 to R.drawable.meat_select_bacon_xml,
            2 to R.drawable.meat_select_tuna_xml,
            3 to R.drawable.meat_select_bacon_xml,
            4 to R.drawable.meat_select_avocado_xml,
            5 to R.drawable.meat_select_vege_xml,
            6 to R.drawable.meat_select_bacon_xml,
            7 to R.drawable.meat_select_tuna_xml,
            8 to R.drawable.meat_select_bacon_xml,
            9 to R.drawable.meat_select_avocado_xml,
            10 to R.drawable.meat_select_vege_xml,
            11 to R.drawable.meat_select_bacon_xml,
            12 to R.drawable.meat_select_tuna_xml,
            13 to R.drawable.meat_select_bacon_xml,
            14 to R.drawable.meat_select_avocado_xml,
            15 to R.drawable.meat_select_vege_xml,
            16 to R.drawable.meat_select_bacon_xml,
            17 to R.drawable.meat_select_tuna_xml,
            18 to R.drawable.meat_select_bacon_xml,
        )

        val menuList = arrayListOf<Sandwich>(
            Sandwich(
                0,
                "dummy",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, true),
                arrayListOf<Int>()
            ), Sandwich(
                1,
                "이탈리안 비엠티",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                2,
                "써브웨이 클럽",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                3,
                "햄",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                4,
                "치킨 베이컨 아보카도",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                5,
                "비엘티",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                6,
                "스파이시 이탈리안",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                7,
                "로티세리 바비큐 치킨",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                8,
                "치킨 슬라이스",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                9,
                "풀드 포크 바비큐",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                10,
                "K-바비큐",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                11,
                "스파이시 바비큐",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                12,
                "로스트 치킨",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                13,
                "스테이크 & 치즈",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                14,
                "치킨 데리야끼",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                15,
                "쉬림프",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                16,
                "에그마요",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                17,
                "참치",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            ), Sandwich(
                18,
                "베지",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(301, 101)
            )
        )

        fun getMenuListByMeat(v: View): HashMap<Int, ArrayList<Sandwich>>
        {
            val menuList = hashMapOf<Int, ArrayList<Sandwich>>(
                v.findViewById<Button>(R.id.meatSelect_ham).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(1).deepCopy(), Sandwich.menuList.get(2).deepCopy(), Sandwich.menuList.get(3).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_bacon).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(2).deepCopy(), Sandwich.menuList.get(4).deepCopy(), Sandwich.menuList.get(5).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_pepperoni).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(1).deepCopy(), Sandwich.menuList.get(6).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_salami).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(1).deepCopy(), Sandwich.menuList.get(6).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_rotisserie_chicken).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(7).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_chicken_breast_ham).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(2).deepCopy(), Sandwich.menuList.get(4).deepCopy(), Sandwich.menuList.get(8).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_pulled_pork_bbq).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(9).deepCopy(), Sandwich.menuList.get(10).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_spicy_bbq).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(11).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_chicken_breast).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(12).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_steak).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(13).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_chicken_teriyaki).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(14).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_shrimp).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(15).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_egg_mayo).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(16).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_tuna).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(17).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_avocado).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(7).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_vege).id to arrayListOf<Sandwich>(
                    Sandwich.menuList.get(18).deepCopy()
                )
            )
            return menuList
        }
    }
}
