package ru.nifontbus.testmvp.di.component

import dagger.Component
import ru.nifontbus.testmvp.di.modules.*
import ru.nifontbus.testmvp.presentation.activity.MainActivity
import ru.nifontbus.testmvp.presentation.activity.MainPresenter
import ru.nifontbus.testmvp.presentation.repos_info.RepoInfoPresenter
import ru.nifontbus.testmvp.presentation.repository.RepositoryPresenter
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import ru.nifontbus.testmvp.presentation.users.adapter.UsersRvAdapter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        UtilsModule::class
    ]
)

interface AppComponent {
    fun userSubcomponent() : UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}
