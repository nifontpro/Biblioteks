package ru.nifontbus.testmvp.di.component

import dagger.Component
import ru.nifontbus.testmvp.di.modules.*
import ru.nifontbus.testmvp.models.db.RepositoryCache
import ru.nifontbus.testmvp.models.db.UserCache
import ru.nifontbus.testmvp.presentation.activity.MainActivity
import ru.nifontbus.testmvp.presentation.activity.MainPresenter
import ru.nifontbus.testmvp.presentation.repository.RepositoryFragment
import ru.nifontbus.testmvp.presentation.repository.RepositoryPresenter
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
        RepositoryModule::class,
        UsersModule::class,
        UtilsModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(repoInfoPresenter: RepoInfoPresenter)
    fun inject(usersRvAdapter: UsersRvAdapter)

    fun inject(usersFragment: UsersFragment)
    fun inject(repositoryFragment: RepositoryFragment)
    fun inject(repoInfoFragment: RepoInfoFragment)
}
