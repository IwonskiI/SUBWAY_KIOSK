package com.example.subway_kiosk.util

class SandwichInfo {
    var sandwichID: Int = 0
    var sandwichName: String = ""
    var breadID: Int = 0
    var cheeseID: Int = 0
    var vege: VegetableInfo = VegetableInfo(false,false,false,false,false,false,false,false,false)
    var sauceID: ArrayList<Int> = arrayListOf()
    var setBool: Boolean = false
    var cookieID: Int = 0
    var price: Int = 0
    var breadName: String = ""
    var cheeseName: String = ""
    var cookieName: String = ""
    constructor()
    constructor(sandwichID: Int, sandwichName: String, breadID: Int, cheeseID: Int, vege: VegetableInfo, sauceID: ArrayList<Int>, setBool: Boolean, cookieID: Int, price: Int, breadName: String, cheeseName: String, cookieName: String){
        this.sandwichID = sandwichID
        this.sandwichName = sandwichName
        this.breadID = breadID
        this.cheeseID = cheeseID
        this.vege = vege
        this.sauceID = sauceID
        this.setBool = setBool
        this.cookieID = cookieID
        this.price = price
        this. breadName = breadName
        this.cheeseName = cheeseName
        this.cookieName = cookieName
    }

}