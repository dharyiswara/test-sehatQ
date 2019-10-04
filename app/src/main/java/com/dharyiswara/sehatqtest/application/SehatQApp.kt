package com.dharyiswara.sehatqtest.application

import android.app.Application
import com.dharyiswara.sehatqtest.di.*
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SehatQApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SehatQApp)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                viewModelModule,
                commonModule
            )
        }
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }

}