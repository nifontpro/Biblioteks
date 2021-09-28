package ru.nifontbus.testmvp.models.db

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.nifontbus.testmvp.app.App
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
