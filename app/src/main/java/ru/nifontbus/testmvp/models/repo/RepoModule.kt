package ru.nifontbus.testmvp.models.repo

import dagger.Module
import dagger.Provides
import ru.nifontbus.testmvp.models.db.ILocalCache
import ru.nifontbus.testmvp.models.utils.network.INetworkStatus
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun retrofitRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: ILocalCache
    ): IGithubUsersRepo = GithubUsersRepo(api, networkStatus, cache)

}
