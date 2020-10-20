package com.isweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

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

        Toast.makeText(this, ciudad, Toast.LENGTH_SHORT).show()
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
            // mostrar info de la ciudad de bcn
            tvCiudad?.text = ciudadmad.nombre
            tvGrados?.text = ciudadmad.grados.toString() + "º"
            tvEstatus?.text = ciudadmad.estatus

        }else{
            Toast.makeText(this,"No se encuentra la información.", Toast.LENGTH_SHORT).show()
    }
}
}