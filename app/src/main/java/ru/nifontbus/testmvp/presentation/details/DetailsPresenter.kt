package ru.nifontbus.testmvp.presentation.details

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.repo.ApiHolder
import ru.nifontbus.testmvp.models.repo.GithubUsersRepo
import ru.nifontbus.testmvp.presentation.details.adapter.ReposListPresenter
import ru.nifontbus.testmvp.presentation.screens.AndroidScreens
import ru.nifontbus.testmvp.presentation.users.CurrentDetailsUser

class DetailsPresenter : MvpPresenter<DetailsView>() {

    private val usersRepo = GithubUsersRepo(ApiHolder.api)
    private val router: Router = App.appInstance.router

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(CurrentDetailsUser.detailsUser)
        loadRepository()
    }

    private fun loadRepository() {
        CurrentDetailsUser.detailsUser.reposUrl?.let { url ->
            usersRepo.getRepository(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repos ->
                    reposListPresenter.repos.clear()
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateList()

                    reposListPresenter.itemClickListener = { itemView ->
                        val detailsRepos = reposListPresenter.repos[itemView.pos]
                        CurrentRepoInfo.detailsRepo = detailsRepos
                        Log.e("my", detailsRepos.toString())
                        router.navigateTo(AndroidScreens.repoInfoScreen())
                    }
                }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}

object CurrentRepoInfo {
    var detailsRepo: GithubRepository = GithubRepository("Not init")
}