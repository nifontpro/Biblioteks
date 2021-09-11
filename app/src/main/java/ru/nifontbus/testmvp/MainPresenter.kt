package ru.nifontbus.testmvp

import moxy.MvpPresenter
import ru.nifontbus.testmvp.models.GithubUser
import ru.nifontbus.testmvp.models.GithubUsersRepo
import ru.nifontbus.testmvp.presentation.IUserListPresenter
import ru.nifontbus.testmvp.views.UserItemView

class MainPresenter(val repo: GithubUsersRepo) : MvpPresenter<MainView>() {

    class UsersListPresenter: IUserListPresenter {

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

        usersListPresenter.itemClickListener = {itemView ->
            // TODO
        }

    }

    private fun loadData() {
        val users = repo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}