package ru.nifontbus.testmvp.di.component

import dagger.Subcomponent
import ru.nifontbus.testmvp.di.modules.RepositoryModule
import ru.nifontbus.testmvp.di.scopes.RepositoryScope
import ru.nifontbus.testmvp.presentation.repos_info.RepoInfoPresenter
import ru.nifontbus.testmvp.presentation.repository.RepositoryPresenter
import ru.nifontbus.testmvp.presentation.users.UsersPresenter

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(repositoryPresenter: RepositoryPresenter)
    fun inject(repoInfoPresenter: RepoInfoPresenter)
}
