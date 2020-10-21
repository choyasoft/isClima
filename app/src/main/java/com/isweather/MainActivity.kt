package com.isweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var tvCiudad: TextView? = null
    var tvGrados: TextView? = null
    var tvEstatus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstatus = findViewById(R.id.tvEstatus)

        val ciudad = intent.getStringExtra("com.isweather.ciudades.CIUDAD")

        if (Network.hayRed(this)) {
            //ejecutar solicitud HTTP
            solicitudHTTPVolley("http://api.openweathermap.org/data/2.5/weather?id="+ciudad+"&appid=eeccf4730d646e8aabbc3e19b72a9b2d&units=metric&lang=es")
            // eeccf4730d646e8aabbc3e19b72a9b2d
            // Tarragona Ciudad: 3108288

        } else {
            //mostrar mensaje
            Toast.makeText(this,"No hay internet", Toast.LENGTH_SHORT).show()
        }

        /*
        val ciudadtgn = Ciudad("Ciudad de Tarragona", 17,"Soleado" )
        val ciudadbcn = Ciudad("Ciudad de Barcelona", 16,"Nublado")
        val ciudadmad = Ciudad("Ciudad de Madrid", 13,"Lluvia")

        if(ciudad == "ciudad-tarragona"){
            // mostrar info de la ciudad de tgn
            tvCiudad?.text = ciudadtgn.nombre
            tvGrados?.text = ciudadtgn.grados.toString() + "º"
            tvEstatus?.text = ciudadtgn.estatus

        }else if (ciudad == "ciudad-barcelona"){
            // mostrar info de la ciudad de bcn
            tvCiudad?.text = ciudadbcn.nombre
            tvGrados?.text = ciudadbcn.grados.toString() + "º"
            tvEstatus?.text = ciudadbcn.estatus
        }
        else if (ciudad == "ciudad-madrid") {
            // mostrar info de la ciudad de madrid
            tvCiudad?.text = ciudadmad.nombre
            tvGrados?.text = ciudadmad.grados.toString() + "º"
            tvEstatus?.text = ciudadmad.estatus

        }else{
            Toast.makeText(this,"No se encuentra la información.", Toast.LENGTH_SHORT).show()
    }

         */
}

        //Método para Volley
        fun solicitudHTTPVolley(url: String) {
            val queue = Volley.newRequestQueue(this)

            val solicitud =
                StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                    try {
                        Log.d("solicitudHTTPVolley", response)
                        val gson = Gson()
                        val ciudad = gson.fromJson(response, Ciudad::class.java)
                        tvCiudad?.text = ciudad.name
                        tvGrados?.text = ciudad.main?.temp.toString() + "º"
                        tvEstatus?.text = ciudad.weather?.get(0)?.description
                    } catch (e: Exception) {

                    }
                }, Response.ErrorListener { })

            queue.add(solicitud)
        }
    }
