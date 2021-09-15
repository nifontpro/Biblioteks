package ru.nifontbus.testmvp.presentation

import ru.nifontbus.testmvp.models.GithubUser
import ru.nifontbus.testmvp.views.UserItemView

class UsersListPresenter : IUserListPresenter {

    val users = mutableListOf<GithubUser>()

    override var itemClickListener: ((UserItemView) -> Unit)? = null

    override fun bindView(view: UserItemView) {
        val user = users[view.pos]
        view.showLogin(user.login)
    }

    override fun getCount(): Int = users.size
}
