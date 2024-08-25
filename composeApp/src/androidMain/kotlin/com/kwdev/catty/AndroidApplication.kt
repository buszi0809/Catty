package com.kwdev.catty

import android.app.Application
import com.kwdev.catty.di.initializeKoin
import com.kwdev.catty.init.AppInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
        }

        AppInitializer.initializeAll()
    }
}
