package ru.nifontbus.testmvp.presentation.repository

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.repo.IGithubRepositoriesRepo
import ru.nifontbus.testmvp.presentation.repository.adapter.ReposListPresenter
import ru.nifontbus.testmvp.presentation.screens.IScreens
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import javax.inject.Inject

class RepositoryPresenter : MvpPresenter<RepositoryView>() {

    @Inject
    lateinit var usersPresenter: UsersPresenter

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var repo: IGithubRepositoriesRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    val reposListPresenter = ReposListPresenter()

    var currentRepository: GithubRepository = GithubRepository("Not init")

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(usersPresenter.currentUser)
        loadRepository()
    }

    private fun loadRepository() {
        repo.getRepository(usersPresenter.currentUser)
            .observeOn(uiScheduler)
            .subscribe { repos ->
                reposListPresenter.repos.clear()
                reposListPresenter.repos.addAll(repos)
                viewState.updateList()

                reposListPresenter.itemClickListener = { itemView ->
                    currentRepository = reposListPresenter.repos[itemView.pos]
                    router.navigateTo(screens.repoInfoScreen())
                }
            }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}