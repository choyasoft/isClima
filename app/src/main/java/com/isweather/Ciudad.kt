package com.isweather

import android.widget.ImageView

class Ciudad(name:String,weather:ArrayList<Weather>, main:Main) {
    var name: String = ""
    var weather: ArrayList<Weather>? = null
    var main: Main? = null
    var wind: Wind? = null

    init{
        this.name = name
        this.weather = weather
        this.main = main
        this.wind = wind
    }

}