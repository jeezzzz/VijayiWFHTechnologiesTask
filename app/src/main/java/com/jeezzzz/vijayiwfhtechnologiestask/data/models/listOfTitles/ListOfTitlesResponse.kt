package com.jeezzzz.vijayiwfhtechnologiestask.data.models.listOfTitles

data class ListOfTitlesResponse(
    val page: Int,
    val titles: List<Title>,
    val total_pages: Int,
    val total_results: Int
)