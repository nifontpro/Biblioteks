package ru.nifontbus.testmvp.presentation.repos_info

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.presentation.repository.RepositoryPresenter
import ru.nifontbus.testmvp.presentation.users.UsersPresenter
import javax.inject.Inject

class RepoInfoPresenter(
    private val user: GithubUser,
    private val repository: GithubRepository
) : MvpPresenter<RepoInfoView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(user)
        viewState.showRepoInfo(repository)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}