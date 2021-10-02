package ru.nifontbus.testmvp.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.db.GithubDatabase
import ru.nifontbus.testmvp.models.db.ILocalCache
import ru.nifontbus.testmvp.models.db.LocalCache
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): GithubDatabase =
        Room.databaseBuilder(app, GithubDatabase::class.java, GithubDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun usersCache(): ILocalCache = LocalCache()
}
