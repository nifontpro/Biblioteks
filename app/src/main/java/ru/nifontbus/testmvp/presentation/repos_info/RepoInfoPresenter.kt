package ru.nifontbus.testmvp.presentation.repos_info

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.presentation.details.CurrentRepoInfo
import ru.nifontbus.testmvp.presentation.users.CurrentDetailsUser

class RepoInfoPresenter : MvpPresenter<RepoInfoView>() {

    private val router: Router = App.instance.router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(CurrentDetailsUser.detailsUser)
        viewState.showRepoInfo(CurrentRepoInfo.detailsRepo)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}