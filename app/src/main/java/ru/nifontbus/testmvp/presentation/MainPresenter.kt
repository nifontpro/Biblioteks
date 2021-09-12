package ru.nifontbus.testmvp.presentation

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.nifontbus.testmvp.views.MainView
import ru.nifontbus.testmvp.models.GithubUsersRepo
import ru.nifontbus.testmvp.screens.AndroidScreens

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.usersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}