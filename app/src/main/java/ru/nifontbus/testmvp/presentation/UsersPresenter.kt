package ru.nifontbus.testmvp.presentation

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.GithubUser
import ru.nifontbus.testmvp.models.GithubUsersRepo
import ru.nifontbus.testmvp.screens.IScreens
import ru.nifontbus.testmvp.views.ui.UsersView

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()
    private val detailsUserRepo = App.instance.detailsUserRepo

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val detailsUser = usersListPresenter.users[itemView.pos]
            detailsUserRepo.detailsUser = detailsUser

            router.navigateTo(screens.detailsScreen())
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .map { user ->
                GithubUser("Привет, " + user.login)
            }
            .subscribe { user ->
                usersListPresenter.users.add(user)
            }

        viewState.updateList()

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}