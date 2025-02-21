package com.jeezzzz.vijayiwfhtechnologiestask.viewModels

import androidx.lifecycle.ViewModel
import com.jeezzzz.vijayiwfhtechnologiestask.data.WatchModeRepository
import com.jeezzzz.vijayiwfhtechnologiestask.data.models.detailsOfTitles.DetailsOfTitlesResponse
import com.jeezzzz.vijayiwfhtechnologiestask.data.models.listOfTitles.Title
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WatchModeViewModel(private val repository: WatchModeRepository) : ViewModel() {
    private val _moviesAndTvShows = MutableStateFlow<Pair<List<Title>, List<Title>>?>(null)
    val moviesAndTvShows: StateFlow<Pair<List<Title>, List<Title>>?> = _moviesAndTvShows

    private val _titleDetails = MutableStateFlow<DetailsOfTitlesResponse?>(null)
    val titleDetails: StateFlow<DetailsOfTitlesResponse?> = _titleDetails

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchMoviesAndTvShows() {
        repository.getMoviesAndTvShows()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                _moviesAndTvShows.value = result
            }, { error ->
                _errorMessage.value = error.message
            })
    }

    fun fetchTitleDetails(id: Int) {
        repository.getTitleDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ details ->
                _titleDetails.value = details
            }, { error ->
                _errorMessage.value = error.message
            })
    }
}
