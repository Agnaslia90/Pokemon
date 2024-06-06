package com.natalvrom.pokmon

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.natalvrom.pokmon.databinding.ActivityPokemonListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
        getAllPokemons()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun initUI() {
        pokemonAdapter = PokemonAdapter{
            pokemon ->
            navegateToPokemonDetail(pokemon.name)
        }
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        binding.rvPokemon.adapter = pokemonAdapter
    }


    @SuppressLint("SuspiciousIndentation")
    private fun getAllPokemons() {
        lifecycleScope.launch((Dispatchers.IO)) {
            val myResponse: Response<PokemonDataResponse> =
                retrofit.create(ApiService::class.java).getAllPokemons()

            if (myResponse.isSuccessful) {
                Log.i("natalvrom", "respuesta obtenida con éxito :)")
                val response: PokemonDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("natalvrom", response.toString())
                    setAdapter(response.results)
                } else {
                    Log.i("App", "respuesta nula")
                }
            }
        }

        }

    private fun navegateToPokemonDetail(pokemonName: String) {
        val intent = Intent(this, PokemonDetailsActivity::class.java).apply {
            putExtra("POKEMON_NAME", pokemonName)
        }
        startActivity(intent)
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private suspend fun setAdapter(list: List<PokemonItemResponse>){
        withContext(Dispatchers.Main) {
            pokemonAdapter.updateList(list)
        }
    }

}
