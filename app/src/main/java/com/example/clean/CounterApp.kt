package com.example.clean

import android.app.Application
import com.example.clean.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CounterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CounterApp)
            androidLogger(level = Level.DEBUG)
            modules(appModule)
        }
    }
}
