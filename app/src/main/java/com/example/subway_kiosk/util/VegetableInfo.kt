package com.example.subway_kiosk.util

class VegetableInfo {
    var lettuce: Boolean = false
    var tomato: Boolean = false
    var cucumber: Boolean = false
    var pepper: Boolean = false
    var onion: Boolean = false
    var pickle: Boolean = false
    var olive: Boolean = false
    var jalapeno: Boolean = false
    var avocado: Boolean = false
    constructor()
    constructor(lettuce: Boolean, tomato: Boolean,cucumber: Boolean,pepper: Boolean,onion: Boolean,pickle: Boolean,olive: Boolean,jalapeno: Boolean,avocado: Boolean){
        this.lettuce = lettuce
        this.tomato = tomato
        this.cucumber = cucumber
        this.pepper = pepper
        this.onion = onion
        this.pickle = pickle
        this.olive = olive
        this.jalapeno = jalapeno
        this.avocado = avocado
    }
}