package ru.nifontbus.testmvp.di.modules

import dagger.Module
import dagger.Provides
import ru.nifontbus.testmvp.models.db.GithubDatabase
import ru.nifontbus.testmvp.models.db.IUserCache
import ru.nifontbus.testmvp.models.db.UserCache
import ru.nifontbus.testmvp.models.repo.IDataSource
import ru.nifontbus.testmvp.models.repo.IGithubUsersRepo
import ru.nifontbus.testmvp.models.repo.RetrofitGithubUsersRepo
import ru.nifontbus.testmvp.models.utils.network.INetworkStatus
import javax.inject.Singleton

@Module
class UsersModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun usersCache(db: GithubDatabase): IUserCache = UserCache(db)
}
