package com.isweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class Ciudades : AppCompatActivity() {

    val TAG = "com.isweather.ciudades.CIUDAD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val bTgn = findViewById<Button>(R.id.bTgn)
        val bBcn = findViewById<Button>(R.id.bBcn)
        val bMad = findViewById<Button>(R.id.bMad)

        bTgn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "3108288")
            startActivity(intent)
        })
        bBcn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "3128760")
            startActivity(intent)
        })
        bMad.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "3117735")
            startActivity(intent)
        })

    }
}