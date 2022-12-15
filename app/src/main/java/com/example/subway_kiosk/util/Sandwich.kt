package com.example.subway_kiosk.util

import android.os.Parcelable
import android.view.View
import android.widget.Button
import com.example.subway_kiosk.R
import kotlinx.android.parcel.Parcelize
import kotlin.system.exitProcess

@Parcelize
class Sandwich(
    private var sandwichID: Int,
    private var sandwichName: String,
    private var breadID: Int,
    private var cheeseID: Int,
    private var vege: Vegetable,
    private var sauceID: ArrayList<Int>,
    private var setSel: Boolean,
    private var cookieID: Int,
    private var price: Int
) : Parcelable
{


    private lateinit var breadName: String
    private lateinit var cheeseName: String
    private var sauceNameSet = HashMap<Int, String>()
    private lateinit var cookieName: String
    private lateinit var setName: String

    init
    {
        setBreadName()
        setCheeseName()
        setCookieName()
        setSetName()

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

    fun getSetBool(): Boolean
    {
        return setSel
    }

    fun getCookieID(): Int
    {
        return cookieID
    }

    fun getCookieName(): String
    {
        return cookieName
    }

    fun getPrice(): Int
    {
        return price
    }

    fun setSandwichID(newID: Int)
    {
        sandwichID = newID
    }

    fun setSandwichName(name: String)
    {
        sandwichName = name
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
            "양상추"  -> vege.setLettuce(boolean)
            "토마토"  -> vege.setTomato(boolean)
            "오이"   -> vege.setCucumter(boolean)
            "피망"   -> vege.setPepper(boolean)
            "양파"   -> vege.setOnion(boolean)
            "피클"   -> vege.setPickle(boolean)
            "올리브"  -> vege.setOlive(boolean)
            "할라피뇨" -> vege.setJalapeno(boolean)
            "아보카도" -> vege.setAvocado(boolean)
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
            1    -> vege.setLettuce(boolean)
            2    -> vege.setTomato(boolean)
            3    -> vege.setCucumter(boolean)
            4    -> vege.setPepper(boolean)
            5    -> vege.setOnion(boolean)
            6    -> vege.setPickle(boolean)
            7    -> vege.setOlive(boolean)
            8    -> vege.setJalapeno(boolean)
            9    -> vege.setAvocado(boolean)
            else ->
            {
                println("invalid vegetable ID")
                exitProcess(1)
            }
        }
    }

    fun changeVege(vegeID: Int)
    {
        when (vegeID)
        {
            1    -> vege.changeLettuce()
            2    -> vege.changeTomato()
            3    -> vege.changeCucumter()
            4    -> vege.changePepper()
            5    -> vege.changeOnion()
            6    -> vege.changePickle()
            7    -> vege.changeOlive()
            8    -> vege.changeJalapeno()
            9    -> vege.changeAvocado()
            else ->
            {
                println("invalid vegetable ID")
                exitProcess(1)
            }
        }
    }

    fun setSauce(SauceID: Int){
        sauceID.add(SauceID)
    }

    fun addSauce(newSauceID: Int): Boolean
    {
        return sauceID.add(newSauceID)
    }

    fun removeSauce(curSauceID: Int): Boolean
    {
        return sauceID.remove(curSauceID)
    }

    fun setSet(setCheck: Boolean)
    {
        setSel = setCheck
    }

    fun setCookieID(newID: Int)
    {
        cookieID = newID
        setCookieName()
    }

    fun setPrice(setPrice : Int){
        price = setPrice
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

    private fun setSetName()
    {
        when (setSel)
        {
            true  -> setName = "세트(쿠키+음료)"
            false -> setName = "단품(선택 없음)"
        }
    }

    private fun setCookieName()
    {
        when (cookieID)
        {
            0    -> cookieName = "선택 안함"
            1    -> cookieName = "더블 초코칩"
            2    -> cookieName = "초코칩"
            3    -> cookieName = "오트밀"
            4    -> cookieName = "라즈베리"
            5    -> cookieName = "마카다미아"
            else ->
            {
                println("invalid cookie ID")
                exitProcess(1)
            }
        }
    }

    fun deepCopy(): Sandwich
    {
        var sauceCopy = ArrayList<Int>()
        sauceCopy.addAll(sauceID)

        return Sandwich(
            sandwichID, sandwichName, breadID, cheeseID, vege.deepCopy(), sauceCopy, setSel, cookieID, price
        )
    }

    override fun toString(): String
    {
        return buildString {
            appendLine("sandwichName: " + sandwichName)
            appendLine("sandwichID: " + sandwichID)
            appendLine("breadID: " + breadID)
            appendLine("breadName: " + breadName)
            appendLine("cheeseID: " + cheeseID)
            appendLine("cheeseName: " + cheeseName)
            appendLine(vege.toString())
            append("sauce(ArrayList): ")
            sauceID.forEach { append("[" + sauceNameSet.getValue(it) + "] ") }
            appendLine()
            appendLine("setSelected: " + setSel)
            appendLine("setCheck: " + setName)
            appendLine("CookieId: " + cookieID)
            appendLine("CookieName: " + cookieName)
        }
    }

    fun showInfo()
    {
        println(toString())
    }

    companion object
    {
        val hashMapIDtoXML = hashMapOf<Int, Int>(
            1 to R.drawable.sandwich_italian_bmt_xml,
            2 to R.drawable.sandwich_subway_club_xml,
            3 to R.drawable.sandwich_ham_xml,
            4 to R.drawable.sandwich_chicken_bacon_xml,
            5 to R.drawable.sandwich_blt_xml,
            6 to R.drawable.sandwich_spicy_italian_xml,
            7 to R.drawable.sandwich_rotisserie_chicken_xml,
            8 to R.drawable.sandwich_chicken_slice_xml,
            9 to R.drawable.sandwich_pulled_pork_xml,
            10 to R.drawable.sandwich_k_bbq_xml,
            11 to R.drawable.sandwich_spicy_bbq_xml,
            12 to R.drawable.sandwich_roasted_chicken_xml,
            13 to R.drawable.sandwich_steak_cheese_xml,
            14 to R.drawable.sandwich_chicken_teriyaki_xml,
            15 to R.drawable.sandwich_shrimp_xml,
            16 to R.drawable.sandwich_egg_mayo_xml,
            17 to R.drawable.sandwich_tuna_xml,
            18 to R.drawable.sandwich_veggie_xml,
        )

        val hashMapIDtoInfo = hashMapOf<Int, String>(
            1 to "페퍼로니 3장\n살라미 3장\n햄 2장",
            2 to "닭가슴살 햄 2장\n햄 1장\n베이컨 2장",
            3 to "햄 4장",
            4 to "닭가슴살 햄 3장\n베이컨 2장\n아보카도 1스쿱",
            5 to "베이컨 4장",
            6 to "페퍼로니 5장\n살라미 5장",
            7 to "로티세리 치킨 1스쿱",
            8 to "닭가슴살 햄 4장",
            9 to "풀드포크 바비큐 1스쿱",
            10 to "풀드포크 바비큐 1스쿱",
            11 to "스파이시 바비큐 1스쿱",
            12 to "닭가슴살 1장",
            13 to "스테이크 1스쿱",
            14 to "데리야끼 치킨 1스쿱",
            15 to "새우 5마리",
            16 to "에그마요 2스쿱",
            17 to "참치 2스쿱",
            18 to "각종 야채",
        )



        val menuList = arrayListOf<Sandwich>(
            Sandwich(
                0,
                "dummy",
                1,
                1,
                Vegetable(false, false, false, false, false, false, false, false, false),
                arrayListOf<Int>(),
                false,
                0,
                0
            ), Sandwich(
                1,
                "이탈리안 비엠티",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(201, 101, 401),
                false,
                0,
                5400
            ), Sandwich(
                2,
                "써브웨이 클럽",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(401, 101),
                false,
                0,
                5900
            ), Sandwich(
                3,
                "햄",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(102),
                false,
                0,
                4800
            ), Sandwich(
                4,
                "치킨 베이컨 아보카도",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(401, 204),
                false,
                0,
                6500
            ), Sandwich(
                5,
                "비엘티",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(103, 401, 202),
                false,
                0,
                5400
            ), Sandwich(
                6,
                "스파이시 이탈리안",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(402, 202),
                false,
                0,
                5700
            ), Sandwich(
                7,
                "로티세리 바비큐 치킨",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(103),
                false,
                0,
                6100
            ), Sandwich(
                8,
                "치킨 슬라이스",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(401, 101),
                false,
                0,
                5300
            ), Sandwich(
                9,
                "풀드 포크 바비큐",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(104),
                false,
                0,
                6000
            ), Sandwich(
                10,
                "K-바비큐",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(501, 503),
                false,
                0,
                6000
            ), Sandwich(
                11,
                "스파이시 바비큐",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(402, 202),
                false,
                0,
                6100
            ), Sandwich(
                12,
                "로스트 치킨",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(101),
                false,
                0,
                6100
            ), Sandwich(
                13,
                "스테이크 & 치즈",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(202, 104),
                false,
                0,
                6500
            ), Sandwich(
                14,
                "치킨 데리야끼",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(104, 103),
                false,
                0,
                5700
            ), Sandwich(
                15,
                "쉬림프",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(103,401, 201),
                false,
                0,
                5900
            ), Sandwich(
                16,
                "에그마요",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(401, 103),
                false,
                0,
                4300
            ), Sandwich(
                17,
                "참치",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(201, 103),
                false,
                0,
                4800
            ), Sandwich(
                18,
                "베지",
                1,
                1,
                Vegetable(true, true, true, true, true, true, true, true, false),
                arrayListOf<Int>(302),
                false,
                0,
                3900
            )
        )

        fun getMenuListByMeat(v: View): HashMap<Int, ArrayList<Sandwich>>
        {
            val menuList = hashMapOf<Int, ArrayList<Sandwich>>(
                v.findViewById<Button>(R.id.meatSelect_ham).id to arrayListOf<Sandwich>(
                    menuList.get(1).deepCopy(),
                    menuList.get(2).deepCopy(),
                    menuList.get(3).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_bacon).id to arrayListOf<Sandwich>(
                    menuList.get(2).deepCopy(),
                    menuList.get(4).deepCopy(),
                    menuList.get(5).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_pepperoni).id to arrayListOf<Sandwich>(
                    menuList.get(1).deepCopy(), menuList.get(6).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_salami).id to arrayListOf<Sandwich>(
                    menuList.get(1).deepCopy(), menuList.get(6).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_rotisserie_chicken).id to arrayListOf<Sandwich>(
                    menuList.get(7).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_chicken_breast_ham).id to arrayListOf<Sandwich>(
                    menuList.get(2).deepCopy(),
                    menuList.get(4).deepCopy(),
                    menuList.get(8).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_pulled_pork_bbq).id to arrayListOf<Sandwich>(
                    menuList.get(9).deepCopy(), menuList.get(10).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_spicy_bbq).id to arrayListOf<Sandwich>(
                    menuList.get(11).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_chicken_breast).id to arrayListOf<Sandwich>(
                    menuList.get(12).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_steak).id to arrayListOf<Sandwich>(
                    menuList.get(13).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_chicken_teriyaki).id to arrayListOf<Sandwich>(
                    menuList.get(14).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_shrimp).id to arrayListOf<Sandwich>(
                    menuList.get(15).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_egg_mayo).id to arrayListOf<Sandwich>(
                    menuList.get(16).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_tuna).id to arrayListOf<Sandwich>(
                    menuList.get(17).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_avocado).id to arrayListOf<Sandwich>(
                    menuList.get(4).deepCopy()
                ),
                v.findViewById<Button>(R.id.meatSelect_vege).id to arrayListOf<Sandwich>(
                    menuList.get(18).deepCopy()
                )
            )
            return menuList
        }
    }
}
