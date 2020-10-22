package com.isweather

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var tvCiudad: TextView? = null
    var tvGrados: TextView? = null
    var tvEstatus: TextView? = null
    var imagenClima: ImageView? = null
    var tvViento: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(300)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstatus = findViewById(R.id.tvEstatus)

        imagenClima = findViewById(R.id.imageClima)
        tvViento = findViewById(R.id.tvViento)

        val ciudad = intent.getStringExtra("com.isweather.ciudades.CIUDAD")


        if (Network.hayRed(this)) {

            //ejecutar solicitud HTTP
            val url_source = "https://api.openweathermap.org/data/2.5/weather?id="
            val api_key = "&appid=eeccf4730d646e8aabbc3e19b72a9b2d"
            val units = "&units=metric"
            val lang = "&lang=es"
            solicitudHTTPVolley(url_source + ciudad + api_key + units + lang)

        } else {
            // mostrar mensaje
            Toast.makeText(this, "No hay internet", Toast.LENGTH_SHORT).show()
        }
    }


    //Método para Volley
    private fun solicitudHTTPVolley(url: String) {

        val queue = Volley.newRequestQueue(this)

        val solicitud = StringRequest(Request.Method.GET, url, { response ->

            try {
                Log.d("solicitudHTTPVolley", response)
                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)
                tvCiudad?.text = ciudad.name.toUpperCase()
                tvGrados?.text = ciudad.main?.temp?.toInt().toString() + "º"
                tvEstatus?.text = ciudad.weather?.get(0)?.description?.toUpperCase()
                tvViento?.text = ciudad.wind?.speed?.toInt().toString() + "Km/h"
                setimageClima(ciudad)
            } catch (e: Exception) {

            }
        }, { })

        queue.add(solicitud)
    }

    fun setimageClima(ciudad: Ciudad) {

        if (ciudad.weather?.get(0)?.description?.contains("nubes")!! || ciudad.weather?.get(0)?.description?.contains(
                "nuboso"
            )!!
        ) {
            imageClima.setImageResource(R.drawable.cloudy)
        } else {
            if (ciudad.weather?.get(0)?.description?.contains("sol")!!) {
                imageClima.setImageResource(R.drawable.sol)
            } else {
                if (ciudad.weather?.get(0)?.description?.contains("lluv")!! || ciudad.weather?.get(0)?.description?.contains(
                        "llov"
                    )!!
                ) {
                    imageClima.setImageResource(R.drawable.lluvia)

                } else {
                    if (ciudad.weather?.get(0)?.description?.contains("torm")!!) {
                        imageClima.setImageResource(R.drawable.storm)
                    } else {
                        imageClima.setImageResource(R.drawable.vacio)
                    }
                }
            }
        }
    }
}


