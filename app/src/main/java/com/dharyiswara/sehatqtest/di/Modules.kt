package com.dharyiswara.sehatqtest.di

import com.dharyiswara.sehatqtest.helper.AppExecutors
import com.dharyiswara.sehatqtest.network.NetworkFactory
import com.dharyiswara.sehatqtest.preferences.UserSession
import com.dharyiswara.sehatqtest.repository.HomepageRepository
import com.dharyiswara.sehatqtest.ui.main.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {

    single { NetworkFactory.makeNetworkService(get(), get(), get()) }

    single { NetworkFactory.makeClientService(get(), get()) }

    single { NetworkFactory.makeLoggingInterceptor() }

    single { NetworkFactory.makeGson() }

    single { NetworkFactory.makeCache(get()) }
}

val repositoryModule = module {

    single { HomepageRepository(get(), get()) }

}

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }

}

val commonModule = module {

    single { AppExecutors() }

    single { UserSession(get()) }

}