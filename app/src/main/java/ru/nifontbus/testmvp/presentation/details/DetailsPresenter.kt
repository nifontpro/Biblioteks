package ru.nifontbus.testmvp.presentation.details

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.data.GithubUser
import ru.nifontbus.testmvp.models.repo.ApiHolder
import ru.nifontbus.testmvp.models.repo.GithubUsersRepo
import ru.nifontbus.testmvp.presentation.details.adapter.ReposListPresenter
import ru.nifontbus.testmvp.presentation.screens.AndroidScreens
import ru.nifontbus.testmvp.presentation.screens.IScreens

class DetailsPresenter() : MvpPresenter<DetailsView>() {

    private val usersRepo = GithubUsersRepo(ApiHolder.api)
    private val detailsUserRepo = App.instance.detailsUserRepo
    private val router: Router = App.instance.router

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(detailsUserRepo.detailsUser)
        loadRepository()

        reposListPresenter.itemClickListener = { itemView ->
            val detailsRepos = reposListPresenter.repos[itemView.pos]
            CurrentRepoInfo.detailsRepo = detailsRepos
            Log.e("my", detailsRepos.toString())
            router.navigateTo(AndroidScreens.repoInfoScreen())
        }
    }

    private fun loadRepository() {
        detailsUserRepo.detailsUser.reposUrl?.let { url ->
            usersRepo.getRepository(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repos ->
                    reposListPresenter.repos.clear()
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateList()
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