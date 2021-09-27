package ru.nifontbus.testmvp.di

import dagger.Component
import ru.nifontbus.testmvp.app.AppModule
import ru.nifontbus.testmvp.models.db.CacheModule
import ru.nifontbus.testmvp.models.db.LocalCache
import ru.nifontbus.testmvp.models.repo.ApiModule
import ru.nifontbus.testmvp.models.repo.RepoModule
import ru.nifontbus.testmvp.models.utils.UtilsModule
import ru.nifontbus.testmvp.presentation.activity.MainActivity
import ru.nifontbus.testmvp.presentation.activity.MainPresenter
import ru.nifontbus.testmvp.presentation.details.DetailsPresenter
import ru.nifontbus.testmvp.presentation.repos_info.RepoInfoPresenter
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import ru.nifontbus.testmvp.presentation.users.adapter.UsersRvAdapter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class,
        UtilsModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(localCache: LocalCache)

    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(detailsPresenter: DetailsPresenter)
    fun inject(repoInfoPresenter: RepoInfoPresenter)
    fun inject(usersRvAdapter: UsersRvAdapter)
}
