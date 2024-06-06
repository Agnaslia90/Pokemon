package com.natalvrom.pokmon

import com.google.gson.annotations.SerializedName

data class PokemonDataResponse(
    @SerializedName("previous") var previous: String,
    @SerializedName("results") var results: List<PokemonItemResponse>
)

data class PokemonItemResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)