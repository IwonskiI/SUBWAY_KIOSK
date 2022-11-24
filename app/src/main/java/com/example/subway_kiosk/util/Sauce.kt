package com.example.subway_kiosk.util

import kotlin.system.exitProcess

class Sauce(val sauceID: Int)
{
    var sauceNameKor: String

    init
    {
        when (sauceID) // 10X: 달콤, 20X: 매콤, 30X: 새콤, 40X: 고소, 50X: 기타
        {
            101  -> sauceNameKor = "스위트 어니언"
            102  -> sauceNameKor = "허니 머스타드"
            103  -> sauceNameKor = "스위트 칠리"
            104  -> sauceNameKor = "스모크 바비큐"
            201  -> sauceNameKor = "핫 칠리"
            202  -> sauceNameKor = "사우스웨스트 치폴레"
            203  -> sauceNameKor = "머스타드"
            204  -> sauceNameKor = "홀스래디쉬"
            301  -> sauceNameKor = "이탈리안 드레싱"
            302  -> sauceNameKor = "레드와인 식초"
            401  -> sauceNameKor = "랜치"
            402  -> sauceNameKor = "마요네즈"
            501  -> sauceNameKor = "올리브 오일"
            502  -> sauceNameKor = "소금"
            503  -> sauceNameKor = "후추"
            else ->
            {
                println("invalid sauce ID")
                exitProcess(1)
            }
        }
    }
}