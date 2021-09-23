package ru.nifontbus.testmvp.presentation.repos_info

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser

@AddToEndSingle
interface RepoInfoView: MvpView {
    fun init()
    fun showDetailsUser(detailsUser: GithubUser)
    fun showRepoInfo(repo: GithubRepository)
}