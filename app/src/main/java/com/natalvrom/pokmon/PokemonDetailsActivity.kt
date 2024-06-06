package com.natalvrom.pokmon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.natalvrom.pokmon.databinding.ActivityPokemonDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonDetailsBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun initUI() {
        val pokemonName = this.intent.getStringExtra("POKEMON_NAME")
        if (pokemonName!= null){
            getPokemonDetails(pokemonName)
        } else {
            Log.e("PokemonDetailsActivity", "No Pokemon Name provided")
        }

    }

    private fun getPokemonDetails(name: String) {
        lifecycleScope.launch((Dispatchers.IO)) {
            val myResponse: Response<PokemonDetailResponse> =
                retrofit.create(ApiService::class.java).getPokemonDetails(name)

            if (myResponse.isSuccessful) {
                Log.i("natalvrom", "respuesta obtenida con éxito :)")
                val response: PokemonDetailResponse? = myResponse.body()
                if (response != null) {
                    Log.i("natalvrom", response.toString())
                    withContext(Dispatchers.Main){
                        binding.tvName.text = response.name
                        binding.tvHeight.text = "Height: ${response.height}"
                        binding.tvWeight.text = "Weight: ${response.weight}"
                    }

                    } else {
                    Log.i("App", "respuesta nula")
                }

            }
        }

    }

    fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}