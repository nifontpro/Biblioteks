package ru.nifontbus.testmvp.presentation.repository

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.nifontbus.testmvp.models.data.GithubUser

@AddToEndSingle
interface RepositoryView: MvpView {
    fun init()
    fun showDetailsUser(detailsUser: GithubUser)
    fun updateList()
}