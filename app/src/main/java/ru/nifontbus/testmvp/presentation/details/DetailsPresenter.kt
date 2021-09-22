package ru.nifontbus.testmvp.presentation.details

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.models.remote.ApiHolder
import ru.nifontbus.testmvp.models.repo.GithubUsersRepo

class DetailsPresenter : MvpPresenter<DetailsView>() {

    private val usersRepo = GithubUsersRepo(ApiHolder.api)
    private val detailsUserRepo = App.instance.detailsUserRepo
    private val router: Router = App.instance.router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showDetailsUser(detailsUserRepo.detailsUser)
        detailsUserRepo.detailsUser.reposUrl?.let { url ->
            usersRepo.getRepository(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repos ->
                    repos.forEach { repo ->
                        Log.e("my", repo.name.orEmpty())
                    }
                }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}