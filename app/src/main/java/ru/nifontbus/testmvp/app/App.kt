package ru.nifontbus.testmvp.app

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.nifontbus.testmvp.models.db.dao.GithubDatabase

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
}