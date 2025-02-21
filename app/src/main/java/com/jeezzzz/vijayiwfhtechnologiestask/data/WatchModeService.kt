package com.jeezzzz.vijayiwfhtechnologiestask.data

import com.jeezzzz.vijayiwfhtechnologiestask.data.models.detailsOfTitles.DetailsOfTitlesResponse
import com.jeezzzz.vijayiwfhtechnologiestask.data.models.listOfTitles.ListOfTitlesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchModeService {

    @GET("list-titles/")
    fun getTitles(
        @Query("apiKey") apiKey: String,
        @Query("types") type: String
    ): Single<ListOfTitlesResponse>

    @GET("title/{id}/details/")
    fun getTitleDetails(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Single<DetailsOfTitlesResponse>
}
