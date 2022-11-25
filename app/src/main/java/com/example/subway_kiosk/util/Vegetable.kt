package com.example.subway_kiosk.util

class Vegetable(
    private var lettuce: Boolean,
    private var tomato: Boolean,
    private var cucumber: Boolean,
    private var pepper: Boolean,
    private var onion: Boolean,
    private var pickle: Boolean,
    private var olive: Boolean,
    private var jalapeno: Boolean,
    private var avacado: Boolean
)
{
    fun changeLettuce(toChange: Boolean)
    {
        lettuce = toChange
    }

    fun changeTomato(toChange: Boolean)
    {
        tomato = toChange
    }

    fun changeCucumter(toChange: Boolean)
    {
        cucumber = toChange
    }

    fun changePepper(toChange: Boolean)
    {
        pepper = toChange
    }

    fun changeOnion(toChange: Boolean)
    {
        onion = toChange
    }

    fun changePickle(toChange: Boolean)
    {
        pickle = toChange
    }

    fun changeOlive(toChange: Boolean)
    {
        olive = toChange
    }

    fun changeJalapeno(toChange: Boolean)
    {
        jalapeno = toChange
    }

    fun changeAvocado(toChange: Boolean)
    {
        avacado = toChange
    }

    fun deepCopy(): Vegetable
    {
        return Vegetable(lettuce, tomato, cucumber, pepper, onion, pickle, olive, jalapeno, avacado)
    }

    fun showInfo()
    {
        print("권장 채소 목록: ")
        print((if (lettuce) "[양상추] " else ""))
        print((if (tomato) "[토마토] " else ""))
        print((if (cucumber) "[오이] " else ""))
        print((if (pepper) "[후추] " else ""))
        print((if (onion) "[양파] " else ""))
        print((if (pickle) "[피클] " else ""))
        print((if (olive) "[올리브] " else ""))
        print((if (jalapeno) "[할라피뇨] " else ""))
        print((if (avacado) "[아보카도] " else ""))

        print("\n빠진 채소 목록: ")
        print((if (!lettuce) "[양상추] " else ""))
        print((if (!tomato) "[토마토] " else ""))
        print((if (!cucumber) "[오이] " else ""))
        print((if (!pepper) "[후추] " else ""))
        print((if (!onion) "[양파] " else ""))
        print((if (!pickle) "[피클] " else ""))
        print((if (!olive) "[올리브] " else ""))
        print((if (!jalapeno) "[할라피뇨] " else ""))
        print((if (!avacado) "[아보카도] " else ""))
        println("")
    }


}