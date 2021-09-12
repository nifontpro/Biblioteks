package ru.nifontbus.testmvp.presentation

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.models.GithubUser
import ru.nifontbus.testmvp.models.GithubUsersRepo
import ru.nifontbus.testmvp.views.UserItemView
import ru.nifontbus.testmvp.views.ui.UsersView

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    val router: Router
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.showLogin(user.login)
        }

        override fun getCount(): Int = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            // TODO
        }

    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed():Boolean {
        router.exit()
        return true
    }
}