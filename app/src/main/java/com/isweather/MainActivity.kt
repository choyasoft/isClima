package com.isweather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    var tvCiudad: TextView? = null
    var tvGrados: TextView? = null
    var tvEstatus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(600)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstatus = findViewById(R.id.tvEstatus)


        val ciudad = intent.getStringExtra("com.isweather.ciudades.CIUDAD")

       if (Network.hayRed(this)) {

                //ejecutar solicitud HTTP
                solicitudHTTPVolley("https://api.openweathermap.org/data/2.5/weather?id=" + ciudad + "&appid=eeccf4730d646e8aabbc3e19b72a9b2d&units=metric&lang=es")
          }else{
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
                tvCiudad?.text = ciudad.name
                tvGrados?.text = ciudad.main?.temp.toString() + "º"
                tvEstatus?.text = ciudad.weather?.get(0)?.description
            } catch (e: Exception) {

            }
        }, { })

        queue.add(solicitud)
    }

    }

