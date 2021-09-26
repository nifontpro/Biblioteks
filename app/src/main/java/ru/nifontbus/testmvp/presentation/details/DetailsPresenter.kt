package ru.nifontbus.testmvp.presentation.details

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.data.GithubRepository
import ru.nifontbus.testmvp.models.repo.IGithubUsersRepo
import ru.nifontbus.testmvp.presentation.details.adapter.ReposListPresenter
import ru.nifontbus.testmvp.presentation.screens.IScreens
import ru.nifontbus.testmvp.presentation.users.CurrentDetailsUser
import javax.inject.Inject

class DetailsPresenter : MvpPresenter<DetailsView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    val reposListPresenter = ReposListPresenter()

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(CurrentDetailsUser.detailsUser)
        loadRepository()
    }

    private fun loadRepository() {
        usersRepo.getRepository(CurrentDetailsUser.detailsUser)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { repos ->
                reposListPresenter.repos.clear()
                reposListPresenter.repos.addAll(repos)
                viewState.updateList()

                reposListPresenter.itemClickListener = { itemView ->
                    val detailsRepos = reposListPresenter.repos[itemView.pos]
                    CurrentRepoInfo.detailsRepo = detailsRepos
                    Log.e("my", detailsRepos.toString())
                    router.navigateTo(screens.repoInfoScreen())
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