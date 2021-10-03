package ru.nifontbus.testmvp.di.modules

import dagger.Module
import dagger.Provides
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.di.scopes.IUserScopeContainer
import ru.nifontbus.testmvp.di.scopes.UserScope
import ru.nifontbus.testmvp.models.db.GithubDatabase
import ru.nifontbus.testmvp.models.db.IUserCache
import ru.nifontbus.testmvp.models.db.UserCache
import ru.nifontbus.testmvp.models.repo.IDataSource
import ru.nifontbus.testmvp.models.repo.IGithubUsersRepo
import ru.nifontbus.testmvp.models.repo.RetrofitGithubUsersRepo
import ru.nifontbus.testmvp.models.utils.network.INetworkStatus
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import javax.inject.Singleton

@Module
open class UsersModule {

    @UserScope
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Provides
    fun usersCache(db: GithubDatabase): IUserCache = UserCache(db)

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app

}
