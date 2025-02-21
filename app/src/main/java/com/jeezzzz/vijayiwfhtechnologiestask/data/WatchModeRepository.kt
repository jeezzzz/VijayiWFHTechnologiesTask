package com.jeezzzz.vijayiwfhtechnologiestask.data

import com.jeezzzz.vijayiwfhtechnologiestask.data.models.detailsOfTitles.DetailsOfTitlesResponse
import com.jeezzzz.vijayiwfhtechnologiestask.data.models.listOfTitles.Title
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

class WatchModeRepository(private val api: WatchModeService) {
    private val apiKey = "1vaVH8dMJ8nsNAUjP3GAlR3C51ARgsPtCEUQLcwd"

    fun getMoviesAndTvShows(): Single<Pair<List<Title>, List<Title>>> {
        return Single.zip(
            api.getTitles(apiKey, "movie"),
            api.getTitles(apiKey, "tv_series"),
            BiFunction { movies, tvShows -> Pair(movies.titles, tvShows.titles) }
        )
    }

    fun getTitleDetails(id: Int): Single<DetailsOfTitlesResponse> {
        return api.getTitleDetails(id, apiKey)
    }
}

