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
    private var avocado: Boolean
) : Parcelable
{
    fun getLettuce(): Boolean
    {
        return lettuce
    }

    fun getTomato(): Boolean
    {
        return tomato
    }

    fun getCucumber(): Boolean
    {
        return cucumber
    }

    fun getPepper(): Boolean
    {
        return pepper
    }

    fun getOnion(): Boolean
    {
        return onion
    }

    fun getPickle(): Boolean
    {
        return pickle
    }

    fun getOlive(): Boolean
    {
        return olive
    }

    fun getJalapeno(): Boolean
    {
        return jalapeno
    }

    fun getAvocado(): Boolean
    {
        return avocado
    }

    fun setLettuce(toChange: Boolean)
    {
        lettuce = toChange
    }

    fun setTomato(toChange: Boolean)
    {
        tomato = toChange
    }

    fun setCucumter(toChange: Boolean)
    {
        cucumber = toChange
    }

    fun setPepper(toChange: Boolean)
    {
        pepper = toChange
    }

    fun setOnion(toChange: Boolean)
    {
        onion = toChange
    }

    fun setPickle(toChange: Boolean)
    {
        pickle = toChange
    }

    fun setOlive(toChange: Boolean)
    {
        olive = toChange
    }

    fun setJalapeno(toChange: Boolean)
    {
        jalapeno = toChange
    }

    fun setAvocado(toChange: Boolean)
    {
        avocado = toChange
    }

    fun changeLettuce()
    {
        lettuce = !lettuce
    }

    fun changeTomato()
    {
        tomato = !tomato
    }

    fun changeCucumter()
    {
        cucumber = !cucumber
    }

    fun changePepper()
    {
        pepper = !pepper
    }

    fun changeOnion()
    {
        onion = !onion
    }

    fun changePickle()
    {
        pickle = !pickle
    }

    fun changeOlive()
    {
        olive = !olive
    }

    fun changeJalapeno()
    {
        jalapeno = !jalapeno
    }

    fun changeAvocado()
    {
        avocado = !avocado
    }

    fun deepCopy(): Vegetable
    {
        return Vegetable(lettuce, tomato, cucumber, pepper, onion, pickle, olive, jalapeno, avocado)
    }

    override fun toString(): String
    {
        return buildString {
            appendLine("?????? ?????? ??????: ")
            append((if (lettuce) "[?????????] " else ""))
            append((if (tomato) "[?????????] " else ""))
            append((if (cucumber) "[??????] " else ""))
            append((if (pepper) "[??????] " else ""))
            append((if (onion) "[??????] " else ""))
            append((if (pickle) "[??????] " else ""))
            append((if (olive) "[?????????] " else ""))
            append((if (jalapeno) "[????????????] " else ""))
            append((if (avocado) "[????????????] " else ""))

            appendLine("\n?????? ?????? ??????: ")
            append((if (!lettuce) "[?????????] " else ""))
            append((if (!tomato) "[?????????] " else ""))
            append((if (!cucumber) "[??????] " else ""))
            append((if (!pepper) "[??????] " else ""))
            append((if (!onion) "[??????] " else ""))
            append((if (!pickle) "[??????] " else ""))
            append((if (!olive) "[?????????] " else ""))
            append((if (!jalapeno) "[????????????] " else ""))
            append((if (!avocado) "[????????????] " else ""))
        }
    }

    fun showInfo()
    {
        println(toString())
    }
}