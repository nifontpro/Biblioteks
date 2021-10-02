package ru.nifontbus.testmvp.presentation.repos_info

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.presentation.repository.RepositoryPresenter
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import javax.inject.Inject

class RepoInfoPresenter : MvpPresenter<RepoInfoView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var usersPresenter: UsersPresenter

    @Inject
    lateinit var repositoryPresenter: RepositoryPresenter

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(usersPresenter.currentUser)
        viewState.showRepoInfo(repositoryPresenter.currentRepository)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}