package ru.nifontbus.testmvp.app

import android.app.Application
import ru.nifontbus.testmvp.di.component.AppComponent
import ru.nifontbus.testmvp.di.DaggerAppComponent
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


/*
class App:Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigationHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        lateinit var appInstance: App
        private const val DB_NAME = "github_users.db"

        private val room_db by lazy {
            Room.databaseBuilder(
                appInstance.applicationContext,
                GithubDatabase::class.java,
                DB_NAME
            )
//                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getRoomDb() = room_db

    }
}*/
