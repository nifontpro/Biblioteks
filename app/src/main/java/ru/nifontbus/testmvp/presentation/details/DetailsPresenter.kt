package ru.nifontbus.testmvp.presentation.details

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App

class DetailsPresenter : MvpPresenter<DetailsView>() {

    private val detailsUserRepo = App.instance.detailsUserRepo
    private val router: Router = App.instance.router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showDetailsUser(detailsUserRepo.detailsUser)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}