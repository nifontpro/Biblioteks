package ru.nifontbus.testmvp.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.db.GithubDatabase
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun database(app: App): GithubDatabase =
        Room.databaseBuilder(app, GithubDatabase::class.java, GithubDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
}