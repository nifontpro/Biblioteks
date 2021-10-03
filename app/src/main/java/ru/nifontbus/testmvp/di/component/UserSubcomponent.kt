package ru.nifontbus.testmvp.di.component

import dagger.Subcomponent
import ru.nifontbus.testmvp.di.modules.UsersModule
import ru.nifontbus.testmvp.di.scopes.UserScope
import ru.nifontbus.testmvp.presentation.users.UsersPresenter

@UserScope
@Subcomponent(modules = [UsersModule::class])
interface UserSubcomponent {

    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
}
