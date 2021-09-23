package ru.nifontbus.testmvp.presentation.repos_info

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.presentation.details.CurrentRepoInfo

class RepoInfoPresenter : MvpPresenter<RepoInfoView>() {

    private val detailsUserRepo = App.instance.detailsUserRepo
    private val router: Router = App.instance.router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(detailsUserRepo.detailsUser)
        viewState.showRepoInfo(CurrentRepoInfo.detailsRepo)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}