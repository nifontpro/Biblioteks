package ru.nifontbus.testmvp.di.modules

import dagger.Module
import dagger.Provides
import ru.nifontbus.testmvp.models.db.IRepositoriesCache
import ru.nifontbus.testmvp.models.db.RepositoryCache
import ru.nifontbus.testmvp.models.repo.IDataSource
import ru.nifontbus.testmvp.models.repo.IGithubRepositoriesRepo
import ru.nifontbus.testmvp.models.repo.RetrofitGithubRepositoriesRepo
import ru.nifontbus.testmvp.models.utils.network.INetworkStatus
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun retrofitRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesCache(): IRepositoriesCache = RepositoryCache()

}
