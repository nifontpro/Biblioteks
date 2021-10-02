package ru.nifontbus.testmvp.app

import android.app.Application
import ru.nifontbus.testmvp.di.component.AppComponent
import ru.nifontbus.testmvp.di.component.DaggerAppComponent
import ru.nifontbus.testmvp.di.modules.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}

