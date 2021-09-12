package ru.nifontbus.testmvp.presentation

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.app.App
import ru.nifontbus.testmvp.views.DetailsView

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