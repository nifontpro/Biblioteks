package ru.nifontbus.testmvp.di.component

import dagger.Component
import ru.nifontbus.testmvp.di.modules.CiceroneModule
import ru.nifontbus.testmvp.di.modules.*
import ru.nifontbus.testmvp.models.db.RepositoryCache
import ru.nifontbus.testmvp.models.db.UserCache
import ru.nifontbus.testmvp.presentation.activity.MainActivity
import ru.nifontbus.testmvp.presentation.activity.MainPresenter
import ru.nifontbus.testmvp.presentation.details.DetailsFragment
import ru.nifontbus.testmvp.presentation.details.DetailsPresenter
import ru.nifontbus.testmvp.presentation.repos_info.RepoInfoFragment
import ru.nifontbus.testmvp.presentation.repos_info.RepoInfoPresenter
import ru.nifontbus.testmvp.presentation.users.UsersFragment
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import ru.nifontbus.testmvp.presentation.users.adapter.UsersRvAdapter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        DbModule::class,
        RepoModule::class,
        UsersModule::class,
        UtilsModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(detailsPresenter: DetailsPresenter)
    fun inject(repoInfoPresenter: RepoInfoPresenter)
    fun inject(usersRvAdapter: UsersRvAdapter)
    fun inject(userCache: UserCache)
    fun inject(repositoryCache: RepositoryCache)

    fun inject(usersFragment: UsersFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(repoInfoFragment: RepoInfoFragment)
}
