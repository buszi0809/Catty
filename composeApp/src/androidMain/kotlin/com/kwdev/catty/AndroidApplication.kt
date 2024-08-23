package com.kwdev.catty

import android.app.Application
import com.kwdev.catty.di.initializeKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
        }
    }
}
