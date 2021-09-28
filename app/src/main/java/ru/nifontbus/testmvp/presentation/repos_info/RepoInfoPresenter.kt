package ru.nifontbus.testmvp.presentation.repos_info

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.presentation.details.DetailsPresenter
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import javax.inject.Inject

class RepoInfoPresenter : MvpPresenter<RepoInfoView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var usersPresenter: UsersPresenter

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(usersPresenter.currentUser)
        viewState.showRepoInfo(detailsPresenter.currentRepository)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}