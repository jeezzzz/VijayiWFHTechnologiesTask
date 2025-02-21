package com.jeezzzz.vijayiwfhtechnologiestask.data.models.listOfTitles

data class Title(
    val id: Int,
    val title: String,
    val year: Int?,
    val imdb_id: String?,
    val tmdb_id: Int?,
    val tmdb_type: String?,
    val type: String?
)
