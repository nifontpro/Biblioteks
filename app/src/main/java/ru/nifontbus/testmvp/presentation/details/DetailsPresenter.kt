package ru.nifontbus.testmvp.presentation.details

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.remote.ApiHolder
import ru.nifontbus.testmvp.models.repo.GithubUsersRepo
import ru.nifontbus.testmvp.presentation.details.adapter.ReposListPresenter
import ru.nifontbus.testmvp.presentation.users.adapter.UsersListPresenter

class DetailsPresenter : MvpPresenter<DetailsView>() {

    private val usersRepo = GithubUsersRepo(ApiHolder.api)
    private val detailsUserRepo = App.instance.detailsUserRepo
    private val router: Router = App.instance.router

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showDetailsUser(detailsUserRepo.detailsUser)

        detailsUserRepo.detailsUser.reposUrl?.let { url ->
            usersRepo.getRepository(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repos ->
                    repos.forEach { repo ->
                        Log.e("my", repo.name.orEmpty())
                    }

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