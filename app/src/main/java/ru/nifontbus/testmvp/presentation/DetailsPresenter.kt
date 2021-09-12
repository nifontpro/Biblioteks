package ru.nifontbus.testmvp.presentation

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.models.DetailsUserRepo
import ru.nifontbus.testmvp.views.DetailsView

class DetailsPresenter(
    private val detailsUserRepo: DetailsUserRepo,
    private val router: Router
) : MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        viewState.init()
        loadData()
    }

    private fun loadData() {
        val detailsUser = detailsUserRepo.detailsUser
        viewState.init(detailsUser)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}