package ru.nifontbus.testmvp.presentation.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.di.scopes.IUserScopeContainer
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.models.repo.IGithubUsersRepo
import ru.nifontbus.testmvp.presentation.screens.IScreens
import ru.nifontbus.testmvp.presentation.users.adapter.UsersListPresenter
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var usersRepo: IGithubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var userScopeContainer: IUserScopeContainer

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val currentUser = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.repositoryScreen(currentUser))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe { users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        userScopeContainer.releaseUserScope()
        super.onDestroy()
    }
}