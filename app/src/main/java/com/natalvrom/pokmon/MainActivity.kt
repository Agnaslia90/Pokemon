package com.natalvrom.pokmon

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.natalvrom.pokmon.R.*
import com.natalvrom.pokmon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun initUI() {
        binding.btnPokemonApp.setOnClickListener {
            navigateToPokemonApp()
        }
    }

    private fun navigateToPokemonApp() {
        val intent = Intent(this, PokemonListActivity::class.java)
        startActivity(intent)
    }

}