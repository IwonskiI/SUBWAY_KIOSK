package com.example.subway_kiosk.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable
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

    override fun toString(): String
    {
        return buildString {
            appendLine("권장 채소 목록: ")
            append((if (lettuce) "[양상추] " else ""))
            append((if (tomato) "[토마토] " else ""))
            append((if (cucumber) "[오이] " else ""))
            append((if (pepper) "[피망] " else ""))
            append((if (onion) "[양파] " else ""))
            append((if (pickle) "[피클] " else ""))
            append((if (olive) "[올리브] " else ""))
            append((if (jalapeno) "[할라피뇨] " else ""))
            append((if (avacado) "[아보카도] " else ""))

            appendLine("\n빠진 채소 목록: ")
            append((if (!lettuce) "[양상추] " else ""))
            append((if (!tomato) "[토마토] " else ""))
            append((if (!cucumber) "[오이] " else ""))
            append((if (!pepper) "[피망] " else ""))
            append((if (!onion) "[양파] " else ""))
            append((if (!pickle) "[피클] " else ""))
            append((if (!olive) "[올리브] " else ""))
            append((if (!jalapeno) "[할라피뇨] " else ""))
            append((if (!avacado) "[아보카도] " else ""))
        }
    }

    fun showInfo()
    {
        println(toString())
    }
}