package ru.nifontbus.testmvp.presentation.details

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.nifontbus.testmvp.models.data.GithubUser

@AddToEndSingle
interface DetailsView: MvpView {
    fun showDetailsUser(detailsUser: GithubUser)
}