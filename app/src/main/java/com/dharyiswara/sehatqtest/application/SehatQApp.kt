package com.dharyiswara.sehatqtest.application

import android.app.Application
import com.dharyiswara.sehatqtest.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SehatQApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SehatQApp)
            modules(
                networkModule,
                localModule,
                repositoryModule,
                viewModelModule,
                commonModule
            )
        }
    }

}