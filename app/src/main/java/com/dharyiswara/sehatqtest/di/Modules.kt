package com.dharyiswara.sehatqtest.di

import com.dharyiswara.sehatqtest.helper.AppExecutors
import org.koin.dsl.module

val networkModule = module {

}

val localModule = module {

}

val repositoryModule = module {

}

val viewModelModule = module {

}

val commonModule = module {

    single { AppExecutors() }

}