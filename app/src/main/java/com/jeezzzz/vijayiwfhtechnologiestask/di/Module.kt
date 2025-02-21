package com.jeezzzz.vijayiwfhtechnologiestask.di

import com.jeezzzz.vijayiwfhtechnologiestask.data.WatchModeRepository
import com.jeezzzz.vijayiwfhtechnologiestask.data.WatchModeService
import com.jeezzzz.vijayiwfhtechnologiestask.viewModels.WatchModeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.watchmode.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(WatchModeService::class.java)
    }
    single { WatchModeRepository(get()) }
    viewModel { WatchModeViewModel(get()) }
}
