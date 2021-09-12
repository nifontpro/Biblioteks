package ru.nifontbus.testmvp.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.nifontbus.testmvp.models.GithubUser

@AddToEndSingle
interface DetailsView: MvpView {
    fun init(detailsUser: GithubUser)
}