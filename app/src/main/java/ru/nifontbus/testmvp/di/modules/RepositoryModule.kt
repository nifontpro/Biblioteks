package ru.nifontbus.testmvp.di.modules

import dagger.Module
import dagger.Provides
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.di.scopes.IRepositoryScopeContainer
import ru.nifontbus.testmvp.di.scopes.RepositoryScope
import ru.nifontbus.testmvp.models.db.GithubDatabase
import ru.nifontbus.testmvp.models.db.IRepositoriesCache
import ru.nifontbus.testmvp.models.db.RepositoryCache
import ru.nifontbus.testmvp.models.repo.IDataSource
import ru.nifontbus.testmvp.models.repo.IGithubRepositoriesRepo
import ru.nifontbus.testmvp.models.repo.RetrofitGithubRepositoriesRepo
import ru.nifontbus.testmvp.models.utils.network.INetworkStatus
import ru.nifontbus.testmvp.presentation.repository.RepositoryPresenter
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @RepositoryScope
    @Provides
    fun retrofitRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api, networkStatus, cache)


    @Provides
    fun repositoriesCache(db: GithubDatabase): IRepositoriesCache = RepositoryCache(db)

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}
