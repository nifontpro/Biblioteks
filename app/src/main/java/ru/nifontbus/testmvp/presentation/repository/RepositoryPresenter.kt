package ru.nifontbus.testmvp.presentation.repository

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.di.scopes.IRepositoryScopeContainer
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.models.repo.IGithubRepositoriesRepo
import ru.nifontbus.testmvp.presentation.repository.adapter.ReposListPresenter
import ru.nifontbus.testmvp.presentation.screens.IScreens
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import javax.inject.Inject

class RepositoryPresenter(private val user: GithubUser) : MvpPresenter<RepositoryView>() {

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var repo: IGithubRepositoriesRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var repositoryScopeContainer:IRepositoryScopeContainer

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(user)
        loadRepository()
    }

    private fun loadRepository() {
        repo.getRepository(user)
            .observeOn(uiScheduler)
            .subscribe { repos ->
                reposListPresenter.repos.clear()
                reposListPresenter.repos.addAll(repos)
                viewState.updateList()

                reposListPresenter.itemClickListener = { itemView ->
                    val currentRepository = reposListPresenter.repos[itemView.pos]
                    router.navigateTo(screens.repoInfoScreen(user, currentRepository))
                }
            }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        repositoryScopeContainer.releaseRepositoryScope()
        super.onDestroy()
    }
}