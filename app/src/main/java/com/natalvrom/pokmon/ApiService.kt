package com.natalvrom.pokmon

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/v2/pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Response<PokemonDetailResponse>

    @GET("/api/v2/pokemon/")
    suspend fun getAllPokemons(): Response<PokemonDataResponse>

}